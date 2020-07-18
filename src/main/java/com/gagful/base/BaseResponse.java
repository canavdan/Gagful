package com.gagful.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gagful.constant.APIStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T extends Object> implements Serializable {

    private int status;

    private String message;

    private T data;

    private List<T> list;

    private boolean isSuccess;

    private Integer pageNo = 1;

    private Integer pageItems = 10;

    private Integer count;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createdDate;

    public BaseResponse(APIStatus apiStatus, T data, boolean isSuccess) {
        this.status = apiStatus.getCode();
        this.message = apiStatus.getDescription();
        this.data = data;
        this.isSuccess = isSuccess;
        this.createdDate = new Date();
        this.count = 1;
    }

    public BaseResponse(APIStatus apiStatus, List<T> list, boolean isSuccess, int pageNo, int pageItems) {
        this.status = apiStatus.getCode();
        this.message = apiStatus.getDescription();
        this.list = list;
        this.isSuccess = isSuccess;
        this.pageItems = pageItems;
        this.pageNo = pageNo;
        this.count = list.size();
        this.createdDate = new Date();
    }
}
