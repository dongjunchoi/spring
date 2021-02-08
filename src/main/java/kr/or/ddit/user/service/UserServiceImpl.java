package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name="userDao")
	private UserDao userDao;
	
	public UserServiceImpl () {}
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserVo selectUser(String usesrid) {
		
		return userDao.selectUser(usesrid);
		
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<UserVo> selectAllUser() {
		return userDao.selectAllUser();
	}

	@Override
	public Map<String, Object> selectPagingUser(PageVo pageVo) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("pageVo", pageVo);
		
		resultMap.put("userList", userDao.selectPagingUser(pageVo));
//		resultMap.put("userCnt", userDao.selectAllUserCnt());
//		int userCnt = userDao.selectAllUserCnt();
		resultMap.put("pagination", (int)Math.ceil( (double)userDao.selectAllUserCnt() / pageVo.getPageSize()) );

		//		resultMap.put("pagination", (int)Math.ceil( ((Integer)resultMap.get("userCnt")).doubleValue() / pageVo.getPageSize()) );
//		(int)Math.ceil( ((Integer)resultMap.get("userCnt")).doubleValue() / pageVo.getPageSize()) );
//      (int)Math.ceil(  Double.valueOf(resultMap.get("userCnt").toString()) /pageVo.getPageSize() )
		return resultMap;
	}

	@Override
	public int modifyUser(UserVo userVo) {
		return userDao.modifyUser(userVo);
	}

	@Override
	public int registUser(UserVo userVo) {
		return userDao.registUser(userVo);
//		
		//예외발생
//		userDao.registUser(userVo);
//		userDao.registUser(userVo);
//		return 1;
	}

	@Override
	public int deleteUser(String userid) {
		return userDao.deleteUser(userid);
	}

}
