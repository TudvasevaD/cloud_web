//producer
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

@RestController
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		//SpringApplication.run(DemoApplication.class, args);

		Properties prop = new Properties();
		prop.put("group.id", "testgroup");
		prop.put("bootstrap.servers", "localhost:9092");
		prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		System.out.print("Hi from consumer~");

		KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(prop);
		List<String> topics = new LinkedList<String>();
		topics.add("mytopic1");
		kafkaConsumer.subscribe(topics);

		try{
			int count = 0;
			while (true){
				ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(10000));
				for (ConsumerRecord<String, String> record: records){
					System.out.println(String.format("topic: " + record.topic() + ", message: " + record.value()));
				}
				Thread.sleep(10000);
				System.out.print(count);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			kafkaConsumer.close();
		}
	}
}
