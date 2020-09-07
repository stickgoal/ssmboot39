package com.woniuxy.cq.ssmboot39.controller;

import com.woniuxy.cq.ssmboot39.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class UploadMultiController {

    @RequestMapping("upload")
    public Result uploadMulti(MultipartFile file) throws IOException {

        file.transferTo(new File("D:/tmp/"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))+file.getOriginalFilename()));

        return Result.success();
    }


}
