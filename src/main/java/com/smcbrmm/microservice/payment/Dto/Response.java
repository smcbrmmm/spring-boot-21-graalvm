package com.smcbrmm.microservice.payment.Dto;

import com.smcbrmm.microservice.payment.Constant.ApiStatus;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> {

    private HttpStatus code;
    private ApiStatus status;
    private List<T> data;
}
