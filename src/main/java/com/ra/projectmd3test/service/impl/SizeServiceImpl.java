package com.ra.projectmd3test.service.impl;

import com.ra.projectmd3test.model.entity.Size;
import com.ra.projectmd3test.repository.design.ISizeRepository;
import com.ra.projectmd3test.repository.impl.SizeRepository;
import com.ra.projectmd3test.service.design.ISizeService;
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
