package com.spring.scrapper.birt.dto;

public class BirtBookInputFormDto {
	private String authorName;
	private String reportType;
	
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	@Override
	public String toString() {
		return "BirtBookInputFormDto [authorName=" + authorName + ", reportType=" + reportType + "]";
	}
}
