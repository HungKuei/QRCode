package com.java.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QrcodeImg {
    /**
     * 生成二维码图片
     * @author 郭洪奎
     * @param content 二维码内容
     * @param imgPath 二维码图片的保存路径
     */
    public static void getQrcodeImg(String content,String imgPath){
        int width=140;
        int height=140;
        //实例化Qrcode
        Qrcode qrcode=new Qrcode();
        //设置二维码的排错率L(7%) M(15%) Q(25%) H(35%)
        qrcode.setQrcodeErrorCorrect('M');
        qrcode.setQrcodeEncodeMode('B');        
        //设置二维码尺寸(1~49)
        qrcode.setQrcodeVersion(7);
        //设置图片尺寸
        BufferedImage bufImg=new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        
        //绘制二维码图片
        Graphics2D gs=bufImg.createGraphics();
        //设置二维码背景颜色
        gs.setBackground(Color.WHITE);
        //创建一个矩形区域
        gs.clearRect(0, 0, width, height);
        //设置二维码的图片颜色值 黑色
        gs.setColor(Color.BLACK);
        
        //获取内容的字节数组,设置编码集
        try {
            byte[] contentBytes=content.getBytes("utf-8");
            int pixoff=2;
            //输出二维码
            if(contentBytes.length>0&&contentBytes.length<120){
                boolean[][] codeOut=qrcode.calQrcode(contentBytes);
                for(int i=0;i<codeOut.length;i++){
                    for(int j=0;j<codeOut.length;j++){
                        if(codeOut[j][i]){
                            gs.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);
                        }
                    }
                }    
            }
            gs.dispose();
            bufImg.flush();
            //生成二维码图片
            File imgFile=new File(imgPath);
            ImageIO.write(bufImg, "png", imgFile);
            
            System.out.println("二维码生成成功！");
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        getQrcodeImg("新年快乐！", "D:\\Qrcode\\qrcode.png");
    }
}