package com.skillsync.app.service;
import com.skillsync.app.model.JobRecord;
import com.skillsync.app.model.JobResponse;
import com.skillsync.app.model.UserProfileRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class JobMatchingService {
    private final CsvLoaderService csvLoaderService;
    public JobMatchingService(CsvLoaderService csvLoaderService) {
        this.csvLoaderService = csvLoaderService;
    }
    public List<JobResponse> matchJobs(UserProfileRequest request) {
        List<JobRecord> allJobs = csvLoaderService.getAllJobs();
        List<JobResponse> scoredJobs = new ArrayList<>();
        for (JobRecord job : allJobs) {
            if (request.getSource() != null && !request.getSource().equalsIgnoreCase("All")
                && !request.getSource().equalsIgnoreCase(job.getSource())) continue;
            if (request.getMinSalary() != null && job.getSalaryFrom() != null
                && job.getSalaryFrom() > 0 && job.getSalaryFrom() < request.getMinSalary()) continue;
            scoredJobs.add(calculateFitScore(request, job));
        }
        scoredJobs.sort(Comparator.comparing(JobResponse::getFitScore).reversed());
        return scoredJobs.stream().limit(20).collect(Collectors.toList());
    }
    private JobResponse calculateFitScore(UserProfileRequest req, JobRecord job) {
        double score = 0;
        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();
        if (req.getSkills() != null && !req.getSkills().isEmpty()) {
            int matchCount = 0;
            String descLower = job.getJobDescription() != null ? job.getJobDescription().toLowerCase() : "";
            for (String skill : req.getSkills()) {
                boolean matched = false;
                if ("Naukri".equals(job.getSource()) && job.getKeySkills() != null) {
                    matched = job.getKeySkills().stream().anyMatch(s -> s.toLowerCase().contains(skill.toLowerCase()));
                } else {
                    matched = descLower.contains(skill.toLowerCase());
                }
                if (matched) { matchCount++; matchedSkills.add(skill); }
                else { missingSkills.add(skill); }
            }
            score += ((double) matchCount / req.getSkills().size()) * 50.0;
        }
        JobResponse response = new JobResponse();
        response.setJobTitle(job.getJobTitle());
        response.setCompanyName(job.getCompanyName());
        response.setCity(job.getCity());
        response.setJobType(job.getJobType());
        response.setSalaryFrom(job.getSalaryFrom());
        response.setSalaryTo(job.getSalaryTo());
        response.setIndustry(job.getIndustry());
        response.setApplyUrl(job.getApplyUrl());
        response.setSource(job.getSource());
        response.setFitScore(Math.round(score * 10.0) / 10.0);
        response.setFitLabel(score >= 80 ? "High" : score >= 50 ? "Good" : score >= 30 ? "Moderate" : "Low");
        response.setMatchedSkills(matchedSkills);
        response.setMissingSkills(missingSkills);
        response.setReason(String.format("Matched %d/%d key skills.", matchedSkills.size(),
                (req.getSkills() != null ? req.getSkills().size() : 0)));
        return response;
    }
}