package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.MemoDto;

public interface MemoService {
	public boolean writeMemo(MemoDto memoDto) throws Exception;
	public List<MemoDto> getMemo(int articleno) throws Exception;
	public boolean deleteMemo(int memono) throws Exception;
	public boolean modifyMemo(MemoDto memoDto) throws Exception;
}
