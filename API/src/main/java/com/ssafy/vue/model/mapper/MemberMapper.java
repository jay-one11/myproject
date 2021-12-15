package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.MemberParameterDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public int registerMember(MemberDto memberDto) throws SQLException;
	public int modifyMember(MemberDto memberDto) throws SQLException;
	public int deleteMember(String id) throws SQLException;
	public List<MemberDto> listMember(MemberParameterDto memberParameterDto) throws SQLException;
}
