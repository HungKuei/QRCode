package com.java.main;

import com.java.utils.QRCodeUtil;

public class QRCodeTest {
	
	public static void main(String[] args) throws Exception {
		
		 QRCodeUtil.generateQRImage("Hello QRcode!", "D:/Qrcode/logo.png", "D:/Qrcode/", "QRCode.png", "png");
		 System.out.println("��ά�����ɳɹ���");
	}

}
