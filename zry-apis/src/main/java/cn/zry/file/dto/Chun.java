package cn.zry.file.dto;

/**
 * author jbp
 * date ${date}
 * Created by Administrator on 2017/8/3.
 */
public class Chun {
    private String resumableChunkNumber;
    private long resumableChunkSize;
    private long resumableCurrentChunkSize;
    private long resumableTotalSize;
    private String resumableType;
    private String resumableIdentifier;
    private String resumableFilename;
    private String resumableRelativePath;


    public String getResumableChunkNumber() {
        return resumableChunkNumber;
    }

    public void setResumableChunkNumber(String resumableChunkNumber) {
        int l = 4-resumableChunkNumber.length();
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<l;i++){
            sb.append(0);
        }
        sb.append(resumableChunkNumber);
        this.resumableChunkNumber = sb.toString();
    }

    public long getResumableChunkSize() {
        return resumableChunkSize;
    }

    public void setResumableChunkSize(long resumableChunkSize) {
        this.resumableChunkSize = resumableChunkSize;
    }

    public long getResumableCurrentChunkSize() {
        return resumableCurrentChunkSize;
    }

    public void setResumableCurrentChunkSize(long resumableCurrentChunkSize) {
        this.resumableCurrentChunkSize = resumableCurrentChunkSize;
    }

    public long getResumableTotalSize() {
        return resumableTotalSize;
    }

    public void setResumableTotalSize(long resumableTotalSize) {
        this.resumableTotalSize = resumableTotalSize;
    }

    public String getResumableType() {
        return resumableType;
    }

    public void setResumableType(String resumableType) {
        this.resumableType = resumableType;
    }

    public String getResumableIdentifier() {
        return resumableIdentifier;
    }

    public void setResumableIdentifier(String resumableIdentifier) {
        this.resumableIdentifier = resumableIdentifier;
    }

    public String getResumableFilename() {
        return resumableFilename;
    }

    public void setResumableFilename(String resumableFilename) {
        this.resumableFilename = resumableFilename;
    }

    public String getResumableRelativePath() {
        return resumableRelativePath;
    }

    public void setResumableRelativePath(String resumableRelativePath) {
        this.resumableRelativePath = resumableRelativePath;
    }
}
