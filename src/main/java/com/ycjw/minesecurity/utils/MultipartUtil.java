package com.ycjw.minesecurity.utils;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

public class MultipartUtil {

    /**
     * 获取文件的后缀名
     * @param multipartFile
     * @return
     * @throws Exception
     */
    public static String getFileType(MultipartFile multipartFile)throws Exception{
        if (multipartFile == null||multipartFile.isEmpty()) {
            //文件为空
            throw ExceptionZyc.IMAGE_IS_NULL;
        }
        String fileAllName = multipartFile.getOriginalFilename();
        int index = fileAllName.indexOf(".");
        String fileType = fileAllName.substring(index,fileAllName.length());
        return fileType;
    }

    /**
     * 上传图片
     * @param multipartFile 要上传的文件
     * @param fullFileName 未添加后缀名时的文件的名称
     * @param pathPrefix 上传路径
     * @return  true-图片上传成功，false-图片上传失败
     * @throws Exception
     */
    public static boolean upLoadImage(MultipartFile multipartFile,String fullFileName,String pathPrefix) throws Exception{
        //判断文件是否为空
        if (multipartFile == null||multipartFile.isEmpty()) {
            //文件为空
            throw ExceptionZyc.IMAGE_IS_NULL;
        }
        File fileImage = new File(pathPrefix);
        if (!fileImage.exists()) {
            fileImage.mkdirs();
        }
        String imgType=getFileType(multipartFile);
        try {
            uploadFile(multipartFile.getBytes(), pathPrefix, fullFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 上传文件
     * @param file
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    private static void uploadFile(byte[] file, String filePath, String fileName)throws Exception{
        File targetFile=new File(filePath);
        if(targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out =new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

}
