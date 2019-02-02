package com.starknetworks.boundarycontext.masterdatamanagement.integration;

import com.starknetworks.boundarycontext.masterdatamanagement.entity.ReligionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReligionRepository extends JpaRepository<ReligionEntity, Long> {
}
