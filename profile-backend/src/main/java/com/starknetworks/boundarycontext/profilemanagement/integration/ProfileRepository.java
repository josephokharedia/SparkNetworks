package com.starknetworks.boundarycontext.profilemanagement.integration;

import com.starknetworks.boundarycontext.profilemanagement.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

}
