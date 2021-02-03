package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

public class UserDaoTest extends ModelTestConfig{
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Test
	public void getUsertest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userDao.selectUser(userid);

		/***Then***/
		assertEquals("브라운", userVo.getUsernm());
	}
	
	@Test
	public void selectPagingUserTest() {
		/***Given***/
		PageVo pageVo = new PageVo(2, 5);
		
		/***When***/
		List<UserVo> pageList = userDao.selectPagingUser(pageVo);

		/***Then***/
		assertEquals(5, pageList.size());
	}
	
	@Test
	public void selectAllUserCntTest() {
		/***Given***/
		
		/***When***/
		List<UserVo> userList = userDao.selectAllUser();

		/***Then***/
		assertEquals(19, userList.size());
	}
	
	@Test
	public void modifyUserTest() {
		/***Given***/
		
		UserVo userVo = new UserVo("ddit", "대덕인재","dditpass", new Date(), "개발원_m", "대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940","brown.png", "uuid-generated-filename.png");

		/***When***/
		int updateCnt = userDao.modifyUser(userVo);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void registUserTest() {
		/***Given***/
		UserVo userVo = new UserVo("ddit", "대덕인재","dditpass", new Date(), "개발원_m", "대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940","brown.png", "uuid-generated-filename.png");

		/***When***/
		int insertCnt = userDao.registUser(userVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteUserTest() {
		/***Given***/
		String userid = "test";
		
		/***When***/
		int deleteCnt = userDao.deleteUser(userid);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	
	}
	
	

}
