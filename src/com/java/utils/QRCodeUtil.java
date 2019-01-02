package com.java.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.java.entity.QRCodeParams;
import com.java.exception.QRParamsException;

public class QRCodeUtil {
	private static int width = 300; // ��ά����
	private static int height = 300; // ��ά��߶�
	private static int onColor = 0xFF000000; // ǰ��ɫ
	private static int offColor = 0xFFFFFFFF; // ����ɫ
	private static int margin = 1; // �ױߴ�С��ȡֵ��Χ0~4
	private static ErrorCorrectionLevel level = ErrorCorrectionLevel.L; // ��ά���ݴ���

	/**
	 * ���ɶ�ά��
	 * 
	 * @param params
	 *            QRCodeParams���ԣ�txt��ImgName��ImgPath����Ϊ�գ�
	 * @throws QRParamsException
	 */
	public static void generateQRImage(QRCodeParams params) throws QRParamsException {
		if (params == null || params.getImgName() == null || params.getImgPath() == null
				|| params.getContext() == null) {

			throw new QRParamsException("��������");
		}
		try {
			initData(params);

			String imgPath = params.getImgPath();
			String imgName = params.getImgName();
			String txt = params.getContext();

			if (params.getLogoPath() != null && !"".equals(params.getLogoPath().trim())) {
				generateQRImage(txt, params.getLogoPath(), imgPath, imgName, params.getSuffixName());
			} else {
				generateQRImage(txt, imgPath, imgName, params.getSuffixName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * ���ɶ�ά��
	 * 
	 * @param txt
	 *            //��ά������
	 * @param imgPath
	 *            //��ά�뱣������·��
	 * @param imgName
	 *            //��ά���ļ�����
	 * @param suffix
	 *            //ͼƬ��׺��
	 */
	public static void generateQRImage(String txt, String imgPath, String imgName, String suffix) {

		File filePath = new File(imgPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		File imageFile = new File(imgPath, imgName);
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// ָ������ȼ�
		hints.put(EncodeHintType.ERROR_CORRECTION, level);
		// ָ�������ʽ
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, margin); // ���ðױ�
		try {
			MatrixToImageConfig config = new MatrixToImageConfig(onColor, offColor);
			BitMatrix bitMatrix = new MultiFormatWriter().encode(txt, BarcodeFormat.QR_CODE, width, height, hints);
			MatrixToImageWriter.writeToPath(bitMatrix, suffix, imageFile.toPath(), config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���ɴ�logo�Ķ�ά��ͼƬ
	 * 
	 * @param txt
	 *            //��ά������
	 * @param logoPath
	 *            //logo��������·��
	 * @param imgPath
	 *            //��ά�뱣���������·��
	 * @param imgName
	 *            //��ά���ļ�����
	 * @param suffix
	 *            //ͼƬ��׺��
	 * @throws Exception
	 */
	public static void generateQRImage(String txt, String logoPath, String imgPath, String imgName, String suffix)
			throws Exception {

		File filePath = new File(imgPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		if (imgPath.endsWith("/")) {
			imgPath += imgName;
		} else {
			imgPath += "/" + imgName;
		}

		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, level);
		hints.put(EncodeHintType.MARGIN, margin); // ���ðױ�
		BitMatrix bitMatrix = new MultiFormatWriter().encode(txt, BarcodeFormat.QR_CODE, width, height, hints);
		File qrcodeFile = new File(imgPath);
		writeToFile(bitMatrix, suffix, qrcodeFile, logoPath);
	}

	/**
	 * 
	 * @param matrix
	 *            ��ά��������
	 * @param format
	 *            ��ά��ͼƬ��ʽ
	 * @param file
	 *            ��ά��ͼƬ�ļ�
	 * @param logoPath
	 *            logo·��
	 * @throws IOException
	 */
	public static void writeToFile(BitMatrix matrix, String format, File file, String logoPath) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		Graphics2D gs = image.createGraphics();

		int ratioWidth = image.getWidth() * 2 / 10;
		int ratioHeight = image.getHeight() * 2 / 10;
		// ����logo
		Image img = ImageIO.read(new File(logoPath));
		int logoWidth = img.getWidth(null) > ratioWidth ? ratioWidth : img.getWidth(null);
		int logoHeight = img.getHeight(null) > ratioHeight ? ratioHeight : img.getHeight(null);

		int x = (image.getWidth() - logoWidth) / 2;
		int y = (image.getHeight() - logoHeight) / 2;

		gs.drawImage(img, x, y, logoWidth, logoHeight, null);
		gs.setColor(Color.black);
		gs.setBackground(Color.WHITE);
		gs.dispose();
		img.flush();
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
		}
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? onColor : offColor);
			}
		}
		return image;
	}

	public static BitMatrix deleteWhite(BitMatrix matrix) {
		int[] rec = matrix.getEnclosingRectangle();
		int resWidth = rec[2] + 1;
		int resHeight = rec[3] + 1;

		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
		resMatrix.clear();
		for (int i = 0; i < resWidth; i++) {
			for (int j = 0; j < resHeight; j++) {
				if (matrix.get(i + rec[0], j + rec[1]))
					resMatrix.set(i, j);
			}
		}
		return resMatrix;
	}

	private static void initData(QRCodeParams params) {
		if (params.getWidth() != null) {
			width = params.getWidth();
		}
		if (params.getHeight() != null) {
			height = params.getHeight();
		}
		if (params.getOnColor() != null) {
			onColor = params.getOnColor();
		}
		if (params.getOffColor() != null) {
			offColor = params.getOffColor();
		}
		if (params.getLevel() != null) {
			level = params.getLevel();
		}

	}
}
