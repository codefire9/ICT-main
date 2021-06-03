package com.cts.unoadm.main;

import com.cts.unoadm.skeletonvalidator.SkeletonValidator;
import com.cts.unoadm.service.*;
import com.cts.unoadm.util.*;
public final class MainApp {
    private MainApp(){}
	public static void main(String[] args) {
		//Don't delete this code
		//Skeletonvalidaton starts
		new SkeletonValidator();
		//Skeletonvalidation ends

		//Write your code here..
		try{
		StudentAdmissionService service = new StudentAdmissionService();
		System.out.println(service.addStudentAdmissionDetails("inputFeed.txt"));
		System.out.println(service.searchStudentAdmission("A005"));
		}catch(Exception e){e.printStackTrace();}
		//List<StudentAdmission> studentAdmissionList = service.buildStudentAdmissionsList(ApplicationUtil.readFile("inputFeed.txt"));
		/*for(StudentAdmission e:studentAdmissionList)
		{
		    System.out.println(e);
		}*/
	}

}
