package br.com.fatec.entity;

import org.joda.time.DateTime;

public class TokenInfo {
	private String userId;
    private DateTime issued;
    private DateTime expires;
    
    public DateTime getIssued() {
        return issued;
    }
    public void setIssued(DateTime issued) {
        this.issued = issued;
    }
    public DateTime getExpires() {
        return expires;
    }
    public void setExpires(DateTime expires) {
        this.expires = expires;
    }
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}