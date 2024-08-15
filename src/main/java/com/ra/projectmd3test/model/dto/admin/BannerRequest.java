package com.ra.projectmd3test.model.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BannerRequest {
    private String title;
    private String description;
    private MultipartFile image;
    private Boolean status;
}
