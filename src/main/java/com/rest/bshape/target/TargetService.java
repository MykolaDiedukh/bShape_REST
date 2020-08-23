package com.rest.bshape.target;

import com.rest.bshape.sevices.GenericService;
import com.rest.bshape.user.User;
import com.rest.bshape.exeption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TargetService implements GenericService<Target> {

    @Autowired
    private TargetRepository targetRepository;

    @Override
    public List<Target> findAll() {
        return this.targetRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
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
