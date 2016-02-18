package br.com.fatec.model;

public class User {
	
	private int userCode;
	private String userName;
	private String password;
	private int situation;
	private int verification;
	private String kindPerson;
	private String token;
	
	public User(){};	
	
	public User(int userCode, String userName, String password, int situation, int verification, String kindPerson,
			String token) {
		
		this.userCode = userCode;
		this.userName = userName;
		this.password = password;
		this.situation = situation;
		this.verification = verification;
		this.kindPerson = kindPerson;
		this.token = token;
	}



	public int getUserCode() {
		return userCode;
	}


	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getSituation() {
		return situation;
	}

	public void setSituation(int situation) {
		this.situation = situation;
	}

	public int getVerification() {
		return verification;
	}

	public void setVerification(int verification) {
		this.verification = verification;
	}


	public String getKindPerson() {
		return kindPerson;
	}

	public void setKindPerson(String kindPerson) {
		this.kindPerson = kindPerson;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}
}
