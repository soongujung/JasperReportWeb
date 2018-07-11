package com.spring.scrapper.birt.util;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BirtPlatformListener {
	private final Logger LOGGER = LoggerFactory.getLogger(BirtPlatformListener.class);
	
	private EngineConfig engineConfig;
	private IReportEngine reportEngine;
	
	public void start(){
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("STARTING ECLIPSE BIRT PLATFORM");
		}
		
		try {
			Platform.startup(engineConfig);
		} catch (BirtException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("FAILURE STARTING BIRT PLATFORM", e);
		}
	}
	
	public void destroy(){
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("SHUTTING DOWN Eclipse BIRT platform");
		}
		
		if(reportEngine != null){
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("SHUTTING DOWN report Engine instance");
			}
			reportEngine.destroy();
		}
		
		Platform.shutdown();
	}
	
	public IReportEngine getReportEngine(){
		if (reportEngine == null) {
		      if (LOGGER.isDebugEnabled()) {
		    	  LOGGER.debug("Creating new instance of report engine.");
		      }
		      IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);

		      reportEngine = factory.createReportEngine(engineConfig);
		    }

		    return reportEngine;
	}
	
	public void setEngineConfig(EngineConfig engineConfig){
		this.engineConfig = engineConfig;
	}
	
}
