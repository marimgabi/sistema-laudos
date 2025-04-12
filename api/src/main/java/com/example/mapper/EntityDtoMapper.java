package com.example.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public interface EntityDtoMapper<E, D> {
    D entityToDto(E entity, Class<D> dtoClass);
    E dtoToEntity(D dto, Class<E> entityClass);


    default List<D> entityToDtoList(List<E> entityList, Class<D> dtoClass){
        return entityList.stream()
                .map(entity -> entityToDto(entity, dtoClass))
                .collect(Collectors.toList());
    }


    default List<E> dtoToEntityList(List<D> dtoList, Class<E> entityClass){
        return dtoList.stream()
                .map(dto -> dtoToEntity(dto, entityClass))
                .collect(Collectors.toList());
    }


    default Page<D> entityPageToDtoPage(Page<E> entityPage, Class<D> dtoClass){
        List<D> dtos = entityPage.getContent().stream()
                .map(entity -> entityToDto(entity, dtoClass))
                .collect(Collectors.toList());


        return new PageImpl<>(dtos, entityPage.getPageable(),entityPage.getTotalElements());
    }


}

