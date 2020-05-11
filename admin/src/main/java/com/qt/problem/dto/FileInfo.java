package com.qt.problem.dto;

import lombok.*;
import org.springframework.core.io.Resource;

@Setter
@Getter
@NoArgsConstructor
public class FileInfo {
    @NonNull
    private String contentDisposition;

    @NonNull
    private String contentLength;

    @NonNull
    private Resource resource;

    @Builder
    public FileInfo(@NonNull String contentDisposition, @NonNull String contentLength, @NonNull Resource resource) {
        this.contentDisposition = contentDisposition;
        this.contentLength = contentLength;
        this.resource = resource;
    }
}
