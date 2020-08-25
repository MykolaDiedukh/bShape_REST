package com.rest.bshape.bodytype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
interface BodyTypeRepository extends JpaRepository<BodyType, Long> {
}
