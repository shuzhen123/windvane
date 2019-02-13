package dianfan.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dianfan.models.ResultBean;
import dianfan.service.company.BackupsService;
import dianfan.service.company.EstateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-context.xml", "classpath:spring-redis.xml"})
public class test1 {
	
	@Autowired
	private EstateService estateService;
	@Autowired
	private BackupsService backupsService;
	
	@Test
	public void test7() throws JsonProcessingException, ParseException {
		ResultBean g = estateService.addEstate("1", "dads", "dads", "dads", "dads", "dads", "dads", "dads");
		ObjectMapper mapper = new ObjectMapper();
		String string = mapper.writeValueAsString(g);
		System.err.println(string);
		System.err.println("成功！！！！！！！！！！！！！！！！！！！");
	}
	
	@Test
	public void test17() throws JsonProcessingException, ParseException {
		ResultBean g = backupsService.findStatisticsList("1", "0", "2018-08-11 23:59:59", "2018-08-30 00:00:00", "01");
		ObjectMapper mapper = new ObjectMapper();
//		String string = mapper.writeValueAsString(g);
//		System.err.println(string);
//		System.err.println("成功！！！！！！！！！！！！！！！！！！！");
	}
	@Test
	public void test117() throws JsonProcessingException, ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
		Date start=sdf.parse("2018-09-01");
		Date end=sdf.parse("2018-09-03");

		/*String fromDate = sdf.format(starttime);
		String toDate = sdf.format(endtime);*/
		long from = sdf.parse("2018-09-01").getTime();
		long to = sdf.parse("2018-09-03").getTime();
		int days = (int) ((to - from)/(1000 * 60 * 60 * 24));
		System.err.println(days);
	}
	
	
	
	
}
