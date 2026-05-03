package com.skillsync.app.model;
import java.util.List;
public class JobResponse {
    private String jobTitle;
    private String companyName;
    private String city;
    private String jobType;
    private Double salaryFrom;
    private Double salaryTo;
    private String industry;
    private String applyUrl;
    private String source;
    private Double fitScore;
    private String fitLabel;
    private List<String> matchedSkills;
    private List<String> missingSkills;
    private String reason;
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getJobType() { return jobType; }
    public void setJobType(String jobType) { this.jobType = jobType; }
    public Double getSalaryFrom() { return salaryFrom; }
    public void setSalaryFrom(Double salaryFrom) { this.salaryFrom = salaryFrom; }
    public Double getSalaryTo() { return salaryTo; }
    public void setSalaryTo(Double salaryTo) { this.salaryTo = salaryTo; }
    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }
    public String getApplyUrl() { return applyUrl; }
    public void setApplyUrl(String applyUrl) { this.applyUrl = applyUrl; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public Double getFitScore() { return fitScore; }
    public void setFitScore(Double fitScore) { this.fitScore = fitScore; }
    public String getFitLabel() { return fitLabel; }
    public void setFitLabel(String fitLabel) { this.fitLabel = fitLabel; }
    public List<String> getMatchedSkills() { return matchedSkills; }
    public void setMatchedSkills(List<String> matchedSkills) { this.matchedSkills = matchedSkills; }
    public List<String> getMissingSkills() { return missingSkills; }
    public void setMissingSkills(List<String> missingSkills) { this.missingSkills = missingSkills; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}