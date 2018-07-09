package com.spring.scrapper.birt.util;

import java.util.logging.Level;

import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.HTMLActionHandler;
import org.eclipse.birt.report.engine.api.HTMLEmitterConfig;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BirtConfiguration {
	private final Logger LOGGER = LoggerFactory.getLogger(BirtConfiguration.class);
	
	private String birtRuntimeLocation;
	private String logLocation;
	private Level logLevel;
	private EngineConfig engineConfig;
	
	public BirtConfiguration(){
		logLevel = Level.WARNING;
	}
	
	public void setBirtRuntimeLocation(String birtRuntimeLocation) {
	    this.birtRuntimeLocation = birtRuntimeLocation;
	}
	
	public EngineConfig getEngineConfig() {
	    if (engineConfig == null) {
	      if (LOGGER.isDebugEnabled()) {
	        LOGGER.debug("Creating new instance of EngineConfig");
	      }

	      engineConfig = new EngineConfig();
	      engineConfig.setEngineHome(birtRuntimeLocation);
	      engineConfig.setLogConfig(logLocation, logLevel);

	      HTMLEmitterConfig htmlConfig = new HTMLEmitterConfig();
	      htmlConfig.setActionHandler(new HTMLActionHandler());
	      
	      // This allows images to be referenced by url
	      htmlConfig.setImageHandler(new HTMLServerImageHandler());

	      engineConfig.getEmitterConfigs().put("html", htmlConfig);
	    }

	    return engineConfig;
	}
}
