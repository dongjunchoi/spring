package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;


public interface UserService {
	
	UserVo selectUser(String usesrid);
	//반환타입 메소드명();
	List<UserVo> selectAllUser();
	//사용자 페이징 리스트
//	List<UserVo> selectPagingUser(PageVo pageVo);
		
	//사용자 페이징 조회
	Map<String, Object> selectPagingUser(PageVo pageVo);
		
	//사용자 정보 수정(update된 건수를 확인)
	int modifyUser(UserVo userVo);
	
	//사용자 정보 등록
	int registUser(UserVo userVo);
		
	//사용자 삭제
	int deleteUser(String userid);
	
}
