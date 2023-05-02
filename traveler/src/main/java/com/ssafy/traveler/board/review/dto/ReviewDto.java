package com.ssafy.traveler.board.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDto {

    private int no;
    private String title;
    private String writer;
    private String writeDate;
    private String content;
    private int readCount;

}
