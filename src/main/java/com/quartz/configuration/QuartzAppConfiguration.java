package com.quartz.configuration;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;

@Configuration
@PropertySource("classpath:application.properties")
public class QuartzAppConfiguration{
	
	@Value("${docker.couchdb.user}")
	private String couchdb_user;
	
	@Value("${docker.couchdb.url}")
	private String couchdb_url;
	
	@Value("${docker.couchdb.password}")
	private String couchdb_password;
	
	@Bean
	private CloudantClient couchdbJavaClient() {
		
		String couch_url = null;
		String couch_username = null;
		String couch_password = null;
		
		if(System.getenv("docker_couch_url") != null)
			couch_url = System.getenv("docker_couch_url");
		else
			couch_url = couchdb_url;
		
		if(System.getenv("docker_couch_username") != null)
			couch_username = System.getenv("docker_couch_username");
		else
			couch_username = couchdb_user;
		
		if(System.getenv("docker_couch_password") != null)
			couch_password = System.getenv("docker_couch_password");
		else
			couch_password = couchdb_password;
		
		CloudantClient cloudantClient = null;
		
		try {
			cloudantClient = ClientBuilder.url(new URL(couch_url))
														.username(couch_username)
														.password(couch_password)
														.build();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cloudantClient;
	}
}