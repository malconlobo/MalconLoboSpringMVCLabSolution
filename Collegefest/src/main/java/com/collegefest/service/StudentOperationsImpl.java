package com.collegefest.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.Transaction;
import com.collegefest.entities.Student;

@Service
public class StudentOperationsImpl implements StudentOperations {

	private SessionFactory sessionFactory;

	private Session session;

	@Autowired
	public StudentOperationsImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = sessionFactory.openSession();
		}
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		List<Student> students = session.createQuery("from Student").list();
		tx.commit();
		return students;
	}

	@Override
	public Student findById(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Student student = session.get(Student.class, id);
		tx.commit();
		return student;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Student student = session.get(Student.class, id);
		if (student != null) {
			session.delete(student);
		}
		tx.commit();
	}

	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(student);
		tx.commit();
	}

}
