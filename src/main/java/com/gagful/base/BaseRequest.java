package com.gagful.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gagful.constant.APIStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequest <T extends Object> implements Serializable {


    private String message;

    private T data;

    private List<T> list;

    private Integer pageNo=1;

    private Integer pageItems=10;

    private Integer count;

    @JsonFormat(pattern = "dd-mm-yyyy HH:mm:ss")
    private Date createdDate;


}
