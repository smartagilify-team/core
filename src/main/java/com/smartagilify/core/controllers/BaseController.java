package com.smartagilify.core.controllers;

import com.smartagilify.core.constant.ErrorConstant;
import com.smartagilify.core.constant.MessageConstant;
import com.smartagilify.core.constant.RestAddress;
import com.smartagilify.core.entities.BaseEntity;
import com.smartagilify.core.mappers.BaseMapper;
import com.smartagilify.core.model.BaseDTO;
import com.smartagilify.core.model.InputDTO;
import com.smartagilify.core.model.PageDTO;
import com.smartagilify.core.model.ResultDTO;
import com.smartagilify.core.services.BaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseController<E extends BaseEntity, R extends JpaRepository<E, Long>, S extends BaseService<E, R, M, D>, M extends BaseMapper<E, D>, D extends BaseDTO> {

    protected final S service;
    //TODO add exception handler to persist exception in list or table
    @PostMapping(value = RestAddress.SAVE)
    public ResponseEntity<ResultDTO<D>> save(@Valid @RequestBody InputDTO<D> dto) {
        if (dto.getData().getId() != null || dto.getData().getVersion() != null)
            throw new InvalidParameterException(ErrorConstant.INVALID_REQUEST_PARAMETER);
        D save = service.save(dto.getData(), dto.getUserId());
        return new ResponseEntity<>(ResultDTO.<D>builder()
                .resultList(Collections.singletonList(save))
                .message(MessageConstant.SAVED_SUCCESSFULLY)
                .build(), HttpStatus.CREATED);
    }

    @PostMapping(value = RestAddress.FIND_ALL)
    public ResponseEntity<ResultDTO<D>> loadAll(@RequestBody InputDTO<D> dto) {
        PageDTO<D> all = service.findAll(
                dto.getInputFilter().getPageNumber(),
                dto.getInputFilter().getPageSize(),
                dto.getInputFilter().getDirection() == null ? Sort.Direction.DESC : dto.getInputFilter().getDirection(),
                dto.getInputFilter().getOrderBy()
        );
        return new ResponseEntity<>(ResultDTO.<D>builder()
                .resultList(all.getResults())
                .pageNumber(dto.getInputFilter().getPageNumber())
                .pageSize(all.getResults().size())
                .totalPages(all.getTotalPages())
                .totalRecordSize(all.getTotalElements())
                .message(MessageConstant.FIND_ALL_SUCCESSFULLY)
                .build(), HttpStatus.OK);
    }

    @GetMapping(value = RestAddress.FIND_BY_ID)
    public ResponseEntity<ResultDTO<D>> loadById(@PathVariable Long id, @RequestParam String hashcode) {
        return new ResponseEntity<>(ResultDTO.<D>builder()
                .resultList(Collections.singletonList(service.findById(id)))
                .message(MessageConstant.FIND_SUCCESSFULLY)
                .build(), HttpStatus.OK);
    }

    @PutMapping(value = RestAddress.UPDATE)
    public ResponseEntity<ResultDTO<D>> update(@Valid @RequestBody InputDTO<D> dto) {
        if (dto.getData().getId() == null || dto.getData().getVersion() == null)
            throw new InvalidParameterException(ErrorConstant.INVALID_REQUEST_PARAMETER);
        return new ResponseEntity<>(ResultDTO.<D>builder()
                .resultList(Collections.singletonList(service.update(dto.getData(), dto.getUserId())))
                .message(MessageConstant.UPDATED_SUCCESSFULLY)
                .build(), HttpStatus.OK);
    }

    @DeleteMapping(value = RestAddress.DELETE_BY_ID)
    public ResponseEntity<ResultDTO<D>> deleteById(@PathVariable Long id, @RequestParam String hashcode) {
        service.deleteById(id);
        return new ResponseEntity<>(ResultDTO.<D>builder()
                .message(MessageConstant.DELETED_SUCCESSFULLY)
                .build(), HttpStatus.NO_CONTENT);
    }

}
