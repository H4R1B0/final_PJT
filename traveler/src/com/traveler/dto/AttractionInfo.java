package com.traveler.dto;

public class AttractionInfo {

	private String content_id;
	private String title;
	private String addr;
	private String img1;
	private String img2;
	private double latitude;
	private double longitude;
	
	public AttractionInfo() {}

	//조회용 생성자
	public AttractionInfo(String content_id, String title, String addr, String img1, String img2) {
		super();
		this.content_id = content_id;
		this.title = title;
		this.addr = addr;
		this.img1 = img1;
		this.img2 = img2;
	}
	
	//지도용 생성자
	public AttractionInfo(String content_id, String title, String addr, String img1, String img2, double latitude,
			double longitude) {
		super();
		this.content_id = content_id;
		this.title = title;
		this.addr = addr;
		this.img1 = img1;
		this.img2 = img2;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	

	public String getcontent_id() {
		return content_id;
	}

	public void setcontent_id(String content_id) {
		this.content_id = content_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "AttractionInfo [content_id=" + content_id + ", title=" + title + ", addr=" + addr + ", img1=" + img1
				+ ", img2=" + img2 + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
