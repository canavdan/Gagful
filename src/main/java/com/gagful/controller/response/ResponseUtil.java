package com.gagful.controller.response;


import com.gagful.base.BaseResponse;
import com.gagful.constant.APIStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ResponseUtil {
    private BaseResponse createBaseResponse(APIStatus apiStatus, Object data, boolean isSuccess) {
        return new BaseResponse(apiStatus, data, isSuccess);
    }

    private BaseResponse createBaseResponse(APIStatus apiStatus, List<?> list, boolean isSuccess, int pageNum, int pageItems) {
        return new BaseResponse(apiStatus, list, isSuccess,pageNum,pageItems);
    }

    public ResponseEntity<BaseResponse> successResponse(Object data) {
        return new ResponseEntity(createBaseResponse(APIStatus.OK, data, true), HttpStatus.OK);
    }
    public ResponseEntity<BaseResponse> successListResponse(List<?> list,int pageNum,int pageItems) {
        return new ResponseEntity(createBaseResponse(APIStatus.OK, list, true,pageNum,pageItems), HttpStatus.OK);
    }

    public ResponseEntity<BaseResponse> badRequestResponse(String errorDesc) {
        return new ResponseEntity(createBaseResponse(APIStatus.ERR_BAD_REQUEST, errorDesc, false), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> badRequestResponse(String errorDesc,List<String> errors) {
        return new ResponseEntity(createBaseResponse(APIStatus.ERR_BAD_REQUEST, errors, false), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<BaseResponse> errorResponse(String errorDesc) {
        return new ResponseEntity(createBaseResponse(APIStatus.ERR_INTERNAL_SERVER, errorDesc, true), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
