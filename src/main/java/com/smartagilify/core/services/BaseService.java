package com.smartagilify.core.services;

import com.smartagilify.core.entities.BaseEntity;
import com.smartagilify.core.entities.base.HibernateStaticValues;
import com.smartagilify.core.enumerations.EN_STATE;
import com.smartagilify.core.exceptions.BusinessException;
import com.smartagilify.core.mappers.BaseMapper;
import com.smartagilify.core.model.BaseDTO;
import com.smartagilify.core.model.PageDTO;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseService<E extends BaseEntity, M extends BaseMapper<E, D>, D extends BaseDTO> {

    protected final M mapper = Mappers.getMapper(getMapper());

    protected abstract Class<M> getMapper();

    protected final JpaRepository<E, Long> jpaRepository;

    public D save(D dto, Long userId) {
        E entity = mapper.dto2Entity(dto);
        entity.setState(EN_STATE.CREATED);
        entity.setCreateById(userId);
        entity.setCreateDate(LocalDateTime.now());
        return mapper.entity2Dto(jpaRepository.save(entity));
    }

    public D update(D dto, Long userId) {
        E entity = mapper.dto2Entity(dto);
        entity.setState(EN_STATE.UPDATED);
        entity.setUpdateById(userId);
        entity.setUpdateDate(LocalDateTime.now());
        return mapper.entity2Dto(jpaRepository.save(entity));
    }


    public Iterable<D> saveAll(List<D> dtoList, Long userId) {
        if (Objects.isNull(dtoList))
            throw new BusinessException("Entities must not be null!");
        List<D> out = new ArrayList<>();
        for (D dto : dtoList) {
            out.add(save(dto, userId));
        }
        return out;
    }

    public D findById(Long id) {
        Optional<E> byId = jpaRepository.findById(id);
        return mapper.entity2Dto(byId.orElseThrow(() -> new BusinessException("ID IS NOT VALID")));
    }

    public PageDTO<D> findAll(int page, int size, Sort.Direction direction, String... properties) {
        if (Objects.isNull(properties) || Objects.equals(properties.length, 0))
            properties = new String[]{HibernateStaticValues.ID};
        Page<E> all = jpaRepository.findAll(PageRequest.of(page, size, direction, properties));
        return PageDTO.<D>builder()
                .results(mapper.entity2Dto(all.getContent()))
                .totalPages(all.getTotalPages())
                .totalElements(all.getTotalElements())
                .build();
    }

    public List<D> findAll(Sort sort) {
        return mapper.entity2Dto(jpaRepository.findAll(sort));
    }

    public List<D> findAllById(Iterable<Long> ids) {
        return mapper.entity2Dto(jpaRepository.findAllById(ids));
    }

    public Page<E> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }

    public void softDelete(D dto, Long userId) {
        E entity = mapper.dto2Entity(dto);
        entity.setState(EN_STATE.DELETED);
        entity.setUpdateById(userId);
        entity.setUpdateDate(LocalDateTime.now());
        jpaRepository.save(entity);
    }

    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    public void delete(D dto) {
        jpaRepository.delete(mapper.dto2Entity(dto));
    }

    public void deleteAllById(Iterable<Long> ids) {
        jpaRepository.deleteAllById(ids);
    }

    public void deleteAll(List<D> dtoList) {
        jpaRepository.deleteAll(mapper.dto2Entity(dtoList));
    }

    public void deleteAllInBatch(List<D> dtoList) {
        jpaRepository.deleteAllInBatch(mapper.dto2Entity(dtoList));
    }

    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        jpaRepository.deleteAllByIdInBatch(ids);
    }

    public E getReferenceById(Long id) {
        return jpaRepository.getReferenceById(id);
    }

}
