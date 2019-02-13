package dianfan.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dianfan.models.ResultBean;
import dianfan.service.logins.LoginService;
import dianfan.service.records.EstateShowService;
import dianfan.service.records.RecordsService;
import dianfan.service.records.TakeLookService;
import freemarker.core.ParseException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-context.xml", "classpath:spring-redis.xml"})
public class test1 {

	@Autowired
	LoginService loginService;
	@Autowired
	RecordsService recordsService;
	@Autowired
	TakeLookService takeLookService;
	@Autowired
	EstateShowService estateShowService;
	

	
	@Test
	public void test3() throws JsonProcessingException, ParseException {
		ResultBean g = takeLookService.findTakeLookList("bb6be675be364eaba56acea438206e3c", 0, 0);
		ObjectMapper mapper = new ObjectMapper();
		String string = mapper.writeValueAsString(g);
		System.err.println(string);
		System.err.println("成功！！！！！！！！！！！！！！！！！！！");
	}
/*	public void test3() throws JsonProcessingException, ParseException {
		ResultBean g = estateShowService.findEstateShowList("1", 1, 11);
		ObjectMapper mapper = new ObjectMapper();
		String string = mapper.writeValueAsString(g);
		System.err.println(string);
		System.err.println("成功！！！！！！！！！！！！！！！！！！！");
	}*/
}
