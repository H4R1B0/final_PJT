package com.ssafy.traveler.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberInterestDto {

    private String memberId;
    private int contentId;

}
