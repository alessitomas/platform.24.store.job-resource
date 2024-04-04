package insper.store.job;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class JobService {

    @Autowired
    private JobRepository JobRepository;

    public Job create(Job in) {
        return JobRepository.save(new JobModel(in)).to();
    }

    public Job read(@NonNull String id) {
        return JobRepository.findById(id).map(JobModel::to).orElse(null);
    }
    
}
