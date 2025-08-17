package com.klef.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.dev.entity.Cricketer;
import com.klef.dev.repository.CricketerRepository;

@Service
public class CricketerServiceImpl implements CricketerService {

    @Autowired
    private CricketerRepository cricketerRepository;

    @Override
    public Cricketer addCricketer(Cricketer cricketer) {
        return cricketerRepository.save(cricketer);
    }

    @Override
    public List<Cricketer> getAllCricketers() {
        return cricketerRepository.findAll();
    }

    @Override
    public Cricketer getCricketerById(int id) {
        Optional<Cricketer> opt = cricketerRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public Cricketer updateCricketer(Cricketer cricketer) {
        return cricketerRepository.save(cricketer);
    }

    @Override
    public void deleteCricketerById(int id) {
        cricketerRepository.deleteById(id);
    }
}