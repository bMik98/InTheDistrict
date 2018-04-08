package com.akurus.telegram;

import com.akurus.telegram.Config.RabbitConfiguration;
import com.akurus.telegram.Listner.TelegramEventHander;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties
@Configuration
@EnableAutoConfiguration
@ComponentScan
//@Import(RabbitConfiguration.class)


public class TelegramApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramApplication.class, args);
	}

}
