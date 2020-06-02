package edu.gpnu.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import sun.misc.BASE64Decoder;


import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理图片的工具类
 */
@Component
public class ImageUtil {

    public static String uploadpath;

    //进行模式匹配  截取字符串  例如//data:image/bmp;base64,
    public static Pattern pattern = Pattern.compile("^data:image/(.*);base64,");

    @Value(value = "${funny.path.upload}")
    public void setUploadpath(String uploadpath) {
        ImageUtil.uploadpath = uploadpath;
    }


    /**
     * 将图片写出到硬盘
     *
     * @param path      传入的是相对路径  传的是 登录用户的学号  例如   传入的是/123
     * @param imageData 图片的base64
     * @return 返回要存到数据库的值
     */
    public static String writeImg(String path, String imageData) throws IOException,RuntimeException {
        String relativeAddr = null;

        //后缀名 将从imageData里面截取出来
        String extension = null;
        Matcher matcher = pattern.matcher(imageData);
        if (matcher.find()){
            extension = matcher.group(1);
            imageData = matcher.replaceFirst("");
        }else {
            throw new RuntimeException("图片格式有错");
        }
        BASE64Decoder decoder = new BASE64Decoder();
        //图片的字节数组
        byte[] imgBytes = decoder.decodeBuffer(imageData);
        String filePath = uploadpath + path;
        File dir = new File(filePath);
        if (!dir.exists()) {
            //如果文件夹不存在 则创建
            dir.mkdirs();
        }
        //生成文件名 uuid.后缀
        String filename = UUIDGenerator.get32UUID() + "." + extension;
        //图片存放地址
        String savePath = dir.getPath() + File.separator + filename;
        File saveFile = new File(savePath);
        //将文件写出
        FileCopyUtils.copy(imgBytes,saveFile);

        relativeAddr = path + File.separator + filename;
        return relativeAddr;
    }

}
