package com.gagful.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class Mapper {


    private final ModelMapper modelMapper;

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        List<T> list = new ArrayList<>();
        for (S s : source) {
            list.add(modelMapper.map(s, targetClass));
        }
        return list;
    }

    public <S, T> T mapEntity(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

}
