package com.smartagilify.core.controllers;


import com.smartagilify.core.entities.BaseEntity;
import com.smartagilify.core.mappers.BaseMapper;
import com.smartagilify.core.model.BaseDTO;
import com.smartagilify.core.model.InputDTO;
import com.smartagilify.core.model.PageDTO;
import com.smartagilify.core.model.ResultDTO;
import com.smartagilify.core.services.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequiredArgsConstructor
public abstract class BaseController<E extends BaseEntity, M extends BaseMapper<E, D>, D extends BaseDTO> {
    protected final BaseService<E, M, D> service;

    @PostMapping(value = RestAddress.SAVE)
    public ResponseEntity<ResultDTO<D>> save(@RequestBody InputDTO<D> dto) {
        D save = service.save(dto.getData(), dto.getUserId());
        return new ResponseEntity<>(ResultDTO.<D>builder()
                .resultList(Collections.singletonList(save))
                .message("CREATED")
                .build(), HttpStatus.CREATED);
    }

    @PostMapping(value = RestAddress.FIND_ALL)
    public ResponseEntity<ResultDTO<D>> loadAll(@RequestBody InputDTO<D> dto) {
        PageDTO<D> all = service.findAll(
                dto.getInputFilter().getPageNumber(),
                dto.getInputFilter().getPageSize(),
                dto.getInputFilter().getDirection() == null ? Sort.Direction.DESC : dto.getInputFilter().getDirection(),
                null
        );
        return new ResponseEntity<>(ResultDTO.<D>builder()
                .resultList(all.getResults())
                .pageNumber(dto.getInputFilter().getPageNumber())
                .pageSize(all.getResults().size())
                .totalPages(all.getTotalPages())
                .totalRecordSize(all.getTotalElements())
                .message("FIND ALL SUCCESSFULLY")
                .build(), HttpStatus.OK);
    }

    @GetMapping(value = RestAddress.FIND_BY_ID)
    public ResponseEntity<ResultDTO<D>> loadById(@PathVariable Long id) {
        return new ResponseEntity<>(ResultDTO.<D>builder()
                .resultList(Collections.singletonList(service.findById(id)))
                .message("FIND SUCCESSFULLY")
                .build(), HttpStatus.OK);
    }

    @PutMapping(value = RestAddress.UPDATE)
    public ResponseEntity<ResultDTO<D>> update(@RequestBody InputDTO<D> dto) {
        return new ResponseEntity<>(ResultDTO.<D>builder()
                .resultList(Collections.singletonList(service.update(dto.getData(), dto.getUserId())))
                .message("UPDATED")
                .build(), HttpStatus.OK);
    }

    @DeleteMapping(value = RestAddress.DELETE)
    public ResponseEntity<ResultDTO<D>> delete(@RequestBody InputDTO<D> dto) {
        service.softDelete(dto.getData(), dto.getUserId());
        return new ResponseEntity<>(ResultDTO.<D>builder()
                .message("DELETED")
                .build(), HttpStatus.NO_CONTENT);
    }

}
