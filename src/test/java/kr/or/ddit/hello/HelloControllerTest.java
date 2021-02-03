package kr.or.ddit.hello;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.config.WebTestConfig;
import kr.or.ddit.user.model.UserVo;


public class HelloControllerTest extends WebTestConfig{

	
	//@Resource(name="helloController")
		//TYPE.  스프링빈 중에 대입 가능한 타입의 스프링 빈을 주입한다
		//만약 동일한 타입의 스프링 빈이 여러개 있을 경우 @Qulifier 어노테이션을 통해
		//특정 스프링 빈의 이름을 지정할 수 있다
		// ==> @ Resource 어노테이션 하나를 사용했을 때와 동일하다.
	
	
	// localhost/hello/view
	@Test
	public void viewTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/hello/view"))
							.andExpect(status().isOk())
							.andExpect(view().name("hello"))
							.andExpect(model().attributeExists("userVo")) // 기대값, 검증과정이 한 라인으로 들어감
							.andDo(print())
							.andReturn(); 
		
		ModelAndView mav = mvcResult.getModelAndView();
		
//		assertEquals("기대값", "실제값");
//		mav.getViewName();
		assertEquals("hello", mav.getViewName());
		UserVo userVo = (UserVo)mav.getModel().get("userVo");
		assertEquals("브라운", userVo.getUsernm());
//		assertEquals("brown", userVo.getUserid());

		
	}
	
	@Test
	public void pathVariableTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/hello/path/cony"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("subpath"))
				.andDo(print())
				.andReturn(); 
	}

}
