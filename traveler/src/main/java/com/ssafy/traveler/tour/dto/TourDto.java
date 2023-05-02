package com.ssafy.traveler.tour.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TourDto {

	private int contentId;
	private int contentTypeId;
	private String title;
	private String addr;
	private String zipcode;
	private String img;
	private int readcount;
	private int sidoCode;
	private int gugunCode;
	private double latitude;
	private double longitude;

}
