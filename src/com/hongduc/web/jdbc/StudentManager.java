package com.hongduc.web.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;


@ManagedBean
@SessionScoped
public class StudentManager {
	StudentDbUtil studentDbUtil;
	List<Student> students;

	public StudentManager() {
		super();
		students = new ArrayList<Student>();
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public void loadStudents()
	{
		this.students = StudentDbUtil.getStudents();
	}
	
	public String addStudent(Student stu)
	{
		StudentDbUtil.addStudent(stu);
		return "List-students";
	}
	
	public String loadStudent(int ids)
	{
		Student theStudent = StudentDbUtil.fetchStudent(ids);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getRequestMap();
		requestMap.put("student", theStudent);
		return "Edit-student";
	}
	
	public String updateStudent(Student stu)
	{
		StudentDbUtil.updateStudent(stu);
		return "List-students";
	}
	
	public String deleteStudent(int id)
	{
		StudentDbUtil.deleteStudent(id);
		return "List-students";
	}
}
