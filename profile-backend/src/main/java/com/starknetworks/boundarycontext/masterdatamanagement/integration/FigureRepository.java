package com.starknetworks.boundarycontext.masterdatamanagement.integration;

import com.starknetworks.boundarycontext.masterdatamanagement.entity.FigureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FigureRepository extends JpaRepository<FigureEntity, Long> {
}
