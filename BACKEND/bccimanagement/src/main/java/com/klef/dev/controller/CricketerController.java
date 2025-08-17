package com.klef.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.klef.dev.entity.Cricketer;
import com.klef.dev.service.CricketerService;

@RestController
@RequestMapping("/cricketerapi/")
@CrossOrigin(origins = "*")
public class CricketerController {

    @Autowired
    private CricketerService cricketerService;
    
    @GetMapping("/")
    public String home() {
        return "Cricketer API is running";
    }
    
    @PostMapping("/add")
    public ResponseEntity<Cricketer> addCricketer(@RequestBody Cricketer cricketer) {
        Cricketer savedCricketer = cricketerService.addCricketer(cricketer);
        return new ResponseEntity<>(savedCricketer, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cricketer>> getAllCricketers() {
        List<Cricketer> cricketers = cricketerService.getAllCricketers();
        return new ResponseEntity<>(cricketers, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCricketerById(@PathVariable int id) {
        Cricketer cricketer = cricketerService.getCricketerById(id);
        if (cricketer != null) {
            return new ResponseEntity<>(cricketer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cricketer with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCricketer(@RequestBody Cricketer cricketer) {
        Cricketer existing = cricketerService.getCricketerById(cricketer.getId());
        if (existing != null) {
            Cricketer updatedCricketer = cricketerService.updateCricketer(cricketer);
            return new ResponseEntity<>(updatedCricketer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot update. Cricketer with ID " + cricketer.getId() + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCricketer(@PathVariable int id) {
        Cricketer existing = cricketerService.getCricketerById(id);
        if (existing != null) {
            cricketerService.deleteCricketerById(id);
            return new ResponseEntity<>("Cricketer with ID " + id + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete. Cricketer with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}