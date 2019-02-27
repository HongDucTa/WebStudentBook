package com.hongduc.web.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;


public class StudentDbUtil {
	private static final String PERSISTENCE_UNIT_NAME="JSFJPA";
	@PersistenceContext
	private static EntityManagerFactory factory= Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	@PersistenceUnit
	private static EntityManager em = factory.createEntityManager();
	
	public StudentDbUtil() {
	}
	
	public static List<Student> getStudents()
	{
		
		List<StudentEntity> students = new ArrayList<StudentEntity>();
		try {
			students = em.createQuery("SELECT s FROM StudentEntity AS s").getResultList();
			List<Student> result = new ArrayList<Student>();
			for (int i = 0;i < students.size();i++)
			{
				Student s = new Student();
				s.id = students.get(i).getId();
				s.firstName = students.get(i).getFirstName();
				s.lastName = students.get(i).getLastName();
				s.email = students.get(i).getEmail();
				result.add(s);
			}
			return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static void addStudent(Student s)
	{
		StudentEntity newStudent = new StudentEntity();
		newStudent.setFirstName(s.getFirstName());
		newStudent.setLastName(s.getLastName());
		newStudent.setEmail(s.getEmail());
		try
		{
			em.getTransaction().begin();
			em.persist(newStudent);
			em.getTransaction().commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Student fetchStudent(int ids)
	{
		StudentEntity studentEntity = em.find(StudentEntity.class, ids);
		Student student = new Student(studentEntity.getId(),studentEntity.getFirstName(),studentEntity.getLastName(),studentEntity.getEmail());
		return student;
	}
	
	public static void updateStudent(Student s)
	{
		StudentEntity student = em.find(StudentEntity.class, s.getId());
		em.getTransaction().begin();
		student.setFirstName(s.getFirstName());
		student.setLastName(s.getLastName());
		student.setEmail(s.getEmail());
		em.getTransaction().commit();
	}
	
	public static void deleteStudent(int ids)
	{
		StudentEntity student = em.find(StudentEntity.class, ids);
		try
		{
			em.getTransaction().begin();
			em.remove(student);
			em.getTransaction().commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
