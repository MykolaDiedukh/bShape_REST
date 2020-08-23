package com.rest.bshape.userhistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userHistory")
@CrossOrigin(origins = "http://localhost:4200")
public class UserHistoryController {

    @Autowired
    private UserHistoryService userHistoryService;

    @GetMapping
    public List<UserHistory> findAll() {
        return this.userHistoryService.findAll();
    }


    @GetMapping("/{id}")
    public UserHistory findById(@PathVariable(value = "id") Long id) {
        return this.userHistoryService.findById(id);
    }

    @PostMapping
    public UserHistory create(@RequestBody UserHistory userHistory) {
        return this.userHistoryService.create(userHistory);
    }


    @PutMapping("/{id}")
    public UserHistory update(@RequestBody UserHistory userHistory, @PathVariable("id") Long id) {
        return this.userHistoryService.update(userHistory, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserHistory> delete(@PathVariable("id") Long id) {
        return this.userHistoryService.delete(id);
    }
}
