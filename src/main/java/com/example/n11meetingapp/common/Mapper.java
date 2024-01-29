package com.example.n11meetingapp.common;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public final class Mapper {

    private final ModelMapper modelMapper;

    public Mapper() {
        this.modelMapper = new ModelMapper();
    }

    public <R> R convertToType(Object source, Class<R> resultClass) {
        return modelMapper.map(source, resultClass);
    }

    public <R> List<R> convertListToType(List<?> sourceList, Class<R> resultClass) {
        return sourceList.stream()
                .map(source -> modelMapper.map(source, resultClass))
                .collect(Collectors.toList());
    }
}
