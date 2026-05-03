package com.skillsync.app.controller;

import com.skillsync.app.model.JobRecord;
import com.skillsync.app.model.JobResponse;
import com.skillsync.app.model.UserProfileRequest;
import com.skillsync.app.service.CsvLoaderService;
import com.skillsync.app.service.JobMatchingService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class JobController {

    private final JobMatchingService matchingService;
    private final CsvLoaderService csvLoaderService;

    public JobController(JobMatchingService matchingService, CsvLoaderService csvLoaderService) {
        this.matchingService = matchingService;
        this.csvLoaderService = csvLoaderService;
    }

    // POST /api/match-jobs — matches jobs based on user profile
    @PostMapping("/match-jobs")
    public Map<String, Object> matchJobs(@RequestBody UserProfileRequest request) {
        List<JobResponse> topJobs = matchingService.matchJobs(request);
        Map<String, Object> response = new HashMap<>();
        response.put("totalMatched", topJobs.size());
        response.put("jobs", topJobs);
        return response;
    }

    // GET /api/jobs/filter — filters jobs by title, city, source
    @GetMapping("/jobs/filter")
    public List<JobRecord> filterJobs(
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "") String city,
            @RequestParam(required = false, defaultValue = "") String source) {
        return csvLoaderService.getAllJobs().stream()
                .filter(job -> title.isEmpty() || (job.getJobTitle() != null && job.getJobTitle().toLowerCase().contains(title.toLowerCase())))
                .filter(job -> city.isEmpty() || (job.getCity() != null && job.getCity().toLowerCase().contains(city.toLowerCase())))
                .filter(job -> source.isEmpty() || job.getSource().equalsIgnoreCase(source))
                .limit(50)
                .collect(Collectors.toList());
    }

    // GET /api/jobs — simple job search by keyword (used by frontend search bar)
    @GetMapping("/jobs")
    public Map<String, Object> getJobs(@RequestParam(required = false, defaultValue = "") String q) {
        List<JobRecord> jobs = csvLoaderService.getAllJobs().stream()
                .filter(job -> q.isEmpty() ||
                        (job.getJobTitle() != null && job.getJobTitle().toLowerCase().contains(q.toLowerCase())) ||
                        (job.getCity() != null && job.getCity().toLowerCase().contains(q.toLowerCase())))
                .limit(50)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("totalMatched", jobs.size());
        response.put("jobs", jobs);
        return response;
    }
}