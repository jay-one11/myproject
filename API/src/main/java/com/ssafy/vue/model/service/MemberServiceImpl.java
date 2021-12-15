package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.MemberParameterDto;
import com.ssafy.vue.model.mapper.BoardMapper;
import com.ssafy.vue.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(userid);
	}

	@Override
	public int registerMember(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).registerMember(memberDto);
	}

	@Override
	public int modifyMember(MemberDto memberDto) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MemberMapper.class).modifyMember(memberDto);
	}

	@Override
	public int deleteMember(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MemberMapper.class).deleteMember(id);
	}

	@Override
	public List<MemberDto> listMember(MemberParameterDto memberParameterDto) throws Exception {
		int start = memberParameterDto.getPg() == 0 ? 0 : (memberParameterDto.getPg() - 1) * memberParameterDto.getSpp();
		memberParameterDto.setStart(start);
		return sqlSession.getMapper(MemberMapper.class).listMember(memberParameterDto);
	}

}
