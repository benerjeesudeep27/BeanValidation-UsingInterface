package com.beanvalidation.app;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;

import com.beanvalidation.beans.Student;
import com.beanvalidation.validator.StudentValidator;

public class TestingApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/beanvalidation/resources/applicationContext.xml");
		Student stud = (Student) context.getBean("stdObj");
		DataBinder data = new DataBinder(stud);
		data.addValidators(new StudentValidator());
		data.validate();
		List<ObjectError> list = data.getBindingResult().getAllErrors();
		if(list.isEmpty()) {
			System.out.println(stud);
		}else {
			for (ObjectError error : list) {
				System.err.println(error.getDefaultMessage());
			}
		}
		
	}
}
