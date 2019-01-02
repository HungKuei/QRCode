package com.java.entity;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeParams {
	// ��ά������
	private String context;
	// ��ά������·��
	private String qrCodeUrl;
	// ��ά�뱣��·��
	private String imgPath;
	// ��ά������ͼƬ����
	private String imgName;
	// ��ά���м�logoͼƬ
	private String logoPath;
	// ��ά����
	private Integer width = 400;
	// ��ά��߶�
	private Integer height = 400;
	// ǰ��ɫ
	private Integer onColor = 0xFF000000;
	// ����ɫ
	private Integer offColor = 0xFFFFFFFF;
	// ��ά��ױߵĴ�С��ȡֵ1~4��
	private Integer margin = 1;
	// ��ά���ݴ���
	private ErrorCorrectionLevel level = ErrorCorrectionLevel.L;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getQrCodeUrl() {
		return qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getOnColor() {
		return onColor;
	}

	public void setOnColor(Integer onColor) {
		this.onColor = onColor;
	}

	public Integer getOffColor() {
		return offColor;
	}

	public void setOffColor(Integer offColor) {
		this.offColor = offColor;
	}

	public Integer getMargin() {
		return margin;
	}

	public void setMargin(Integer margin) {
		this.margin = margin;
	}

	public ErrorCorrectionLevel getLevel() {
		return level;
	}

	public void setLevel(ErrorCorrectionLevel level) {
		this.level = level;
	}

	/**
	 * ����ͼƬ��׺��
	 * 
	 * @return
	 */
	public String getSuffixName() {
		String sufName = this.getImgName();
		if (sufName != null && !"".equals(sufName)) {
			String suffix = imgName.substring(imgName.lastIndexOf(".") + 1);
			return suffix;
		}
		return "";
	}

}
