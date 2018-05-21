package com.spring.scrapper.jasper.domain;

public class JasperBookInputFormVO {
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
		return "JasperBookInputFormVO [reportFormat=" + reportFormat + ", authorName=" + authorName + "]";
	}
	
}
