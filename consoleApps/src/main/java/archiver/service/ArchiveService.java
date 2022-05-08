package archiver.service;

import org.springframework.lang.Nullable;

import java.util.Optional;

public interface ArchiveService {

    Optional<String> zip(String source);

    Optional<String> unzip(String source);
}
