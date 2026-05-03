package com.skillsync.app.model;
import java.util.List;
public class UserProfileRequest {
    private List<String> skills;
    private List<String> courses;
    private Integer experienceYears;
    private String education;
    private String preferredTitle;
    private String preferredCity;
    private Double minSalary;
    private String jobType;
    private String industry;
    private String source;
    private String sortBy;
    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }
    public List<String> getCourses() { return courses; }
    public void setCourses(List<String> courses) { this.courses = courses; }
    public Integer getExperienceYears() { return experienceYears; }
    public void setExperienceYears(Integer experienceYears) { this.experienceYears = experienceYears; }
    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }
    public String getPreferredTitle() { return preferredTitle; }
    public void setPreferredTitle(String preferredTitle) { this.preferredTitle = preferredTitle; }
    public String getPreferredCity() { return preferredCity; }
    public void setPreferredCity(String preferredCity) { this.preferredCity = preferredCity; }
    public Double getMinSalary() { return minSalary; }
    public void setMinSalary(Double minSalary) { this.minSalary = minSalary; }
    public String getJobType() { return jobType; }
    public void setJobType(String jobType) { this.jobType = jobType; }
    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }
}