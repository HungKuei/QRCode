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
     * ���ɶ�ά��ͼƬ
     * @author �����
     * @param content ��ά������
     * @param imgPath ��ά��ͼƬ�ı���·��
     */
    public static void getQrcodeImg(String content,String imgPath){
        int width=140;
        int height=140;
        //ʵ����Qrcode
        Qrcode qrcode=new Qrcode();
        //���ö�ά����Ŵ���L(7%) M(15%) Q(25%) H(35%)
        qrcode.setQrcodeErrorCorrect('M');
        qrcode.setQrcodeEncodeMode('B');        
        //���ö�ά��ߴ�(1~49)
        qrcode.setQrcodeVersion(7);
        //����ͼƬ�ߴ�
        BufferedImage bufImg=new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        
        //���ƶ�ά��ͼƬ
        Graphics2D gs=bufImg.createGraphics();
        //���ö�ά�뱳����ɫ
        gs.setBackground(Color.WHITE);
        //����һ����������
        gs.clearRect(0, 0, width, height);
        //���ö�ά���ͼƬ��ɫֵ ��ɫ
        gs.setColor(Color.BLACK);
        
        //��ȡ���ݵ��ֽ�����,���ñ��뼯
        try {
            byte[] contentBytes=content.getBytes("utf-8");
            int pixoff=2;
            //�����ά��
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
            //���ɶ�ά��ͼƬ
            File imgFile=new File(imgPath);
            ImageIO.write(bufImg, "png", imgFile);
            
            System.out.println("��ά�����ɳɹ���");
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        getQrcodeImg("������֣�", "D:\\Qrcode\\qrcode.png");
    }
}