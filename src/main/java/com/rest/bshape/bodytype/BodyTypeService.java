package com.rest.bshape.bodytype;


import java.util.List;

public interface BodyTypeService {
     List<BodyTypeDTO> findAll();

     BodyTypeDTO findById(Long id);

     BodyTypeID create(BodyTypeDTO bodyTypeDTO);

     BodyTypeDTO update(BodyTypeDTO bodyTypeDTO, Long id);

     void delete(Long id);

}
