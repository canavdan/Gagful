package com.gagful.service;

import com.gagful.dto.CategoryDTO;
import com.gagful.dto.PostDTO;

import javax.validation.Valid;
import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findAll();
    CategoryDTO save(@Valid CategoryDTO categoryDTO);
}
