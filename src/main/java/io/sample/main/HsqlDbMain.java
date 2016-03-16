package io.sample.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.sample.service.SampleService;


public class HsqlDbMain {

	public static void main(String[] args) {

		System.out.println("I love minji");
		ApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");
		SampleService sampleService = (SampleService) context.getBean("sampleServiceImpl");
		sampleService.find("njoonk");

	}

}