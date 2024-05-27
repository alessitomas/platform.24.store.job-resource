package insper.store.job;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class JobService {

    @Autowired
    private JobRepository JobRepository;


    @Cacheable(key = "'ALLJobs'", value = "allJobs")
    public Iterable<JobModel> readAll() {
        return JobRepository.findAll();
    }

    public Job create(Job in) {
        return JobRepository.save(new JobModel(in)).to();
    }

    @Cacheable(key = "#id", value = "job")
    public Job read(@NonNull String id) {
        return JobRepository.findById(id).map(JobModel::to).orElse(null);
    }
    
}
