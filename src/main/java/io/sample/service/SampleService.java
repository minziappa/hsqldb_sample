package io.sample.service;

import java.util.List;

import io.sample.bean.Person;

public interface SampleService {

	public List<Person> find(String name);
	public void remove(int userId);
}