package com.ra.projectmd3test.service.impl.admin;

import com.ra.projectmd3test.model.entity.Color;
import com.ra.projectmd3test.repository.design.admin.IColorRepository;
import com.ra.projectmd3test.service.design.admin.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService implements IColorService {
    @Autowired
    private IColorRepository colorRepository;
    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Color findById(Integer id) {
        return colorRepository.findById(id);
    }

    @Override
    public void save(Color color) {
        colorRepository.save(color);
    }

    @Override
    public void deleteById(Integer t) {
        colorRepository.deleteById(t);
    }
}
