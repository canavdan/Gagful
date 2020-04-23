package com.gagful.controller;

import com.gagful.base.BaseController;
import com.gagful.base.BaseRequest;
import com.gagful.base.BaseResponse;
import com.gagful.constant.APIConstants;
import com.gagful.dto.CategoryDTO;
import com.gagful.entity.Category;
import com.gagful.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(APIConstants.VERSION + "/category")
public class CategoryController extends BaseController<Category> {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<BaseResponse> findAll(){
        return responseUtil.successListResponse(categoryService.findAll(),1,10);
    }

    @PostMapping(value= "/",produces = APIConstants.CHARSET)
    public ResponseEntity<BaseResponse> save(HttpServletRequest request, @RequestBody BaseRequest<CategoryDTO> categoryDTO){
        CategoryDTO categoryDTOSaved=categoryService.save(categoryDTO.getData());
        return responseUtil.successResponse(categoryDTOSaved);
    }

}


