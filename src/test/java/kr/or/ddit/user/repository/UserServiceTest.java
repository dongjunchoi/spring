package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

public class UserServiceTest  extends ModelTestConfig{
	
	@Resource(name="userService")
	private UserService userService;
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userService.selectUser(userid);

		/***Then***/
		assertEquals("브라운", userVo.getUsernm());
	}
	
	@Test
	public void selectPagingUserTest() {
		/***Given***/
//		UserServiceI userService = new UserService();
		PageVo pageVo = new PageVo(2, 5);
		
		/***When***/
		Map<String, Object> map = userService.selectPagingUser(pageVo);
		List<UserVo> pageList = (List<UserVo>)map.get("userList");
//		int userCnt =  (int)map.get("userCnt");

		/***Then***/
		assertEquals(5, pageList.size());
//		assertEquals(19, userCnt);
	}
	
	@Test
	public void selectUserNotExsistTest() {
		/***Given***/
		String userid = "brownNotexsist";

		/***When***/
		UserVo user = userService.selectUser(userid);

		/***Then***/
		assertNull(user);
	}
	
	//사용자 수정
	@Test
	public void modifyUserTest() {
		/***Given***/

		//userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("ddit", "대덕인재","dditpass", new Date(), "개발원_m", "대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940","brown.png", "uuid-generated-filename.png");

		/***When***/
		int updateCnt = userService.modifyUser(userVo);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	//사용자 등록
	@Test
	public void registUserTest() {
		/***Given***/

		// userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("ddit_n", "대덕인재", "dditpass", new Date(), "개발원_m", "대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940","brown.png", "uuid-generated-filename.png");

		/*** When ***/
		int insertCnt = userService.registUser(userVo);

		/*** Then ***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteUserTest() {
		/***Given***/
		String userid = "sally13";
		/***When***/
		int deleteCnt = userService.deleteUser(userid);
		/***Then***/
		assertEquals(1, deleteCnt);

	}

}
