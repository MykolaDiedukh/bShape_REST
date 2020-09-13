package com.rest.bshape.bodytype.impl;

import com.rest.bshape.bodytype.*;
import com.rest.bshape.bodytype.converter.BodyTypeConverter;
import com.rest.bshape.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.rest.bshape.bodytype.converter.BodyTypeConverter.convertFromDTO;
import static com.rest.bshape.bodytype.converter.BodyTypeConverter.convertToDTO;

@Service
class BodyTypeServiceImpl implements BodyTypeService {

    private final BodyTypeRepository bodyTypeRepository;

    public BodyTypeServiceImpl(BodyTypeRepository bodyTypeRepository) {
        this.bodyTypeRepository = bodyTypeRepository;
    }

    @Override
    public List<BodyTypeDTO> findAll() {
        List<BodyType> optionalAllBodyType = this.bodyTypeRepository.findAll();
        return optionalAllBodyType.isEmpty() ? Collections.emptyList() : optionalAllBodyType.stream()
                .map(BodyTypeConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BodyTypeDTO findById(Long id) {
        return bodyTypeRepository.findById(id)
                .map(BodyTypeConverter::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("BodyType not found with id :" + id));
    }

    // usunalem Optionala z metody Create
    @Override
    public BodyTypeID create(BodyTypeDTO bodyTypeDTO) {
        BodyType bodyType = convertFromDTO(bodyTypeDTO);

        BodyType createdBodyType = bodyTypeRepository.save(bodyType);
        return new BodyTypeID(createdBodyType.getId());
    }

    // ponownie usunąlem optional
    @Override
    public BodyTypeDTO update(BodyTypeDTO bodyTypeDTO, Long id) {
        BodyType bodyType = convertFromDTO(bodyTypeDTO);

        BodyType bodyTypeById = bodyTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BodyType not found with id :" + id));

        bodyTypeById.setTypeOfBody(bodyType.getTypeOfBody());
        return (convertToDTO(bodyTypeRepository.save(bodyTypeById)));
    }

    // usunalem defaultowa metode ze springa
    @Override
    public void delete(Long id) {
        BodyType existingBodyType = this.bodyTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BodyType not found with id:" + id));
        this.bodyTypeRepository.delete(existingBodyType);
    }

}
