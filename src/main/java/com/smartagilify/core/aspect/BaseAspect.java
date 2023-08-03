package com.smartagilify.core.aspect;

import com.smartagilify.core.constant.ErrorConstant;
import com.smartagilify.core.constant.RestAddress;
import com.smartagilify.core.model.BaseDTO;
import com.smartagilify.core.model.ResultDTO;
import com.smartagilify.core.util.HashUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Aspect
@Component
public class BaseAspect {

    protected final Pattern controllerRestPattern = Pattern.compile(RestAddress.REST_PREFIX + "/(.*?)/", Pattern.DOTALL);

    @Before(value = "execution(* com.smartagilify.core.controllers.*.*ById*(Long, String)) && args(id, hashcode)", argNames = "id,hashcode")
    public void checkIdAndHashCode(Long id, String hashcode) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) throw new InvalidParameterException(ErrorConstant.INVALID_REQUEST_PARAMETER);
        HttpServletRequest request = attributes.getRequest();

        String expectedHash = HashUtil.createIdHashcode(id, getRestControllerName(request), -1);
        //TODO get user id from security
        if (!expectedHash.equals(hashcode))
            throw new InvalidParameterException(ErrorConstant.INVALID_REQUEST_PARAMETER);
    }

    @Around(value = "execution(org.springframework.http.ResponseEntity com.smartagilify.core.controllers.*.save(..)) "
            + "|| execution(org.springframework.http.ResponseEntity com.smartagilify.core.controllers.*.update(..)) "
            + "|| execution(org.springframework.http.ResponseEntity com.smartagilify.core.controllers.*.loadAll(..))")
    public Object createIdAndHashCode(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) throw new InvalidParameterException(ErrorConstant.INVALID_REQUEST_PARAMETER);
        HttpServletRequest request = attributes.getRequest();
        Object result = joinPoint.proceed();
        if (result instanceof ResponseEntity<?> proceed && proceed.getBody() instanceof ResultDTO<?> dto) {
            setIdHashCode(request, dto.getResultList());
        }
        return result;
    }

    protected final void setIdHashCode(HttpServletRequest request, List<? extends BaseDTO> dtoList) {
        String restControllerName = getRestControllerName(request);
        for (BaseDTO dto : dtoList) {
            dto.setHashCode(HashUtil.createIdHashcode(dto.getId(), restControllerName, -1));
            //TODO get user id from security
        }
    }

    protected final String getRestControllerName(HttpServletRequest request) {
        Matcher matcher = controllerRestPattern.matcher(request.getRequestURL());
        return matcher.find() ? matcher.group(1) : "";
    }
}
