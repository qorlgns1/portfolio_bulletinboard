package naver.rlgns1129.bulletinboard.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface SpringUserService {
	//메소드의 원형(모양)은
	//public Map<String, Object> 이름
	//(HttpServletRequest request, HttpServletResponse response)
	//request를 이용해서 클라이언트의 정보를 확인할 수 있고
	//response를 이용해서 클라이언트에 출력을 할 수 있습니다.
	//파일이 업로드 되는 경우에는 HttpServletRequest 대신에
	//MultipartHttpServletRequest를 사용하면 됩니다.
	
	//회원가입을 처리해주는 메소드
	public Map<String, Object> join(MultipartHttpServletRequest request, HttpServletResponse response);
	
}
