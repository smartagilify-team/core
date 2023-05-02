package com.smartagilify.core.mappers;

import java.util.List;

public interface BaseMapper<E, D> {
    D entity2Dto(E entity);

    List<D> entity2Dto(List<E> entities);

    E dto2Entity(D dto);

    List<E> dto2Entity(List<D> listOfDto);
}
