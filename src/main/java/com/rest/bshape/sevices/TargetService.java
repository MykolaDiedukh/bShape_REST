package com.rest.bshape.sevices;

import com.rest.bshape.entity.Target;
import com.rest.bshape.exeption.ResourceNotFoundException;
import com.rest.bshape.repository.TargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetService implements MainService<Target> {

    @Autowired
    private TargetRepository targetRepository;

    @Override
    public List<Target> findAll() {
        return this.targetRepository.findAll();
    }

    @Override
    public Target findById(Long id) {
        return this.targetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found with id :" + id));
    }

    @Override
    public Target create(Target target) {
        return this.targetRepository.save(target);
    }

    @Override
    public Target update(Target target, Long id) {
        Target existingTarget = this.targetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found with id:" + id));
        existingTarget.setFutureTarget(target.getFutureTarget());
        return this.targetRepository.save(existingTarget);
    }

    @Override
    public ResponseEntity<Target> delete(Long id) {
        Target existingTarget = this.targetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found with id:" + id));
        this.targetRepository.delete(existingTarget);
        return ResponseEntity.ok().build();
    }
}