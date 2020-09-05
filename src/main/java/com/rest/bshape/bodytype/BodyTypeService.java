package com.rest.bshape.bodytype;

import com.rest.bshape.exception.ResourceNotFoundException;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class BodyTypeService {

    private final BodyTypeRepository bodyTypeRepository;

    public BodyTypeService(BodyTypeRepository bodyTypeRepository) {
        this.bodyTypeRepository = bodyTypeRepository;
    }

    public List<BodyTypeDTO> findAll() {
        List<BodyType> optionalAllBodyType = this.bodyTypeRepository.findAll();
        return optionalAllBodyType.isEmpty() ? Collections.emptyList() : optionalAllBodyType.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<BodyTypeDTO> findById(Long id) {
        Optional<BodyType> optionalBodyType = bodyTypeRepository.findById(id);
        return optionalBodyType.isEmpty() ? Optional.empty() : optionalBodyType.map(this::convertToDTO);
    }


    public Optional<BodyTypeID> create(BodyTypeDTO bodyTypeDTO) {
        BodyType bodyType = this.convertFromDTO(bodyTypeDTO);

        BodyType createdBodyType = bodyTypeRepository.save(bodyType);
        val bodyTypeID = new BodyTypeID(createdBodyType.getId());
        return Optional.of(bodyTypeID);
    }

    public Optional<BodyTypeDTO> update(BodyTypeDTO bodyTypeDTO, Long id) {
        BodyType bodyType = this.convertFromDTO(bodyTypeDTO);

        Optional<BodyType> bodyTypeById = bodyTypeRepository.findById(id);
        if (bodyTypeById.isEmpty()) {
            return Optional.empty();
        }
        BodyType existingBodyType = bodyTypeById.get();
        existingBodyType.setTypeOfBody(bodyType.getTypeOfBody());
        return Optional.of(this.convertToDTO(bodyTypeRepository.save(existingBodyType)));
    }

    public ResponseEntity<BodyTypeID> delete(Long id) {
        BodyType existingBodyType = this.bodyTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BodyType not found with id:" + id));
        this.bodyTypeRepository.delete(existingBodyType);
        return ResponseEntity.ok().build();
    }

    private BodyTypeDTO convertToDTO(BodyType bodyType) {
        return BodyTypeDTO.builder()
                .id(bodyType.getId())
                .typeOfBody(bodyType.getTypeOfBody())
                .build();
    }

    private BodyType convertFromDTO(BodyTypeDTO bodyTypeDTO) {
        return BodyType.builder()
                .id(bodyTypeDTO.getId())
                .typeOfBody(bodyTypeDTO.getTypeOfBody())
                .build();
    }
}
