package info.gucchi.sample.api.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {
    private final Path uploadDir;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.uploadDir = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.uploadDir);
        } catch (Exception e) {
            throw new FileStorageException("Could not create upload directory");
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Region region = Region.AP_NORTHEAST_1;
        S3Client s3 = S3Client.builder().region(region).build();
        String bucket = "gucchisk-sample-files";
        PutObjectRequest objectRequest = PutObjectRequest.builder().bucket(bucket).key(fileName).build();

        try {
//            ByteBuffer buffer = ByteBuffer.wrap(file.getBytes());
//            String str = buffer.array().toString();
            s3.putObject(objectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
            //s3.putObject(objectRequest, RequestBody.fromByteBuffer(buffer));
        } catch (IOException e) {
            throw new FileStorageException("Could not store file " + fileName);
        }
        return fileName;
//        try {
//            if (fileName.contains("..")) {
//                throw new FileStorageException("invalid path");
//            }
//            Path targetPath = this.uploadDir.resolve(fileName);
//            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
//            return fileName;
//        } catch (IOException e) {
//            throw new FileStorageException("Could not store file " + fileName);
//        }
    }

}
