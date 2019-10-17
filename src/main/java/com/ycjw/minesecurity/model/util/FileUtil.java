package com.ycjw.minesecurity.model.util;

import com.ycjw.minesecurity.exception.ExceptionZyc;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

public class FileUtil {

    /**
     * 上传文件
     * @param file 上传的文件
     * @param filepath  上传的文件夹路径
     * @param filename 上传后的文件名字，不带后缀
     * @throws ExceptionZyc
     */
    public static String uploadFile(MultipartFile file,String filepath,String filename) throws ExceptionZyc{
        //判断文件是否为空
        if (file == null||file.isEmpty()) {
            //文件为空
            throw ExceptionZyc.FILE_IS_NULL;
        }
        String fileAllName = file.getOriginalFilename();
        int index = fileAllName.indexOf(".");
        String filetype = fileAllName.substring(index,fileAllName.length());
        File file1 = new File(filepath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        try {
            uploadFile(file.getBytes(), filepath, filename + filetype);
            return filepath + filename + filetype;
        } catch (Exception e) {
            e.printStackTrace();
            throw ExceptionZyc.FILE_IS_NULL;
        }
    }

    public static void downloadFile(String filepath,HttpServletResponse response) throws ExceptionZyc{
        if(filepath == null || filepath.equals("") || filepath.length() <= 0){
            //文件不存在，抛出错误
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        try {
            File file = new File(filepath);
            //判断文件是否存在如果不存在就返回默认图标
            if(!(file.exists() && file.canRead())) {
                //用户不存在，抛出错误
                response.setStatus(404);
                throw ExceptionZyc.DOWNLOAD_FAILURE;
            }
            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[(int)file.length()];
            int length = inputStream.read(data);
            inputStream.close();
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "utf-8"));
            System.out.println(file.getName());
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(500);
            throw ExceptionZyc.DOWNLOAD_FAILURE;
        }
    }

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
