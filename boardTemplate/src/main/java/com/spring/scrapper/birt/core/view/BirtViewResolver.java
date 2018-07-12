package com.spring.scrapper.birt.core.view;

import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.birt.report.engine.api.IReportEngine;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

public class BirtViewResolver extends UrlBasedViewResolver{
	private String reportsDirectory = "";

    private IReportEngine birtEngine;
    private DataSource dataSource;
    private int taskType;
    private Map reportParameters = null;

    public void setReportsDirectory(String reportsDirectory) {
        this.reportsDirectory = reportsDirectory;
    }

//    public BirtViewResolver() {
//        setViewClass(AbstractSingleFormatBirtView.class);
//        setSuffix(".rptdesign");
//    }

    public void setBirtEngine(IReportEngine birtEngine) {
        this.birtEngine = birtEngine;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public void setReportParameters(Map reportParameters) {
        this.reportParameters = reportParameters;
    }

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        AbstractSingleFormatBirtView view = (AbstractSingleFormatBirtView) super.buildView(viewName);
        view.setDataSource(this.dataSource);
        view.setBirtEngine(this.birtEngine);
        view.setReportParameters(this.reportParameters);
        view.setReportName(viewName);
        view.setReportsDirectory(this.reportsDirectory);
        return view;
    }


//    @Override
//    protected Class requiredViewClass() {
//        return AbstractSingleFormatBirtView.class;
//    }
}
