package com.cts.unoadm.service;

import java.util.ArrayList;
import java.util.List;

import com.cts.unoadm.exception.StudentAdmissionException;
import com.cts.unoadm.vo.StudentAdmission;
import com.cts.unoadm.util.*;
import com.cts.unoadm.dao.*;
public class StudentAdmissionService {
	
	/**
	 * @param empReimburseRecords
	 * @return List<StudentAdmission>
	 */
	public static List<StudentAdmission> buildStudentAdmissionsList(List<String> studentAdmissionRecords) {
		List<StudentAdmission> studentAdmissionList = new ArrayList<StudentAdmission>();
		
		//Code here
		//storing each line into List of StudentAdmission objects
		for(String e:studentAdmissionRecords) {
		    String res[] = e.split(",");
		    String admissionId = res[0];
		    String studentCode = res[1];
		    String dateOfCounseling = res[2];
		    String departmentName = res[3];
		    String dateOfAdmission = res[4];
		    String preferCollegeHostel = res[5];
		    String firstGraduate = res[6];
		    String managerApproval = res[7];
		    StudentAdmission obj = new StudentAdmission();
		    obj.setAdmissionId(admissionId);
		    obj.setStudentCode(studentCode);
		    //converting String to java.uti.Date
		    obj.setDateOfCounseling(ApplicationUtil.convertStringToDate(dateOfCounseling));
		    obj.setDepartmentName(departmentName);
		    //converting String to java.uti.Date
		    obj.setDateOfAdmission(ApplicationUtil.convertStringToDate(dateOfAdmission));
		    obj.setPreferCollegeHostel(preferCollegeHostel);
		    obj.setFirstGraduate(firstGraduate);
		    obj.setManagerApproval(managerApproval);
		    double[] studentAdmissionCosts = calculateTotalCollegeFee(preferCollegeHostel,firstGraduate,departmentName);
		    obj.setAdmissionFee(studentAdmissionCosts[0]);
		    obj.setTuitionFee(studentAdmissionCosts[1]);
		    obj.setHostelFee(studentAdmissionCosts[2]);
		    obj.setTotalCollegeFee(studentAdmissionCosts[3]);
		    obj.setFinalStatusOfAdmission("AdmissionSuccessfull");
		    
		    
		    studentAdmissionList.add(obj);
		}
		
		return studentAdmissionList;
	}


	public boolean addStudentAdmissionDetails(String inputFeed) throws StudentAdmissionException {
		
		//Code here
		List<StudentAdmission> studentAdmissionList = StudentAdmissionService.buildStudentAdmissionsList(ApplicationUtil.readFile(inputFeed));
		StudentAdmissionDAO stdDao = new StudentAdmissionDAO();
		return stdDao.addStudentAdmissionDetails(studentAdmissionList);
	}

	public static double[] calculateTotalCollegeFee(String preferCollegeHostel, String firstGraduate, String departmentName) {
		double[] studentAdmissionCosts = new double[4];

		//Code here..
		if("YES".equals(preferCollegeHostel))
		{
		        studentAdmissionCosts[2]=75000;
		}
		else{
		        studentAdmissionCosts[2]=0;
		}
		if("EEE".equals(departmentName)) {
		    
		        studentAdmissionCosts[0]=30000;
		        studentAdmissionCosts[1]=45000;
		    }
		else if("ECE".equals(departmentName)) {
		    
		        studentAdmissionCosts[0]=30000;
		        studentAdmissionCosts[1]=50000;
		    }
		else if("CSE".equals(departmentName)) {
		    studentAdmissionCosts[0]=30000;
		        studentAdmissionCosts[1]=45000;
		        
		    }
		else if("MECH".equals(departmentName)) {
    		  studentAdmissionCosts[0]=30000;
		        studentAdmissionCosts[1]=55000;
		        
		    }
		else if("CIVIL".equals(departmentName)) {
		        studentAdmissionCosts[0]=30000;
		        studentAdmissionCosts[1]=50000;
		        
		    }
		else if("IT".equals(departmentName)) {
		        studentAdmissionCosts[0]=30000;
		        studentAdmissionCosts[1]=45000;
		        
		    }
		 //for first graduate discount is there
		if("YES".equals(firstGraduate)) {
		    studentAdmissionCosts[3]=studentAdmissionCosts[0]+studentAdmissionCosts[1]+studentAdmissionCosts[2]-20000;
		}
		else{
		    studentAdmissionCosts[3]=studentAdmissionCosts[0]+studentAdmissionCosts[1]+studentAdmissionCosts[2];
		}
		return studentAdmissionCosts;
	}

	public boolean searchStudentAdmission(String admissionId) throws StudentAdmissionException {
		boolean status = false;
		
		//Code here..
		StudentAdmissionDAO stdDao = new StudentAdmissionDAO();
		List<StudentAdmission> stdAdmissions = stdDao.getAllStudentAdmissionDetails();
		for(StudentAdmission e:stdAdmissions) {
		    if(e.getAdmissionId().equals(admissionId)) {
		        System.out.println(e);
		        return true;
		    }
		}
		return false;
	}
}
