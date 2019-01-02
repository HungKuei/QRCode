package com.java.entity;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeParams {
	// 二维码内容
	private String context;
	// 二维码网络路径
	private String qrCodeUrl;
	// 二维码保存路径
	private String imgPath;
	// 二维码生成图片名称
	private String imgName;
	// 二维码中间logo图片
	private String logoPath;
	// 二维码宽度
	private Integer width = 400;
	// 二维码高度
	private Integer height = 400;
	// 前景色
	private Integer onColor = 0xFF000000;
	// 背景色
	private Integer offColor = 0xFFFFFFFF;
	// 二维码白边的大小（取值1~4）
	private Integer margin = 1;
	// 二维码容错率
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
	 * 返回图片后缀名
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
