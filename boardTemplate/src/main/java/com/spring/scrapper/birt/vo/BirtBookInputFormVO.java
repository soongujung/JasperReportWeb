package com.spring.scrapper.birt.vo;

public class BirtBookInputFormVO {
	private String reportFormat = "Html";
	private String authorName;
	
	public String getReportFormat() {
		return reportFormat;
	}
	public void setReportFormat(String reportFormat) {
		this.reportFormat = reportFormat;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	@Override
	public String toString() {
		return "BirtBookInputFormVO [reportFormat=" + reportFormat + ", authorName=" + authorName + "]";
	}
	
}
