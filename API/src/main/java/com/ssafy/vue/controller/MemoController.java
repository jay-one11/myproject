package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.MemoDto;
import com.ssafy.vue.model.service.BoardService;
import com.ssafy.vue.model.service.MemoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/vue/swagger-ui.html
//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/memo")
@Api("댓글 컨트롤러  API V1")
public class MemoController {

	private static final Logger logger = LoggerFactory.getLogger(MemoController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private MemoService memoService;

	@ApiOperation(value = "메모작성", notes = "메모 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeMemo(@RequestBody @ApiParam(value = "메모 정보.", required = true) MemoDto memoDto) throws Exception {
		logger.info("writeMemo - 호출");
		if (memoService.writeMemo(memoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "메모 호출", notes = "메모 정보를 호출한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = BoardDto.class)
	@GetMapping("/{articelno}")
	public ResponseEntity<List<MemoDto>> getMemo(@PathVariable("articelno") @ApiParam(value = ".", required = true) int articelno) throws Exception {
		logger.info("getMemo - 호출: " + articelno);
		return new ResponseEntity<List<MemoDto>>(memoService.getMemo(articelno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "메모 삭제", notes = "메모 정보를 호출한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{memono}")
	public ResponseEntity<String> deleteMemo(@PathVariable("memono") @ApiParam(value = "수정할 글 정보", required = true) int memono) throws Exception {
		logger.info("deleteMemo - 호출" + memono);
		if (memoService.deleteMemo(memono)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "메모 수정", notes = "메모 정보를 수정한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyMemo(@RequestBody @ApiParam(value = "�닔�젙�븷 湲��젙蹂�.", required = true) MemoDto memoDto) throws Exception {
		logger.info("modifyMemo - 호출");
		
		if (memoService.modifyMemo(memoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
}