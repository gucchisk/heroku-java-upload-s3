package info.gucchi.sample.api.upload.s3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.logging.Logger;

@RestController
public class UploadFileController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public UploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
        Logger logger = Logger.getLogger("upload");
        logger.info("/upload:" + file.getOriginalFilename());
        FileStorageService.StoreFileResponse response = fileStorageService.storeFile(file);
        String fileName = response.getFileName();
        int code = response.getS3response().sdkHttpResponse().statusCode();
        String text = response.getS3response().sdkHttpResponse().statusText().orElse("");
        return new UploadResponse(fileName, file.getContentType(), file.getSize(), code, text);
    }
}
