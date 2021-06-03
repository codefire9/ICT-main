package com.cts.unoadm.util;


import java.util.*;
import java.io.*;
import java.text.*;

import com.cts.unoadm.exception.StudentAdmissionException;

public final class ApplicationUtil {

	/**
	 * @param fileName
	 * @return List<String>
	 * @throws StudentAdmissionException
	 */
	 
	 private ApplicationUtil(){}
	public static List<String> readFile(String fileName) throws StudentAdmissionException {
		List<String> studentAdmissionList = new ArrayList<String>();
		  //Code here..
		FileReader fr = null;
		BufferedReader br = null;
		try{
		    fr=new FileReader(fileName);
		    br = new BufferedReader(fr);
		    String line = null;
		    while((line=br.readLine())!=null)
		    {
		        String []res = line.split(",");
		        String managerApproval = res[7];
		        Date dtOfCounseling = convertStringToDate(res[2]);
		        Date dtOfAdmission = convertStringToDate(res[4]);
		        if(checkIfValidAdmission(dtOfCounseling,dtOfAdmission,managerApproval))
		        {
		            studentAdmissionList.add(line);
		        }
		        
		    }
		}catch(Exception e){e.printStackTrace();}
		
		return studentAdmissionList;
	}

	/**
	 * @param util
	 *            Date
	 * @return sql Date
	 */
	public static java.sql.Date convertUtilToSqlDate(java.util.Date uDate) {
		
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		
		//Code here..
		
		
		return sDate;
	}

	/**
	 * @param inDate
	 * @return Date
	 */
	public static Date convertStringToDate(String inDate) {
		
		//Code here..
		try{
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
		    return format.parse(inDate);
		}catch(Exception e){e.printStackTrace();
		    return null;
		}
	}

	public static boolean checkIfValidAdmission(Date dtOfCounseling, Date dtOfAdmission, String manager) {
		boolean admissionValidity = false;
		
		//Code here..
		if("Approved".equals(manager) && ((dtOfAdmission.getTime() - dtOfCounseling.getTime())/(1000*60*60*24))%365<= 10)
		{
		    admissionValidity = true;
		}
		
		return admissionValidity;
	}
}
