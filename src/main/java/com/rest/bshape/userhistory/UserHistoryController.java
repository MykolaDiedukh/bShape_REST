package com.rest.bshape.userhistory;

import com.rest.bshape.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userHistory")
@CrossOrigin(origins = "http://localhost:4200")
public class UserHistoryController {

    private final UserHistoryService userHistoryService;

    public UserHistoryController(UserHistoryService userHistoryService) {
        this.userHistoryService = userHistoryService;
    }

    @GetMapping
    public List<UserHistoryDTO> findAll() {
        return this.userHistoryService.findAll();
    }


    @GetMapping("/{id}")
    public UserHistoryDTO findById(@PathVariable(value = "id") Long id) {
        return userHistoryService.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserHistory not found with id :" + id));
    }

    @PostMapping
    public UserHistoryID create(@RequestBody UserHistoryDTO userHistoryDTO) {
        return userHistoryService.create(userHistoryDTO).orElseThrow(() -> new ResourceNotFoundException("UserHistory not created"));
    }


    @PutMapping("/{id}")
    public UserHistoryDTO update(@RequestBody UserHistoryDTO userHistoryDTO, @PathVariable("id") Long id) {
        return userHistoryService.update(userHistoryDTO, id).orElseThrow(() -> new ResourceNotFoundException("UserHistory not found with id :" + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserHistoryID> delete(@PathVariable("id") Long id) {
        return this.userHistoryService.delete(id);
    }
}
