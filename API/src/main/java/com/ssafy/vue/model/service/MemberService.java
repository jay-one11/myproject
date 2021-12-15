package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.MemberParameterDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public int registerMember(MemberDto memberDto) throws Exception;
	public int modifyMember(MemberDto memberDto) throws Exception;
	public int deleteMember(String id) throws Exception;
	public List<MemberDto> listMember(MemberParameterDto memberParameterDto) throws Exception;

}
