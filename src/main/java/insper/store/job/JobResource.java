package insper.store.job;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Job", description = "Job API")
public class JobResource implements JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/jobs/info")
    @Operation(summary = "Info", description = "Job API Info")
    public ResponseEntity<Map<String, String>> info() {
        return new ResponseEntity<Map<String, String>>(
            Map.ofEntries(
                Map.entry("microservice.name", JobApplication.class.getSimpleName()),
                Map.entry("os.arch", System.getProperty("os.arch")),
                Map.entry("os.name", System.getProperty("os.name")),
                Map.entry("os.version", System.getProperty("os.version")),
                Map.entry("file.separator", System.getProperty("file.separator")),
                Map.entry("java.class.path", System.getProperty("java.class.path")),
                Map.entry("java.home", System.getProperty("java.home")),
                Map.entry("java.vendor", System.getProperty("java.vendor")),
                Map.entry("java.vendor.url", System.getProperty("java.vendor.url")),
                Map.entry("java.version", System.getProperty("java.version")),
                Map.entry("line.separator", System.getProperty("line.separator")),
                Map.entry("path.separator", System.getProperty("path.separator")),
                Map.entry("user.dir", System.getProperty("user.dir")),
                Map.entry("user.home", System.getProperty("user.home")),
                Map.entry("user.name", System.getProperty("user.name")),
                Map.entry("jar", new java.io.File(
                    JobApplication.class.getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .getPath()
                    ).toString())
            ), HttpStatus.OK
        );
    }

    
    @Override
    @Operation(summary = "Create Job", description = "Create Job") 
    public ResponseEntity<JobOut> create(JobIn in) {
        // parser
        Job job = JobParser.to(in);
        // insert using service
        job = jobService.create(job);
        // return
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(job.id())
                .toUri())
            .body(JobParser.to(job));
    }

    @Override
    @Operation(summary = "Update", description = "Update Job")
    public ResponseEntity<JobOut> update(String id, JobIn in) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }


    @Override
    @Operation(summary = "Read", description = "Read Job")
    public ResponseEntity<JobOut> read(String idJob) {
        final JobOut job= JobOut.builder()
            .id(idJob)
            .build();
        return ResponseEntity.ok(job);
    }
    
    @GetMapping("/jobs-test")
    @Operation(summary = "Test", description = "Test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test");
    }

}
