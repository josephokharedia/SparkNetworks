package com.starknetworks.boundarycontext.masterdatamanagement.integration;

import com.starknetworks.boundarycontext.masterdatamanagement.entity.MaritalStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaritalStatusRepository extends JpaRepository<MaritalStatusEntity, Long> {
}
