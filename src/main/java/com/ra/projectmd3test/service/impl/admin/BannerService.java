package com.ra.projectmd3test.service.impl.admin;

import com.ra.projectmd3test.model.dto.admin.BannerRequest;
import com.ra.projectmd3test.model.entity.Banner;
import com.ra.projectmd3test.repository.design.admin.IBannerRepository;
import com.ra.projectmd3test.service.UploadService;
import com.ra.projectmd3test.service.design.admin.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService implements IBannerService {
    @Autowired
    private IBannerRepository bannerRepository;

    @Autowired
    private UploadService uploadService;

    @Override
    public List<Banner> findAll() {
        return bannerRepository.findAll();
    }

    @Override
    public Banner findById(Integer id) {
        return bannerRepository.findById(id);
    }

    @Override
    public void save(Banner banner) {
        bannerRepository.save(banner);
    }

    @Override
    public void deleteById(Integer t) {
        bannerRepository.deleteById(t);
    }

    @Override
    public void save(BannerRequest bannerRequest) {
        Banner banner = null;
        if(bannerRequest.getImage().getSize()>0){
            String imageMainUrl = uploadService.uploadFileToServer(bannerRequest.getImage());
            banner = Banner.builder()
                    .title(bannerRequest.getTitle())
                    .description(bannerRequest.getDescription())
                    .image(imageMainUrl)
                    .status(true)
                    .build();
            bannerRepository.save(banner);
        }
    }
}
