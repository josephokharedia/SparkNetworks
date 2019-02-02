package com.starknetworks.boundarycontext.masterdatamanagement.integration;

import com.starknetworks.boundarycontext.masterdatamanagement.entity.EthnicityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EthnicityRepository extends JpaRepository<EthnicityEntity, Long> {
}
