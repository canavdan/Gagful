package com.gagful.mapper;

import com.gagful.base.BaseEntity;
import com.gagful.constant.VoteType;
import com.gagful.dto.PostDTO;
import com.gagful.entity.Post;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Mapper{

    @Autowired
    private ModelMapper modelMapper;

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        List<T> list = new ArrayList<>();
        for (S s : source) {
            list.add(modelMapper.map(s, targetClass));
        }
        return list;
    }

    public <S, T> T mapEntity(S source, Class<T> targetClass) {
       return modelMapper.map(source,targetClass);
    }

}
