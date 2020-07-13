package naver.rlgns1129.bulletinboard;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import naver.rlgns1129.bulletinboard.service.SpringUserService;

@RestController
public class SpringUserRestController {
	@Autowired
	private SpringUserService springUserService;
	
	//회원가입을 처리하는 메소드
	@RequestMapping(value="user/join", method=RequestMethod.POST)
	public Map<String, Object> join(MultipartHttpServletRequest request, HttpServletResponse response){
		
		//서비스의 메소드를 호출
		Map<String, Object> map = springUserService.join(request, response);
		return map;
	}
}
