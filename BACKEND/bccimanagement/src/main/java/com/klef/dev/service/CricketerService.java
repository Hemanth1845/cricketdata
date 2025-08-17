package com.klef.dev.service;

import java.util.List;
import com.klef.dev.entity.Cricketer;

public interface CricketerService {
    Cricketer addCricketer(Cricketer cricketer);
    List<Cricketer> getAllCricketers();
    Cricketer getCricketerById(int id);
    Cricketer updateCricketer(Cricketer cricketer);
    void deleteCricketerById(int id);
}