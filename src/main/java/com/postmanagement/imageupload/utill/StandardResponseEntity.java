package com.postmanagement.imageupload.utill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponseEntity {
    private int code;
    private String message;
    private Object data;
}
