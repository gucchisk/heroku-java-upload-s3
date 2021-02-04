package info.gucchi.sample.api.upload;

import lombok.Data;

@Data
public class UploadResponse {
    private String fileName;
    private String fileType;
    private long size;
    public UploadResponse(String fileName, String fileType, long size) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
    }
}
