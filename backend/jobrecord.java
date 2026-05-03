package com.skillsync.app.model;
import java.util.ArrayList;
import java.util.List;
public class JobRecord {
    private String uniqId;
    private String source;
    private String jobTitle;
    private String jobDescription;
    private String jobType;
    private String location;
    private String city;
    private String state;
    private Double salaryFrom;
    private Double salaryTo;
    private String industry;
    private String companyName;
    private String applyUrl;
    private List<String> keySkills = new ArrayList<>();
    private String experienceRequired;
    public String getUniqId() { return uniqId; }
    public void setUniqId(String uniqId) { this.uniqId = uniqId; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getJobDescription() { return jobDescription; }
    public void setJobDescription(String jobDescription) { this.jobDescription = jobDescription; }
    public String getJobType() { return jobType; }
    public void setJobType(String jobType) { this.jobType = jobType; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public Double getSalaryFrom() { return salaryFrom; }
    public void setSalaryFrom(Double salaryFrom) { this.salaryFrom = salaryFrom; }
    public Double getSalaryTo() { return salaryTo; }
    public void setSalaryTo(Double salaryTo) { this.salaryTo = salaryTo; }
    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getApplyUrl() { return applyUrl; }
    public void setApplyUrl(String applyUrl) { this.applyUrl = applyUrl; }
    public List<String> getKeySkills() { return keySkills; }
    public void setKeySkills(List<String> keySkills) { this.keySkills = keySkills; }
    public String getExperienceRequired() { return experienceRequired; }
    public void setExperienceRequired(String experienceRequired) { this.experienceRequired = experienceRequired; }
}