package naver.rlgns1129.bulletinboard;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import naver.rlgns1129.bulletinboard.domain.SpringUser;

//Spring에서 JUnit4 라이브러리를 사용하기 위한 설정
@RunWith(SpringJUnit4ClassRunner.class)
//locations에 설정된 파일들을 실행시켜서 메모리에 로드하기 위한 설정
//프레임워크마다 설정 파일의 경로가 다르므로 web.xml에 설정된 애용을 확인하고 작성
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class PortfolioTest {
	//데이터베이스 연결을 확인할 때 주입
	@Autowired
	private DataSource dataSource;
	
	//연결  테스트를 위한 메소드
	@Test
	public void ConnectTest() {
		try {
			System.out.println(dataSource.getConnection());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void emailckeck1() {
		//email 중복검사
		System.out.println(sqlSession.selectOne("springuser.emailcheck", "rlgns1129@naver.com"));
		System.out.println(sqlSession.selectOne("springuser.emailcheck", "rlgns0610@naver.com"));
	}
	
	@Test
	public void nicknamecheck1() {
		//nickname 중복검사
		System.out.println(sqlSession.selectOne("springuser.nicknamecheck", "배기훈짱"));
		System.out.println(sqlSession.selectOne("springuser.nicknamecheck", "배기훈안짱"));
	}
	
	@Test
	public void registertest1() {
		//데이터 삽입 확인
		
		SpringUser user = new SpringUser();
		user.setEmail("inserttest@naver.com");
		user.setPw("1234");
		user.setNickname("insert테스트");
		user.setImage("sample.png");
		
		System.out.println(sqlSession.insert("springuser.join", user));
	}
}
