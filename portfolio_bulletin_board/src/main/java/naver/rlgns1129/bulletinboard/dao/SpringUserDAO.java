package naver.rlgns1129.bulletinboard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.rlgns1129.bulletinboard.domain.SpringUser;

@Repository
public class SpringUserDAO {
	@Autowired
	private SqlSession sqlSession;
	
	//이메일 중복 체크 메소드
	public List<String> emailCheck() {
		return sqlSession.selectList("springuser.emailcheck");
	}
	
	//닉네임 중복 체크 메소드
	public String nicknameCheck(String nickname) {
		return sqlSession.selectOne("springuser.nicknamecheck", nickname);
	}
	
	public int join(SpringUser springUser) {
		return sqlSession.insert("springuser.join", springUser);
	}
}
