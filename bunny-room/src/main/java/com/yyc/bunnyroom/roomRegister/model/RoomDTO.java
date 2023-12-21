package com.yyc.bunnyroom.roomRegister.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RoomDTO {

//    방 번호	ROOM_NO		INTEGER
//    사업체 번호	BUSINESS_NO		INTEGER
//    방 이름	ROOM_NAME		VARCHAR
//    한 줄 소개	ROOM_INTRO		VARCHAR
//    상세 소개	ROOM_DETAIL_INTRO		VARCHAR
//    방 상태	ROOM_AVAILABLE_STATUS		VARCHAR
//    최소 수용 인원	ROOM_MIN_PEOPLE		INTEGER
//    최대 수용 인원	ROOM_MAX_PEOPLE		INTEGER
//    시설 안내	ROOM_FACILITY_INFO		VARCHAR
//    유의 사항	ROOM_NOTICE		VARCHAR
//    등록일	ROOM_REGIST_DATE		TIMESTAMP
//    수정일	ROOM_UPDATE_DATE		TIMESTAMP
//    상태	ROOM_STATUS		VARCHAR


    private int roomNo;
    private int businessNo;
    private String roomName;
    private String roomIntro;
    private String roomDetailInfo;
    private String roomAvailableStatus;
    private int roomMinPeople;
    private int roomMaxPeople;
    private String roomFacilityInfo;
    private String roomNotice;
    private ZonedDateTime roomRegistDate;
    private ZonedDateTime getRoomUpdateDate;
    private String roomStatus;

}
