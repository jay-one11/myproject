package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.HospitalDto;
import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.StarbucksDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<StarbucksDto> getStarbucks(String dong) throws Exception;
	List<HospitalDto> corona(String gugun) throws Exception;
}
