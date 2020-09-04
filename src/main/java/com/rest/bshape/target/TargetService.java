package com.rest.bshape.target;

import com.rest.bshape.exeption.ResourceNotFoundException;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TargetService {

    private final TargetRepository targetRepository;

    public TargetService(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    public List<TargetDTO> findAll() {
        List<Target> optionalTarget = this.targetRepository.findAll();
        return optionalTarget.isEmpty() ? Collections.emptyList() : optionalTarget.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TargetDTO> findById(Long id) {
        Optional<Target> optionalTarget = targetRepository.findById(id);
        return optionalTarget.isEmpty() ? Optional.empty() : optionalTarget.map(this::convertToDTO);
    }

    public Optional<TargetID> create(TargetDTO targetDTO) {
        Target target = this.convertFromDTO(targetDTO);

        Target createdTarget = targetRepository.save(target);
        val targetID = new TargetID(createdTarget.getId());
        return Optional.of(targetID);
    }

    public Optional<TargetDTO> update(TargetDTO targetDTO, Long id) {
        Target target = this.convertFromDTO(targetDTO);

        Optional<Target> targetById = targetRepository.findById(id);
        if (targetById.isEmpty()) {
            return Optional.empty();
        }
        Target existingTarget = targetById.get();
        existingTarget.setFutureTarget(target.getFutureTarget());
        return Optional.of(this.convertToDTO(targetRepository.save(existingTarget)));
    }

    public ResponseEntity<TargetID> delete(Long id) {
        Target existingTarget = this.targetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found with id:" + id));
        this.targetRepository.delete(existingTarget);
        return ResponseEntity.ok().build();
    }

    private TargetDTO convertToDTO(Target target) {
        return TargetDTO.builder()
                .id(target.getId())
                .futureTarget(target.getFutureTarget())
                .build();
    }

    private Target convertFromDTO(TargetDTO targetDTO) {
        return Target.builder()
                .id(targetDTO.getId())
                .futureTarget(targetDTO.getFutureTarget())
                .build();
    }
}
