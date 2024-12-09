package com.ra.projectmd3test.service.design.admin;

import com.ra.projectmd3test.model.dto.admin.BannerRequest;
import com.ra.projectmd3test.model.entity.Banner;

public interface IBannerService extends IGenericService<Banner,Integer>{
    void save(BannerRequest bannerRequest);
}
