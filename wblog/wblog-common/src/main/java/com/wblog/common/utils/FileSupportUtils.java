package com.wblog.common.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author luyanan
 * @since 2020/11/30
 * 文件工具类
 **/
public class FileSupportUtils {

    //获取传输的multipartFile，将输入流+文件名转成multipartFile文件，去调用feignClient
    public static MultipartFile getMulFile(InputStream inputStream, String fileName) {
        FileItem fileItem = createFileItem(inputStream, fileName);
        //CommonsMultipartFile是feign对multipartFile的封装，但是要FileItem类对象
        MultipartFile mfile = new CommonsMultipartFile(fileItem);
        return mfile;
    }

    //FileItem类对象创建
    public static FileItem createFileItem(InputStream fis, String fileName) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "file";
        FileItem item = factory.createItem(textFieldName, "multipart/form-data", true, fileName);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        //使用输出流输出输入流的字节
        try {
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }
}
