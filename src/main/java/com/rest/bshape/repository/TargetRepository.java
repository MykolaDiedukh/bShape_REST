package com.rest.bshape.repository;

import com.rest.bshape.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface TargetRepository extends JpaRepository<Target, Long> {
}
