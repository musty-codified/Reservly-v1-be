package com.mustycodified.Reservlyv1be.restartifacts;

import com.mustycodified.Reservlyv1be.enums.ResponseCodeEnum;
import lombok.Data;

@Data
public class BaseResponse {
    private int code;
    private String description;

    public BaseResponse() {
        this(ResponseCodeEnum.ERROR);   //default value
    }


    //seeing this through a different lens now
    public BaseResponse(ResponseCodeEnum responseCode) {
        this.code = responseCode.getCode();
        this.description = responseCode.getDescription();
    }

    public void assignResponseCodeAndDescription(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public BaseResponse(ResponseCodeEnum responseCode, String description) {
        this.code = responseCode.getCode();
        this.description = description;
    }

}
