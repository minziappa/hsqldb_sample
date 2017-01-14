package io.sample.main;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

import io.sample.bean.Person;
import io.sample.service.SampleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springConfig-test.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
//@DbUnitConfiguration(databaseConnection="dataSource")
//@TestExecutionListeners({
//	  DependencyInjectionTestExecutionListener.class,
//	  DirtiesContextTestExecutionListener.class,
//	  TransactionDbUnitTestExecutionListener.class, //<-- needed if using transactions otherwise use TransactionalTestExecutionListener.class
//	  })
public class SampleServiceTest {

	@Autowired
	private SampleService sampleService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before");
	}

	@Test
	@DatabaseSetup("/xml/sampleData.xml")
	public void test1() { 
	    List<Person> userList = sampleService.find("hil");
	    assertEquals(1, userList.size());
	    assertEquals("Phillip", userList.get(0).getFirstName());
	}

	@Test
	@DatabaseSetup("/xml/sampleData.xml")
	@ExpectedDatabase("/xml/expectedData.xml")
	public void testRemove() throws Exception {
		sampleService.remove(1);
	}

//	@Test
//	@DatabaseSetup(value="/xml/sampleData.xml", type=DatabaseOperation.CLEAN_INSERT)
//	@DatabaseTearDown(value="/xml/sampleData.xml", type=DatabaseOperation.DELETE_ALL)
//	@Transactional
//	public void test01() {
//	    //some code
//	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("After");
	}

}
