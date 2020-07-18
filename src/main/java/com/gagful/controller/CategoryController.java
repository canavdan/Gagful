package com.gagful.controller;

import com.gagful.base.BaseController;
import com.gagful.base.BaseRequest;
import com.gagful.base.BaseResponse;
import com.gagful.constant.APIConstants;
import com.gagful.dto.CategoryDTO;
import com.gagful.entity.Category;
import com.gagful.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping(APIConstants.VERSION)
public class CategoryController extends BaseController<Category> {


    private final CategoryService categoryService;

    @GetMapping("/public/categories")
    public ResponseEntity<BaseResponse> findAll() {
        return responseUtil.successListResponse(categoryService.findAll(), 1, 10);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = "/admin/secure/category", produces = APIConstants.CHARSET)
    public ResponseEntity<BaseResponse> save(@RequestPart MultipartFile image, @RequestPart BaseRequest<CategoryDTO> categoryDTO) throws IOException {
        categoryDTO.getData().setIcon(image.getBytes());
        //TODO compress
        CategoryDTO categoryDTOSaved = categoryService.save(categoryDTO.getData());
        return responseUtil.successResponse(categoryDTOSaved);
    }

}


