package com.cts.unoadm.dao;

import java.util.ArrayList;
import java.util.List;

import com.cts.unoadm.exception.StudentAdmissionException;
import com.cts.unoadm.vo.StudentAdmission;
import com.cts.unoadm.util.*;
import java.sql.*;

/*
CREATE DATABASE CTSUNO

CREATE TABLE UNO_ADMISSION(
    ADMISSION_ID  VARCHAR(50) PRIMARY KEY,
    STUDENT_CODE VARCHAR(50)NOT NULL,
    DATE_OF_COUNSELING DATE NOT NULL,
    DEPARTMENT_NAME VARCHAR(20),
    DATE_OF_ADMISSION DATE NOT NULL,
    PREFER_COLLEGE_HOSTEL VARCHAR(20),
    FIRST_GRADUATE VARCHAR(20),
    MANAGER_APPROVAL VARCHAR(10),
    ADMISSION_FEE DOUBLE,
    TUITION_FEE DOUBLE,
    HOSTEL_FEE DOUBLE,
    TOTAL_COLLEGE_FEE DOUBLE,
    FINAL_STATUS_OF_ADMISSION VARCHAR(20)
);
*/
public class StudentAdmissionDAO {
	
	public boolean addStudentAdmissionDetails(List<StudentAdmission> stdAdmissions) throws StudentAdmissionException {
		boolean recordsAdded = false;
		Connection con = DBConnectionManager.getInstance().getConnection();
		PreparedStatement ps = null;
		try{
		    //inserting values of list stdAdmissions into database
		    String query = "insert into UNO_ADMISSION(ADMISSION_ID,STUDENT_CODE,DATE_OF_COUNSELING,DEPARTMENT_NAME,DATE_OF_ADMISSION,PREFER_COLLEGE_HOSTEL,FIRST_GRADUATE,MANAGER_APPROVAL,ADMISSION_FEE,TUITION_FEE,HOSTEL_FEE,TOTAL_COLLEGE_FEE,FINAL_STATUS_OF_ADMISSION) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		    for(StudentAdmission e:stdAdmissions) {
		        ps = con.prepareStatement(query);
		        ps.setString(1,e.getAdmissionId());
		        ps.setString(2,e.getStudentCode());
		        ps.setDate(3,ApplicationUtil.convertUtilToSqlDate(e.getDateOfCounseling()));
		        ps.setString(4,e.getDepartmentName());
		        ps.setDate(5,ApplicationUtil.convertUtilToSqlDate(e.getDateOfAdmission()));
		        ps.setString(6,e.getPreferCollegeHostel());
		        ps.setString(7,e.getFirstGraduate());
		        ps.setString(8,e.getManagerApproval());
		        ps.setDouble(9,e.getAdmissionFee());
		        ps.setDouble(10,e.getTuitionFee());
		        ps.setDouble(11,e.getHostelFee());
		        ps.setDouble(12,e.getTotalCollegeFee());
		        ps.setString(13,e.getFinalStatusOfAdmission());
		        int i = ps.executeUpdate();
		        if(i>0)
		        {
		            recordsAdded = true;
		        }
		        else
		        {
		            recordsAdded = false;
		        }
		    }
		    
		   
		}
		catch(SQLException e)
		{
		    try{
		    con.rollback();
		    }catch(Exception e1){
		        e.printStackTrace();
		    }
		}
		catch(Exception e) {
		    e.printStackTrace();
		    //throw new StudentAdmissionException("Database Value Insertion Failed", e.getCause());
		}
		finally{
		    try{
		     ps.close();
		    con.close();
		    }catch(Exception e) {
		    e.printStackTrace();
		    //throw new StudentAdmissionException("Database Value Insertion Failed", e.getCause());
		    }
		}
		//code here
		
		return recordsAdded; 
	}

	public List<StudentAdmission> getAllStudentAdmissionDetails() throws StudentAdmissionException {
		
		List<StudentAdmission> stdAdmissions = new ArrayList<StudentAdmission>();

		//code here
		//Retrieval of all records from database
		String query = "select * from UNO_ADMISSION";
		try(Connection con = DBConnectionManager.getInstance().getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);){
		    while(rs.next())
		    {
		        //storing retrieved records in object
		        StudentAdmission obj = new StudentAdmission();
		        obj.setAdmissionId(rs.getString(1));
    		    obj.setStudentCode(rs.getString(2));
    		    obj.setDateOfCounseling(new java.util.Date(rs.getDate(3).getTime()));
    		    obj.setDepartmentName(rs.getString(4));
    		    obj.setDateOfAdmission(new java.util.Date(rs.getDate(5).getTime()));
    		    obj.setPreferCollegeHostel(rs.getString(6));
    		    obj.setFirstGraduate(rs.getString(7));
    		    obj.setManagerApproval(rs.getString(8));
    		    obj.setAdmissionFee(rs.getDouble(9));
    		    obj.setTuitionFee(rs.getDouble(10));
    		    obj.setHostelFee(rs.getDouble(11));
    		    obj.setTotalCollegeFee(rs.getDouble(12));
    		    obj.setFinalStatusOfAdmission(rs.getString(13));
    		    //adding StudentAdmission object into arraylist
    		    stdAdmissions.add(obj);
		    }
		    
		}catch(Exception e)
		{
		    e.printStackTrace();
		    //throw new StudentAdmissionException("Database Value Retrieval Failed", e.getCause());
		}
		
		return stdAdmissions;

	}
}