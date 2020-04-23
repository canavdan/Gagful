package com.gagful.service.impl;

import com.gagful.dto.CategoryDTO;
import com.gagful.entity.Category;
import com.gagful.mapper.Mapper;
import com.gagful.repository.CategoryRepository;
import com.gagful.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public List<CategoryDTO> findAll() {
        return mapper.mapList(categoryRepository.findAll(),CategoryDTO.class);
    }

    @Override
    public CategoryDTO save(@Valid CategoryDTO categoryDTO) {
        Category category = mapper.mapEntity(categoryDTO,Category.class);
        return mapper.mapEntity(categoryRepository.save(category),CategoryDTO.class);
    }
}
