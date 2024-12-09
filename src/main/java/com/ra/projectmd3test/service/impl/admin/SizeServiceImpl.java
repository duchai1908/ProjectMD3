package com.ra.projectmd3test.service.impl.admin;

import com.ra.projectmd3test.model.entity.Size;
import com.ra.projectmd3test.repository.design.admin.ISizeRepository;
import com.ra.projectmd3test.service.design.admin.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements ISizeService {
    @Autowired
    private ISizeRepository sizeRepository;
    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size findById(Integer id) {
        return sizeRepository.findById(id);
    }

    @Override
    public void save(Size size) {
        sizeRepository.save(size);
    }

    @Override
    public void deleteById(Integer t) {
        sizeRepository.deleteById(t);
    }
}
