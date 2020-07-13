package naver.rlgns1129.bulletinboard.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import naver.rlgns1129.bulletinboard.dao.SpringUserDAO;
import naver.rlgns1129.bulletinboard.domain.SpringUser;
import naver.rlgns1129.bulletinboard.util.CryptoUtil;

@Service
public class SpringUserServiceImpl implements SpringUserService {
	@Autowired
	private SpringUserDAO springUserDao;

	@Override
	public Map<String, Object> join(MultipartHttpServletRequest request, HttpServletResponse response) {
		// 결과를 저장할 객체를 생성
		Map<String, Object> map = new HashMap<String, Object>();
		//성공과 실패 여부를 확인할 데이터 생성
		map.put("result", false);
		//실패했을 때 왜 실패헀는지 이유를 저장하기 위한 데이터 생성
		map.put("emailcheck", false);
		map.put("nicknamecheck", false);
		
		// 파라미터 읽기
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String nickname = request.getParameter("nickname");
		MultipartFile mf = request.getFile("image");
		
		// email 중복 검사 수행
		String emailResult = null;
		//암호화 되어 있기 때문에 전체 이메일을 가져와서
		//복호화 하면서 비교
		List<String> emailList = springUserDao.emailCheck();
		try {
			for (String imsi : emailList) {
				if (CryptoUtil.decryptAES256(
						imsi, "itggangpae").equals(email)) {
					emailResult = email;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// nickname 중복 검사 수행
		String nicknameResult = springUserDao.nicknameCheck(nickname);
		if (nicknameResult == null) {
			map.put("nicknamecheck", true);
		}

		
		//email 중복검사와 nickname 중복 검사를 통과한 경우에만 데이터 삽입
		if(emailResult == null && nicknameResult == null) {
			String image = "default.jpg";
			//파일을 선택한 경우에만 파일을 서버에 복사
			//mf.isEmpty() -> 없을때 true
			if(mf.isEmpty() == false) {
				//저장할 디렉토리 경로를 생성
				String uploadPath = request.getServletContext().getRealPath("/user/profile");
				image = UUID.randomUUID() + mf.getOriginalFilename();
				//실제 저장될 경로 만들기
				uploadPath = uploadPath + "/" + image;
				//파일 객체 생성
				File file = new File(uploadPath);
				FileOutputStream fos = null;
				try {
					//mf의 내용을 file에 복사
					fos = new FileOutputStream(file);
					fos.write(mf.getBytes());
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			
			//공개키 암호화에 사용할 키 생성
			//실무에서 이 키를 데이터베이스에서 불러옵니다.
			String key = "rlgns1129";
			//저장할 데이터 생성
			SpringUser user = new SpringUser();
			try {
				//email 암호화 해서 저장
				user.setEmail(CryptoUtil.encryptAES256(email, key));
				user.setPw(BCrypt.hashpw(pw, BCrypt.gensalt()));
				user.setNickname(nickname);
				user.setImage(image);
				
				//데이터베이스에 저장
				int row = springUserDao.join(user);
				//저장에 성공하면 map의 result에 true 저장
				if(row>0) {
					map.put("result", true);
				}
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		return map;
	}
}
