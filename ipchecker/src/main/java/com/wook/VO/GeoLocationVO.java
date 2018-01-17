package com.wook.VO;

import com.opencsv.bean.CsvBindByPosition;

public class GeoLocationVO {
	

	private String startIp;
	private String endIp;
	private String nationalCode;
	private String ipOwnerDescription;
	
	public GeoLocationVO() {
		
	}
	//Constructor
	public GeoLocationVO(String startIp, String endIp, String nationalCode, String ipOwnerDescription) {
		this.startIp=startIp;
		this.endIp=endIp;
		this.nationalCode=nationalCode;
		this.ipOwnerDescription=ipOwnerDescription;
	}
	
	//Getters & Setters
	public String getStartIp() {
		return startIp;
	}
	public void setStartIp(String startIp) {
		this.startIp = startIp;
	}
	public String getEndIp() {
		return endIp;
	}
	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}
	public String getNationalCode() {
		return nationalCode;
	}
	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}
	public String getIpOwnerDescription() {
		return ipOwnerDescription;
	}
	public void setIpOwnerDescription(String ipOwnerDescription) {
		this.ipOwnerDescription = ipOwnerDescription;
	}
	
	
}
