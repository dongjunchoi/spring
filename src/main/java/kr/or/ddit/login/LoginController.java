package kr.or.ddit.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@Controller
@RequestMapping("login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
//	@RequestMapping(path="view", method={RequestMethod.GET}, params = {"userid=brown"},... )
	@RequestMapping(path="view", method={RequestMethod.GET} )
	public String view() {
		logger.debug("loginController.view()");
		return "login";
	}
	
//	String userid = req.getParameter("userid");
//	String pass = req.getParameter("pass");
//	@RequestMapping("process")
	//3-1(파라미터가 몇개 없어서 value object를 사용하지 않는 경우	스프링 컨트롤러 메소드에 파라미터와 동이한 이름의 인자를 선언
	//		스프링 mvc 모튤에서 request 객체의 파라미터를 메소드 인자로 자동 바인딩)
	public String process(String userid, String pass, int price) {
		logger.debug("userid : {}", userid);
		logger.debug("pass : {}", pass);
		logger.debug("price : {}", price);
		
		return "";
	}
	
	//post 메소드만 처리하도록 설정 (method => 배열형태이지만 한개만 받을 경우는 {} 사용 안해도 상관없다)
	//					method={RequestMethod.POST} ==> method=RequestMethod.POST
	//3-2
	@RequestMapping(path="process", method=RequestMethod.POST)
	public String process(UserVo userVo, HttpSession session, RedirectAttributes ra) {
		logger.debug("userVo : {}", userVo);
		
		UserVo dbUser = userService.selectUser(userVo.getUserid());
		
		//로그인 성공
		if(dbUser != null && userVo.getPass().equals(dbUser.getPass())) {
			session.setAttribute("S_USER", dbUser);
			return "main";
		}else {	//로그인 실패
			// 내부적으로 session을 사용하여 속성을 저장
			// 리다이렉트 처리가 완료 되면 스프링 프레임워크에서 자동으로 session에서 제거
			ra.addFlashAttribute("msg", "잘못된 사용자 정보입니다.");
//			session.setAttribute("msg", "잘못된 사용자 정보입니다.");
			
			//일반 속성을 추가한 경우 : addAttribute
			//리다이렉트 페이지의 파라미터로 전달된다.
			ra.addAttribute("userid", "brown");
			
			
			return "redirect:/login/view";
		}
		
	}
	
	
}
