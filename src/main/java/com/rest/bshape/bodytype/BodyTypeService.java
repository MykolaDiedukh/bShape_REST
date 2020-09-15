package com.rest.bshape.bodytype;


import com.rest.bshape.bodytype.domain.BodyType;
import com.rest.bshape.bodytype.domain.BodyTypeID;

import java.util.List;

public interface BodyTypeService {
     List<BodyType> findAll();

     BodyType findById(Long id);

     BodyTypeID create(BodyType bodyType); // sprawdzić //

     BodyType update(BodyType bodyType, Long id);

     void delete(Long id);

}
