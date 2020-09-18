package com.rest.bshape.bodytype.impl;

import com.rest.bshape.bodytype.BodyTypeRepository;
import com.rest.bshape.bodytype.BodyTypeService;
import com.rest.bshape.bodytype.domain.BodyType;
import com.rest.bshape.bodytype.domain.BodyTypeID;
import com.rest.bshape.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BodyTypeServiceImpl implements BodyTypeService {

    private final BodyTypeRepository bodyTypeRepository;

    public BodyTypeServiceImpl(BodyTypeRepository bodyTypeRepository) {
        this.bodyTypeRepository = bodyTypeRepository;
    }

    @Override
    public List<BodyType> findAll() {
        return this.bodyTypeRepository.findAll();

    }

    @Override
    public BodyType findById(Long id) {
        return bodyTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BodyType not found with id :" + id));
    }

    // usunalem Optionala z metody Create
    @Override
    public BodyTypeID create(BodyType bodyType) {
        bodyType = bodyTypeRepository.save(bodyType);
        return new BodyTypeID(bodyType.getId());
    }

    // ponownie usunąlem optional
    @Override
    public BodyType update(BodyType bodyType, Long id) {

        BodyType bodyTypeById = bodyTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BodyType not found with id :" + id));

        bodyTypeById.setTypeOfBody(bodyType.getTypeOfBody());
        return bodyTypeRepository.save(bodyTypeById);
    }

    // usunalem defaultowa metode ze springa
    @Override
    public void delete(Long id) {
     /*   BodyType existingBodyType = this.bodyTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BodyType not found with id:" + id));*/
        System.out.println("test");
        this.bodyTypeRepository.deleteById(id);
    }

 /*   @Override
    public void delete(Long id) {
        this.bodyTypeRepository.deleteById(id);
    }
*/

}
