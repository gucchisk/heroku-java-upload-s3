package info.gucchi.sample.api.upload.s3;

import lombok.Data;

@Data
public class UploadResponse {
    private String fileName;
    private String fileType;
    private long size;
    private int code;
    private String status;
    public UploadResponse(String fileName, String fileType, long size, int code, String status) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
        this.code = code;
        this.status = status;
    }
}
