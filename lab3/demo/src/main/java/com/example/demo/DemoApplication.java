//producer
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

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

		Properties prop = new Properties();
		prop.put("bootstrap.servers", "localhost:9092");
		prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		System.out.print("Hi from producer~");

		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(prop);
		int delay = 2000;
		double multiplier;

		try{
			int count = 0;
			while (true){
				kafkaProducer.send(new ProducerRecord<String, String>("mytopic1", Integer.toString(count), "test message - " + count));
				count++;
				multiplier = (1.0 + Math.sin(System.currentTimeMillis())) / 2.0;
				Thread.sleep((long)(multiplier*delay));
				System.out.println(count);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			kafkaProducer.close();
			System.out.print("Bye from producer~");
		}
	}
}
