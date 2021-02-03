package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.util.FileUtil;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.validator.UserVoValidator;

@Controller
@RequestMapping("user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "userService")
	private UserService userService;
	
	//전체유저
	@RequestMapping("allUser")
	private String allUser(Model model) {
		model.addAttribute("userList", userService.selectAllUser());
		return "user/allUser";
				
	}
	
	//상세조회
	@RequestMapping("detailUser")
	private String detailUser(Model model, String userid) {
		model.addAttribute("user",userService.selectUser(userid));
		return "user/user";
	}
	
	//사용자 정보 수정 페이지
	@RequestMapping(path="userModify", method={RequestMethod.GET})
	private String userModify(Model model, String userid) {
		model.addAttribute("user",userService.selectUser(userid));
		return "user/userModify";
	}
	
	//사용자 정보 수정
	@RequestMapping(path="userModify", method=RequestMethod.POST)
	public String userModify(UserVo userVo, Model model, RedirectAttributes ra, MultipartFile profile) {
		
		if("".equals(profile.getOriginalFilename())) {
			userVo.setFilename(userVo.getFilename());
			userVo.setRealfilename(userVo.getRealfilename());
			
			if(userVo.getFilename()==null) {
				userVo.setFilename("");
			}
			if(userVo.getRealfilename()==null) {
				userVo.setRealfilename("");
			}
			
		}else {
			try {
				userVo.setFilename(profile.getOriginalFilename());
				String fileExtension = FileUtil.getFileExtension(profile.getOriginalFilename());
				String realFileName = UUID.randomUUID().toString()+fileExtension;
				userVo.setRealfilename(realFileName);
				profile.transferTo(new File("D:/upload/"+realFileName));
			} catch (IllegalStateException | IOException e1) {
				e1.printStackTrace();
			}
		}
		
		int updateCnt = 0;
		try {
			updateCnt = userService.modifyUser(userVo);
		} catch (Exception e) {
			updateCnt =0;
		}
		// 사용자 수정이 정상적으로 된 경우 ==> 해당 사용자의 상세 조회 페이지로 이동
		if(updateCnt == 1) {
			ra.addAttribute("userid", userVo.getUserid());
			return "redirect:/user/detailUser";
//			resp.sendRedirect(req.getContextPath()+"/user?userid=" + userid);
		}
		// 사용자 수정이 비정상적으로 된 경우 ==> 해당 사용자의 정보 수정 페이지로 이동
		else {
			model.addAttribute("user", userVo);
			return "user/userModify";
		}
		
	}
	
	// 사용자 등록 페이지
	@RequestMapping(path="registUser", method={RequestMethod.GET})
	private String regisgUser() {
		return "user/registUser";
	}
	
	//사용자 등록
	//bindingResult 객체는 command 객체 바로 뒤에 인자로 기술해야 한다.
	@RequestMapping(path="registUser", method={RequestMethod.POST})
	public String registUser(@Valid UserVo userVo, BindingResult result ,RedirectAttributes ra, Model model, MultipartFile profile) {
		
//		new UserVoValidator().validate(userVo, result);
		
		if(result.hasErrors()) {
			logger.debug("result has error");
			return "user/registUser";
		}
		
		if("".equals(profile.getOriginalFilename())) {
			userVo.setFilename("");
			userVo.setRealfilename("");
		}else {
			try {
				userVo.setFilename(profile.getOriginalFilename());
				String fileExtension = FileUtil.getFileExtension(profile.getOriginalFilename());
				String realFileName = UUID.randomUUID().toString()+fileExtension;
				userVo.setRealfilename(realFileName);
				profile.transferTo(new File("D:/upload/"+realFileName));
			} catch (IllegalStateException | IOException e1) {
				e1.printStackTrace();
			}
		}
		
		int insertCnt = 0;
		try {
			insertCnt = userService.registUser(userVo);
		} catch (Exception e) {
			insertCnt = 0;
			e.printStackTrace();
		}
		
		if(insertCnt == 1 ) {
			return "redirect:/user/pagingUser";
		}else {
			model.addAttribute("insert", userVo);
			return "user/registUser";
		}
	}
	
	//사용자 삭제
	@RequestMapping("deleteUser")
	public String deleteUser(Model model,String userid) {
		model.addAttribute(userService.deleteUser(userid));
		return "redirect:/user/pagingUser";
	}
	
	
//	@RequestMapping("pagingUser")
//	public String pagingUser(@RequestParam(defaultValue = "1") int page,
//							@RequestParam(defaultValue = "5") int pageSize
//							,@RequestParam(name="p") int price){
//		logger.debug("page : {}, pageSize : {}, price : {}",  page, pageSize, price);
//		
//		PageVo pageVo = new PageVo(page, pageSize);
//		
//		return "";
//	}	
	
	//페이징 처리
	@RequestMapping("pagingUser")
	public String pagingUser(@RequestParam(defaultValue = "1") int page,
							@RequestParam(defaultValue = "5") int pageSize,
							Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		model.addAllAttributes(userService.selectPagingUser(pageVo));

		return "user/pagingUser";
		
//		Map<String, Object> map = (Map<String, Object>) userService.selectPagingUser(pageVo);
//		List<UserVo> pagingList = (List<UserVo>) map.get("userList");

		
//		int userCnt = (int)map.get("userCnt");
//		int pagination = (int) Math.ceil((double) userCnt / pageSize);
//		
//		model.addAttribute("pagingList", pagingList);
//		model.addAttribute("pagination", pagination);
//		model.addAttribute("pageVo",pageVo);
		
//		return "user/pagingUser";
	}	
	
	
//	@RequestMapping("pagingUser")
	public String pagingUser(PageVo pageVo) {

		logger.debug("pageVo : {}", pageVo);
		
		return "";
	}
	
	
	@RequestMapping("excelDownload")
	public String excelDownload(Model model) {
		List<String> header = new ArrayList<String>();
		
		header.add("사용자 아이디");
		header.add("사용자 이름");
		header.add("사용자 별명");
		
		model.addAttribute("header", header);
		model.addAttribute("data", userService.selectAllUser());
		
		return "userExcelDownloadView";
	}
	
	
//----------------------Tiles 방식------------------------------------------------------
	//페이징
	@RequestMapping("pagingUserTiles")
	public String pagingUserTiles(@RequestParam(defaultValue = "1") int page,
							 	  @RequestParam(defaultValue = "5") int pageSize,
							 	  Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		
		//tiles-definition에 설정한 name
		return "tiles.user.pagingUser";
	}
	
	//전체유저
	@RequestMapping("allUserTiles")
	private String allUserTiles(Model model) {
		model.addAttribute("userList", userService.selectAllUser());
		return "tiles.user.allUser";
				
	}
	
	// 사용자 등록 페이지
	@RequestMapping(path="registUserTiles", method={RequestMethod.GET})
	private String regisgUserTiles() {
		return "tiles.user.registUser";
	}
	
	//사용자 등록
	//bindingResult 객체는 command 객체 바로 뒤에 인자로 기술해야 한다.
	@RequestMapping(path="registUserTiles", method={RequestMethod.POST})
	public String registUserTiles(@Valid UserVo userVo, BindingResult result ,RedirectAttributes ra, Model model, MultipartFile profile) {
			
//			new UserVoValidator().validate(userVo, result);
			
		if(result.hasErrors()) {
			logger.debug("result has error");
			return "tiles.user.registUser";
		}
			
		if("".equals(profile.getOriginalFilename())) {
			userVo.setFilename("");
			userVo.setRealfilename("");
		}else {
			try {
				userVo.setFilename(profile.getOriginalFilename());
				String fileExtension = FileUtil.getFileExtension(profile.getOriginalFilename());
				String realFileName = UUID.randomUUID().toString()+fileExtension;
				userVo.setRealfilename(realFileName);
				profile.transferTo(new File("D:/upload/"+realFileName));
			} catch (IllegalStateException | IOException e1) {
				e1.printStackTrace();
			}
		}
			
		int insertCnt = 0;
		try {
			insertCnt = userService.registUser(userVo);
		} catch (Exception e) {
			insertCnt = 0;
			e.printStackTrace();
		}
			
		if(insertCnt == 1 ) {
			return "redirect:/user/pagingUserTiles";
		}else {
			model.addAttribute("insert", userVo);
			return "tiles.user.registUser";
		}
	}
	//상세조회
	@RequestMapping("detailUserTiles")
	private String detailUserTiles(Model model, String userid) {
		model.addAttribute("user",userService.selectUser(userid));
		return "tiles.user.user";
	}
	
	
	//사용자 삭제
	@RequestMapping("deleteUserTiles")
	public String deleteUserTiles(Model model,String userid) {
		model.addAttribute(userService.deleteUser(userid));
		return "redirect:/user/pagingUser";
	}
	
	// localhost/user/profile
	@RequestMapping("profile")
	public void profile(HttpServletResponse resp, String userid, HttpServletRequest req) {
		resp.setContentType("image");
		
		// userid 파라미터를 이용하여 userService 객체를 통해 사용자의 사잔 파일 이름을 획득
		// 파일 입출력을 통해 사진을 읽어들여 resp객체의 outputStream으로 응답 생성
		
		UserVo userVo = userService.selectUser(userid);
		
		String path = "";
		if(userVo.getRealfilename() == null) {
			//	/webapp/image 경로..
			path = req.getServletContext().getRealPath("/image/unknown.png");
		}else {
			path = userVo.getRealfilename();
		}
		
		logger.debug("path : {}", path);
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			
			byte[] buff = new byte[512];
			
			while(fis.read(buff) != -1) {
				sos.write(buff);
			}
			
			fis.close();
			sos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//profileDownload
	@RequestMapping("profileDownload")
	public void profileDownload(String userid, HttpServletRequest req, HttpServletResponse resp) {
		
		UserVo userVo = userService.selectUser(userid);

		String path = "";
		String filename = "";
		if(userVo.getRealfilename() == null) {
			//	/webapp/image 경로..
			path = req.getServletContext().getRealPath("/image/unknown.png");
			filename = "unknown.png";
		}else {
			path = userVo.getRealfilename();
			filename = userVo.getFilename();
		}
		
		resp.setHeader("Content-Disposition", "attacment; filename=" + filename);
		
		logger.debug("path : {}", path);
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			
			byte[] buff = new byte[512];
			
			while(fis.read(buff) != -1) {
				sos.write(buff);
			}
			
			fis.close();
			sos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
