package com.ra.projectmd3test.service.design;

import com.ra.projectmd3test.model.dto.BannerRequest;
import com.ra.projectmd3test.model.entity.Banner;

public interface IBannerService extends IGenericService<Banner,Integer>{
    void save(BannerRequest bannerRequest);
}
