package io.sample.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sample.bean.Person;
import io.sample.service.SampleService;

@Service
@Transactional
public class SampleServiceImpl implements SampleService {

    @PersistenceContext
    private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> find(String name) {
		Query query = entityManager.createNamedQuery("Person.find");
        query.setParameter("name", "%"+name+"%");
        return query.getResultList();
	}

    public void remove(int userId) {
        Person person = entityManager.find(Person.class, userId);
        entityManager.remove(person);
    }

}