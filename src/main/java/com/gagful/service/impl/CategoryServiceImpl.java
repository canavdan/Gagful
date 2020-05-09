package com.gagful.service.impl;

import com.gagful.dto.CategoryDTO;
import com.gagful.entity.Category;
import com.gagful.mapper.Mapper;
import com.gagful.repository.CategoryRepository;
import com.gagful.service.CategoryService;
import com.gagful.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Validated
@Slf4j
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;


    private final Mapper mapper;

    @Override
    public List<CategoryDTO> findAll() {
        log.info("Find all categories: ");
        return mapper.mapList(categoryRepository.findAll(), CategoryDTO.class);
    }

    @Override
    public CategoryDTO save(@Valid CategoryDTO categoryDTO) {
        log.info("Save new category: " + categoryDTO.toString());
        if (categoryDTO.getIcon().length < 100) {
            try {
                categoryDTO.setIcon(FileUtil.setCategoryIcon());
            } catch (IOException e) {
                log.info("Save exception: " + e.toString());
            }
        }
        Category category = mapper.mapEntity(categoryDTO, Category.class);
        return mapper.mapEntity(categoryRepository.save(category), CategoryDTO.class);
    }
}
