package naver.rlgns1129.bulletinboard.domain;

import java.util.Date;

public class SpringUser {
	private String email;
	private String pw;
	private String nickname; 
	private String image;
	private Date regdate;
	private Date logindate;
	private int emailauth;
	
	public SpringUser() {
		super();
	}
	
	public SpringUser(String email, String pw, String nickname, String image, Date regdate, Date logindate,
			int emailauth) {
		super();
		this.email = email;
		this.pw = pw;
		this.nickname = nickname;
		this.image = image;
		this.regdate = regdate;
		this.logindate = logindate;
		this.emailauth = emailauth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getLogindate() {
		return logindate;
	}
	public void setLogindate(Date logindate) {
		this.logindate = logindate;
	}
	public int getEmailauth() {
		return emailauth;
	}
	public void setEmailauth(int emailauth) {
		this.emailauth = emailauth;
	}
	
	@Override
	public String toString() {
		return "SpringUser [email=" + email + ", pw=" + pw + ", nickname=" + nickname + ", image=" + image
				+ ", regdate=" + regdate + ", logindate=" + logindate + ", emailauth=" + emailauth + "]";
	}
	
	
}