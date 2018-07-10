package com.spring.scrapper.birt;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class BirtServiceImpl implements BirtService{

	@Autowired
	private ServletContext servletContext;
	
	private IReportEngine birtEngine;
	private ApplicationContext context;
	
	private Map<String, IReportRunnable> reports = new HashMap<>();
	private Map<String, IReportRunnable> thumbnails = new HashMap<>();
	private static final String IMAGE_FOLDER = "/images";
	
//	@PostConstruct
//	protected void initialize() throws BirtException{
//		EngineConfig config = new EngineConfig();
//		config.getAppContext().put("spring", this.context);
//		IReportEngineFactory factory = (IReportEngineFactory)Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
//		this.birtEngine = factory.createReportEngine(config); 
//	}
//	
	@Override
	public void loadReports(HttpServletRequest request) throws EngineException {
		File folder = new File(request.getServletContext().getRealPath("/") + "WEB-INF/reports");
		for (String file : folder.list()) {
			if (!file.endsWith(".rptdesign")) {
				continue;
			}
			if (file.contains("thumb")) {
				thumbnails.put(file.replace("-thumb.rptdesign", ""),
						birtEngine.openReportDesign(folder.getAbsolutePath() + File.separator + file));
			} else {
				reports.put(file.replace(".rptdesign", ""),
						birtEngine.openReportDesign(folder.getAbsolutePath() + File.separator + file));
			}
		}
	}
//	
	@Override
	public void getBirtReport(IRenderOption option) {
		
	}
	
	
}
