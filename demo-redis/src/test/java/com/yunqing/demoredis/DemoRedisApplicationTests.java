package com.yunqing.demoredis;

import com.yunqing.demoredis.config.RedisCacheTemplate;
import com.yunqing.demoredis.entity.Emp;
import com.yunqing.demoredis.mapper.EmpMapper;
import com.yunqing.demoredis.utils.SequenceUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
class DemoRedisApplicationTests {

	private static final String  SERIAL_NUM= "redis:serialNumber:";

	@Autowired
	private RedisCacheTemplate redisCacheTemplate;

	@Test
	void contextLoads() {
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String key = SERIAL_NUM + currentDate;
		long incr = redisCacheTemplate.incr(key, -1, 86400);
		//移位运算符<<，左移几位相当于乘以2的几次方, 1 << 2 = 4
		String code = SequenceUtil.getSequence(incr, 1 << 2);
		log.info(currentDate + code);
	}


}
