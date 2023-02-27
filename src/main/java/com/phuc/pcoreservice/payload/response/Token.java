package com.phuc.pcoreservice.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.phuc.pcoreservice.util.Constant;
import lombok.Data;

import java.util.Date;

@Data
public class Token {

    private String username;

    private String accessToken;

    private String refreshToken;

    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE_TIME, timezone = "Asia/Ho_Chi_Minh")
    private Date createAt;

    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE_TIME, timezone = "Asia/Ho_Chi_Minh")
    private Date expiredAt;

    private Object role;

}
