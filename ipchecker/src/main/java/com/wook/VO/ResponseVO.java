package com.wook.VO;

public class ResponseVO {

	private String ip;
	private String nationalCode;
	private long timestamp;
	
	public ResponseVO() {
		
	}
	
	public ResponseVO(String ip, String nationalCode) {
		this.ip=ip;
		this.nationalCode=nationalCode;
	}
	public ResponseVO(String ip, String nationalCode, long timestamp) {
		this.ip=ip;
		this.nationalCode=nationalCode;
		this.timestamp=timestamp;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNationalCode() {
		return nationalCode;
	}

	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
