package com.ssafy.traveler.board.info.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class InfoDto {

    private int no;
    private String title;
    private String writer;
    private String writeDate;
    private int readCount;
    private String content;

}
