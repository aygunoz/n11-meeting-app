package com.example.n11meetingapp.common;
import org.modelmapper.ModelMapper;

public final class Mapper {

    public <R> R convertToType(Object source, Class<R> resultClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(source, resultClass);
    }

}
