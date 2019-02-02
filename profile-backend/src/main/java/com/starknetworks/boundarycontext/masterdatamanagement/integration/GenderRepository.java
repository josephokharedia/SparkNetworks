package com.starknetworks.boundarycontext.masterdatamanagement.integration;

import com.starknetworks.boundarycontext.masterdatamanagement.entity.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
}
