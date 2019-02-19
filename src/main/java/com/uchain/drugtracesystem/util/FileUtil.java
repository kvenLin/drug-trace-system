package com.uchain.drugtracesystem.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

/**
 * @author hk
 */
@Slf4j
public class FileUtil {


    /**
     * 获取文件类型
     * @param suffix
     * @return
     */
    public static int getType(String suffix) {
        if (".xls".equals(suffix)) {
            return FileType.EXCEL.getValue();
        }
        if (".xlsx".equals(suffix)) {
            return FileType.EXCEL.getValue();
        }
        if (".doc".equals(suffix)) {
            return FileType.WORD.getValue();
        }
        if (".docx".equals(suffix)) {
            return FileType.WORD.getValue();
        }
        if (".mp3".equals(suffix)) {
            return FileType.VIDEO.getValue();
        }
        if (".mp4".equals(suffix)) {
            return FileType.VIDEO.getValue();
        }

        if (".jpg".equals(suffix)) {
            return FileType.IMAGE.getValue();
        }
        if (".png".equals(suffix)) {
            return FileType.IMAGE.getValue();
        }
        if (".img".equals(suffix)) {
            return FileType.IMAGE.getValue();
        }
        return 0;
    }

    /**
     * 上传文件
     * @param multipartFile
     * @param realPath
     * @return
     */
    public static boolean uploadFile(MultipartFile multipartFile, String realPath){
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(realPath);
            Files.write(path,bytes);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * 下载文件
     * @param response
     * @param realPath
     * @return
     */
    public static boolean downloadFile(HttpServletResponse response, String realPath){
        try {
            response.setCharacterEncoding("UTF-8");
            //response.setContentType("application/force-download");//应用程序强制下载
            File file = new File(realPath);
            //如果文件不存在
            if (file == null || !file.exists()) {
                log.error("文件 " + realPath + " 不存在!");
                return false;
            }
            String simpleName = file.getName().substring(file.getName().lastIndexOf("/") + 1);
            String newFileName = new String(simpleName.getBytes(), "utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + newFileName);
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            BufferedOutputStream bos = new BufferedOutputStream(
                    response.getOutputStream());
            byte[] buffer = new byte[1024];
            int length;
            while ((length = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }
            if (bis != null){
                bis.close();
            }
            if (bos != null){
                bos.close();
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获取文件真正的路径
     * @param fileId
     * @param uploadDir
     * @param fileName
     * @return
     */
    public static String getFileRealPath(Long fileId, String uploadDir, String fileName){
        return uploadDir + "/" + fileId + "_" + fileName;
    }

    /**
     * 获取文件的大小
     * @param fileSize
     * @return
     */
    public static String FormatFileSize(long fileSize) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileSize < 1024) {
            fileSizeString = df.format((double) fileSize) + "B";
        } else if (fileSize < 1048576) {
            fileSizeString = df.format((double) fileSize / 1024) + "K";
        } else if (fileSize < 1073741824) {
            fileSizeString = df.format((double) fileSize / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileSize / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 获取不带后缀的文件名
     * @param fileName
     * @return
     */
    public static String getFileNameWithoutSuffix(String fileName){
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    /**
     * 获取上传文件的后缀
     * @param multipartFile
     * @return
     */
    public static String getMultipartFileSuffix(MultipartFile multipartFile){
        String originalFilename = multipartFile.getOriginalFilename();
        return originalFilename.substring(originalFilename.lastIndexOf("."));
    }


    /**
     * 删除指定文件
     * @param realPath
     * @return
     */
    public static boolean deleteFile(String realPath) {
        File file = new File(realPath);
        if (!file.exists()){
            return true;
        }
        return file.delete();
    }

    /**
     * 在basePath下保存上传的文件夹
     * @param basePath
     * @param files
     */
    public static void saveMultiFile(String basePath, MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return;
        }
        if (basePath.endsWith("/")) {
            basePath = basePath.substring(0, basePath.length() - 1);
        }
        for (MultipartFile file : files) {
            String filePath = basePath + "/" + file.getOriginalFilename();
            makeDir(filePath);
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 确保目录存在，不存在则创建
     * @param filePath
     */
    private static void makeDir(String filePath) {
        if (filePath.lastIndexOf('/') > 0) {
            String dirPath = filePath.substring(0, filePath.lastIndexOf('/'));
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }

}
