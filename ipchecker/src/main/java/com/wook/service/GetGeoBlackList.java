package com.wook.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


import com.wook.VO.BlackListVO;
import com.wook.VO.GeoLocationVO;

@Service
public class GetGeoBlackList {
	
	private static final String GEOLOCATION_CSV_FILE_PATH = "csv/geolocation.csv";
	private static final String BLACKLIST_CSV_FILE_PATH = "csv/blacklist.csv";
  		
	@Bean
	public List<GeoLocationVO> getGeoLocationList() {

		List<GeoLocationVO> GeoLocationList= new ArrayList<GeoLocationVO>();		
		GeoLocationVO geoLocationVO = new GeoLocationVO();
        String line = "";
    
        //Read geolocation.csv File and store in List when init
        try (BufferedReader br = new BufferedReader(new FileReader(getFile(GEOLOCATION_CSV_FILE_PATH)))) {

           while ((line = br.readLine()) != null) {

               // Use comma as separator
               String[] lineArray = parseLine(line);
               geoLocationVO.setStartIp(lineArray[0]);
               geoLocationVO.setEndIp(lineArray[1]);
               geoLocationVO.setNationalCode(lineArray[2]);
               geoLocationVO.setIpOwnerDescription(lineArray[3]);
               GeoLocationList.add(geoLocationVO);
               geoLocationVO = new GeoLocationVO();

           }          
       } catch (IOException e) {
            e.printStackTrace();
        }
        return GeoLocationList;
        
	}  
	
	// Remove all " from line
	public static String[] parseLine(String line) {
		
		String[] lineArray = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
		lineArray[0]=lineArray[0].replace("\"","");
		lineArray[1]=lineArray[1].replace("\"","");
		lineArray[2]=lineArray[2].replace("\"","");
		lineArray[3]=lineArray[3].replace("\"","");	
		
		return lineArray;
	}

	@Bean
	public List<BlackListVO> getBlackList() {

		List<BlackListVO> blackList= new ArrayList<BlackListVO>();		
        BlackListVO blackListVO = new BlackListVO();
        String line = "";
    
        // Read blacklist.csv File and store in List when init
        try (BufferedReader br = new BufferedReader(new FileReader(getFile(BLACKLIST_CSV_FILE_PATH)))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
            	blackListVO.setIp(line);
            	blackList.add(blackListVO);
            	blackListVO = new BlackListVO();

            }        
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return blackList;
	} 
       
	
	/* Input: Class path file name
	 * Output: File object
	 */
	private File getFile(String fileName) {
    	
    	ClassLoader classLoader = getClass().getClassLoader();
    	File file = new File(classLoader.getResource(fileName).getFile());
    	
    	return file;
    }

}
