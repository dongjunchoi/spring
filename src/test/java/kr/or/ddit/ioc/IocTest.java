package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.service.UserService;

@ContextConfiguration("classpath:/kr/or/ddit/ioc/ioc.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IocTest {
	
	// 객체 이름 변경이 영향?? 1/29 다음주에 다시
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="userService")
	private UserService userService2;
	
	@Resource(name="userServiceCons")
	private UserService userServiceCons;
	
	@Resource(name="userServiceProtoType")
	private UserService userServicePrototype;
	
	@Resource(name="userServiceProtoType")
	private UserService userServicePrototype2;
	
	@Resource(name="dbConfig")
	private DbConfig dbConfig;

	// userServiceCons ������ ���� ���������� �����Ǿ����� �׽�Ʈ
	@Test
	public void userServiceConsTest() {
		
		/***Given***/
		
		/***When***/

		/***Then***/
		assertNotNull(userServiceCons);
	
	}
	
	@Test
	public void beanScopeTest() {
	
		// ������ ������ singleton�������� ���� �ΰ��� ��ü�� �� Ŭ�����κ��� �������Ƿ� �����ؾ���
		// ������ �������� singleton������ bean ������Ʈ�� �������� �ϳ��� ��ü�� �����ȴ�
		assertNotEquals(userService, userServiceCons);
		
	}
	
	@Test
	public void beanScopeTest2() {
		
		// ������ ������ ���� ���Թ޾����Ƿ� userService, userService2�� ���� ��ü��.
		assertEquals(userService, userService2);
	}
	
	@Test
	public void beanScopePrototypeTest2() {
		
		// ������ userServicePrototype ������ ���� ����(scope : prototype)
		assertNotEquals(userServicePrototype, userServicePrototype2);

	}
	@Test
	public void propertyPlaceholderTest() {
		assertNotNull(dbConfig);
		assertEquals("psy", dbConfig.getUsername());
		assertEquals("java", dbConfig.getPassword());
		assertEquals("jdbc:oracle:thin:@localhost:1521:xe", dbConfig.getUrl());
		assertEquals("oracle.jdbc.driver.OracleDriver", dbConfig.getDriverClassName());
	}
}
