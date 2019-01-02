package com.java.main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.java.utils.ImageWrite;

public class QRcodeEncode {
	public static void main(String[] args) throws Exception {
		try {
			// ��ά������
			String content = "Hello QRcode!";
			// ��ά������·��
			String path = "D:/Qrcode/";
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			Map hints = new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
			File imgFile = new File(path, "rcode.jpg");
			ImageWrite.writeToFile(bitMatrix, "jpg", imgFile);
			System.out.println("��ά�������ɣ�");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}
}
