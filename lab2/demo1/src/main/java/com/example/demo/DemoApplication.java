package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

@RestController
@SpringBootApplication
public class DemoApplication {

	public static int count;

	@RequestMapping("/")
	@ResponseBody
	public String index() {

		Jedis jedis = new Jedis("localhost", 6379);
		System.out.println("Connected to Redis");

		if (!jedis.exists("count"))
		{
			jedis.set("count", "0");
			count = 0;
			System.out.println("if");
		}
		else {
			Long c = jedis.incr("count");
			count = c.intValue();
		}

		jedis.close();
		jedis.shutdown();

		//count++;
		return "count = " + count;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
