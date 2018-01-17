package com.wook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wook.VO.BlackListVO;
import com.wook.VO.GeoLocationVO;
import com.wook.VO.ResponseVO;
import com.wook.VO.WhiteListVO;

@Service
public class MainService {
	
	@Autowired
	GetWhiteList getWhiteList;
	
	@Autowired
	List<GeoLocationVO> getGeoList;
	
	@Autowired
	List<BlackListVO> getBlackList;
	
	private List<WhiteListVO> whiteLists;
	
	public MainService() {
		
	}
	
	// Get location from given IP
	public ResponseVO getLocation(String Ip) {
		
		ResponseVO responseVO = new ResponseVO();
		
		String TempIp = Ip;
		//Get Dynamic white list
		whiteLists = getWhiteList.WhiteLists();
		
		//Get location
		//Black IP
		for (BlackListVO checkIfBlack: getBlackList ) {
			if (Ip.equals(checkIfBlack.getIp())) {
				System.out.println("Black");	
				responseVO.setIp(Ip);
				responseVO.setNationalCode("BlackIP");
				return responseVO;
			}
				
		}
		//White IP
		for (WhiteListVO checkIfWhite: whiteLists ) {
			if (Ip.equals(checkIfWhite.getIp())) {
				System.out.println("White");	
				responseVO.setIp(Ip);
				responseVO.setNationalCode("White");
				return responseVO;
			}
				
		}
		// Lookup geolocation list object
		// Split Ip into 4 part
		String []getIp = TempIp.split("\\.");		
		int ipFirst= Integer.parseInt(getIp[0]);
		int ipSecond = Integer.parseInt(getIp[1]);
		int ipThird = Integer.parseInt(getIp[2]);
		int ipFourth = Integer.parseInt(getIp[3]);
		int geoStartFirst=0;
		int geoStartSecond=0;
		int geoStartThird=0;
		int geoStartFouth=0;
		int geoEndSecond=0;
		int geoEndThird=0;
		int geoEndFouth=0;
		String [] geoIp = new String[4];
		String [] geoEndIp = new String[4];
		
		// Can't think a better way...no time T.T
		for (GeoLocationVO checkIp: getGeoList ) {

			geoIp = checkIp.getStartIp().split("\\.");
			geoEndIp = checkIp.getEndIp().split("\\.");
			
			geoStartFirst = Integer.parseInt(geoIp[0]);
			geoStartSecond = Integer.parseInt(geoIp[1]);
			geoStartThird = Integer.parseInt(geoIp[2]);
			geoStartFouth = Integer.parseInt(geoIp[3]);			
			
			geoEndSecond = Integer.parseInt(geoEndIp[1]);
			geoEndThird = Integer.parseInt(geoEndIp[2]);
			geoEndFouth = Integer.parseInt(geoEndIp[3]);
			
			if (ipFirst==geoStartFirst) {
				if (ipSecond==geoStartSecond) {
					if (ipThird==geoStartThird) {
						if (ipFourth==geoStartFouth) {  // All mapping to the start IP -> RETURN
							responseVO.setIp(Ip);
							responseVO.setNationalCode(checkIp.getNationalCode());						
							return responseVO;
						}
						else {
							if (ipFourth <=geoEndFouth) { // All mapping except the last digit and inclusive at the end IP -> RETURN
								responseVO.setIp(Ip);
								responseVO.setNationalCode(checkIp.getNationalCode());
								return responseVO;
							}
							else {} // All mapping except the last digit and not inclusive at the end IP -> PASS
						}
					}
					else {
						if (ipThird <=geoEndThird) { // First, second match and third digit is inclusive at the end IP -> RETURN
							responseVO.setIp(Ip);
							responseVO.setNationalCode(checkIp.getNationalCode());
							return responseVO;
						}
						else {} // First, second match and third digit is not inclusive at the end IP -> PASS
					}					
				}
				else {
					if (ipSecond <=geoEndSecond) { // First match and second digit is inclusive at the end IP -> RETURN
						responseVO.setIp(Ip);
						responseVO.setNationalCode(checkIp.getNationalCode());
						return responseVO;
					}
					else {} // First match and second digit is not inclusive at the end IP -> PASS
				}				
			}
			else {}  // Noting Match -> PASS
			geoIp = new String[4];
			geoEndIp = new String[4];
		}

		// No Match Found	Is this a valid response?	
		responseVO.setIp(Ip);
    	responseVO.setNationalCode("No Mapping Location Found");
    	
    	return responseVO;
	}
}
