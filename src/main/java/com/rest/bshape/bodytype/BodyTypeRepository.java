package com.rest.bshape.bodytype;

import com.rest.bshape.bodytype.domain.BodyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:4200")
public interface  BodyTypeRepository extends JpaRepository<BodyType, Long> {
}
