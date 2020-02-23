package com.quartz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.google.gson.JsonObject;

@RestController("/couchdbConnection")
public class QuartzRestController{
	
@Autowired
private CloudantClient couchdbClient;

@RequestMapping(value = "/createDataBase/{dbname}", method = RequestMethod.POST)
public String createDatabase(@RequestParam("dbname") String dbName)
{
	couchdbClient.createDB(dbName);
	if(couchdbClient.getAllDbs().contains(dbName))
		return "Database has been created successfully";
	else
		return "There occured some problem creating the database";
	
}

@RequestMapping(value = "/{dbname}/addJsonDoc", method = RequestMethod.POST)
public String addJSONDocToCouchDB(@RequestBody JsonObject jsonObject,@RequestParam("dbname") String dbName)
{
	Database database = couchdbClient.database(dbName, false);
	try {
		
		database.save(jsonObject);
		return "Document has been inserted successfully.";
		
	}catch(com.cloudant.client.org.lightcouch.NoDocumentException ex)
	{
		return "Database does not exist !!! Please create database first.";
	}
	
}

}