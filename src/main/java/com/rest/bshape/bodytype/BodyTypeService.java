package com.rest.bshape.bodytype;

import com.rest.bshape.bodytype.converter.BodyTypeConverter;
import com.rest.bshape.exception.ResourceNotFoundException;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.rest.bshape.bodytype.converter.BodyTypeConverter.convertFromDTO;
import static com.rest.bshape.bodytype.converter.BodyTypeConverter.convertToDTO;

@Service
class BodyTypeService {

    private final BodyTypeRepository bodyTypeRepository;

    public BodyTypeService(BodyTypeRepository bodyTypeRepository) {
        this.bodyTypeRepository = bodyTypeRepository;
    }

    public List<BodyTypeDTO> findAll() {
        List<BodyType> optionalAllBodyType = this.bodyTypeRepository.findAll();
        return optionalAllBodyType.isEmpty() ? Collections.emptyList() : optionalAllBodyType.stream()
                .map(BodyTypeConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public BodyTypeDTO findById(Long id) {
        return bodyTypeRepository.findById(id)
                .map(BodyTypeConverter::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("BodyType not found with id :" + id));
    }


    public Optional<BodyTypeID> create(BodyTypeDTO bodyTypeDTO) {
        BodyType bodyType = convertFromDTO(bodyTypeDTO);

        BodyType createdBodyType = bodyTypeRepository.save(bodyType);
        val bodyTypeID = new BodyTypeID(createdBodyType.getId());
        return Optional.of(bodyTypeID);
    }

    public Optional<BodyTypeDTO> update(BodyTypeDTO bodyTypeDTO, Long id) {
        BodyType bodyType = convertFromDTO(bodyTypeDTO);

        Optional<BodyType> bodyTypeById = bodyTypeRepository.findById(id);
        if (bodyTypeById.isEmpty()) {
            return Optional.empty();
        }
        BodyType existingBodyType = bodyTypeById.get();
        existingBodyType.setTypeOfBody(bodyType.getTypeOfBody());
        return Optional.of(convertToDTO(bodyTypeRepository.save(existingBodyType)));
    }

    public ResponseEntity<BodyTypeID> delete(Long id) {
        BodyType existingBodyType = this.bodyTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BodyType not found with id:" + id));
        this.bodyTypeRepository.delete(existingBodyType);
        return ResponseEntity.ok().build();
    }

}
