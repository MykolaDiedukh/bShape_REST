package com.rest.bshape.sevices;

import com.rest.bshape.entity.BodyType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BodyTypeService implements MainService<BodyType> {
    @Override
    public List<BodyType> findAll() {
        return null;
    }

    @Override
    public BodyType findById(Long id) {
        return null;
    }

    @Override
    public BodyType create(BodyType bodyType) {
        return null;
    }

    @Override
    public BodyType update(BodyType bodyType, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<BodyType> delete(Long id) {
        return null;
    }
}
