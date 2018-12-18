package com.mail.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Hibiki
 * @version 1.0.0
 * @ClassName IFileService.java
 * @Description TODO
 * @createTime 2018年12月01日 17:50:00
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
