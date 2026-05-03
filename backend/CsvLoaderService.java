package com.skillsync.app.service;
import com.opencsv.CSVReader;
import com.skillsync.app.model.JobRecord;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class CsvLoaderService {
    private final List<JobRecord> allJobs = new ArrayList<>();
    @PostConstruct
    public void loadData() {
        try {
            loadIndeedData();
            loadNaukriData();
            System.out.println("Total jobs loaded into memory: " + allJobs.size());
        } catch (Exception e) {
            System.err.println("Error loading CSV data.");
            e.printStackTrace();
        }
    }
    public List<JobRecord> getAllJobs() { return allJobs; }
    private void loadIndeedData() throws Exception {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                getClass().getResourceAsStream("/data/indeed_jobs.csv")))) {
            String[] headers = reader.readNext();
            if (headers == null) return;
            List<String> headerList = Arrays.asList(headers);
            String[] line;
            while ((line = reader.readNext()) != null) {
                JobRecord job = new JobRecord();
                job.setSource("Indeed");
                job.setJobTitle(getValue(line, headerList, "Job Title"));
                job.setJobDescription(getValue(line, headerList, "Job Description"));
                job.setJobType(getValue(line, headerList, "Job Type"));
                job.setLocation(getValue(line, headerList, "Location"));
                job.setCity(getValue(line, headerList, "City"));
                job.setState(getValue(line, headerList, "State"));
                job.setIndustry(getValue(line, headerList, "Industry"));
                job.setCompanyName(getValue(line, headerList, "Company Name"));
                job.setApplyUrl(getValue(line, headerList, "Apply Url"));
                job.setUniqId(getValue(line, headerList, "Uniq Id"));
                job.setSalaryFrom(parseDouble(getValue(line, headerList, "Salary From")));
                job.setSalaryTo(parseDouble(getValue(line, headerList, "Salary To")));
                job.setExperienceRequired(extractExperienceRegex(job.getJobDescription()));
                allJobs.add(job);
            }
        }
    }
    private void loadNaukriData() throws Exception {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                getClass().getResourceAsStream("/data/naukri_jobs.csv")))) {
            String[] headers = reader.readNext();
            if (headers == null) return;
            List<String> headerList = Arrays.asList(headers);
            String[] line;
            while ((line = reader.readNext()) != null) {
                JobRecord job = new JobRecord();
                job.setSource("Naukri");
                job.setUniqId(getValue(line, headerList, "Uniq Id"));
                job.setJobTitle(getValue(line, headerList, "Job Title"));
                job.setExperienceRequired(getValue(line, headerList, "Job Experience Required"));
                job.setLocation(getValue(line, headerList, "Location"));
                job.setIndustry(getValue(line, headerList, "Industry"));
                String rawSkills = getValue(line, headerList, "Key Skills");
                if (rawSkills != null && !rawSkills.isEmpty()) {
                    job.setKeySkills(Arrays.asList(rawSkills.split("\\|")));
                }
                if (job.getLocation() != null && !job.getLocation().isEmpty()) {
                    job.setCity(job.getLocation().split(",")[0].trim());
                }
                String role = getValue(line, headerList, "Role");
                String funcArea = getValue(line, headerList, "Functional Area");
                job.setJobDescription("Role: " + role + ". Area: " + funcArea + ". Skills: " + rawSkills);
                allJobs.add(job);
            }
        }
    }
    private String getValue(String[] line, List<String> headers, String colName) {
        int index = headers.indexOf(colName);
        if (index >= 0 && index < line.length) { return line[index]; }
        return "";
    }
    private Double parseDouble(String val) {
        try { return Double.parseDouble(val.replaceAll("[^\\d.]", "")); }
        catch (Exception e) { return 0.0; }
    }
    private String extractExperienceRegex(String description) {
        if (description == null) return "";
        Pattern p = Pattern.compile("(\\d+)[\\s-]*(to|-)*[\\s-]*(\\d+)?\\s*(years?|yrs?)\\s*(of experience)?", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(description);
        if (m.find()) { return m.group(0); }
        return "";
    }
}