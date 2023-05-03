package com.ssafy.traveler.tour.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TourInterestDto {

    private int contentId;
    private int interestCount;

}
