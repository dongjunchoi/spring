package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.config.ComponentScanJavaConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserService;

@ContextConfiguration(classes = {ComponentScanJavaConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ComponentScanJavaTest {
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="userService")
	private UserService userService;
	//@Repository������̼��� ������ userDaoImpl������ ���� ���������� �����̳ʿ� ��� �Ǿ����� Ȯ��
		
	@Test
	public void userDaoImplSpringBeanTest() {
		assertNotNull(userDao);
		
		UserVo userVo = userDao.selectUser("brown");
		assertEquals("브라운", userVo.getUsernm());
	}
	
	// userServiceImpl ������ ���� ���������� �����̳ʿ� ��ϵǾ����� Ȯ��
	@Test
	public void userServiceImplSpringBeanTest() {
		assertNotNull(userService);
		
		UserVo userVo = userService.selectUser("brown");
		assertEquals("브라운", userVo.getUsernm());
	}
}
