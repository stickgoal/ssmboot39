package com.woniuxy.cq.ssmboot39.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ValidationUtil {


    public static String extract(BindingResult result) {
        List<FieldError> allErrors = result.getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        if (!allErrors.isEmpty()) {
            for (FieldError e : allErrors) {
                stringBuilder.append(e.getField()).append(":").append(e.getDefaultMessage())
                        .append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        }
        return null;
    }


}
