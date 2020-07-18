package com.gagful.base;

import com.gagful.controller.response.ResponseUtil;
import com.gagful.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseController<T extends BaseEntity> {

    @Autowired
    protected ResponseUtil responseUtil;

    @Autowired
    protected RedisService redisService;

}
