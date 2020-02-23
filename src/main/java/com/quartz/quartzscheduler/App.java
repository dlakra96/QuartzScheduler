package com.quartz.quartzscheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * deepanshu lakra : A simple application to demonstrate logging using log4j2 logging framework
 *
 */
public class App 
{
    
	private static final Logger logger  = LogManager.getLogger(App.class);
	public static void main( String[] args )
    {
        logger.info("Deepanshu Lakra :- This message has been printed using log4j2 logger");    
    }
}
