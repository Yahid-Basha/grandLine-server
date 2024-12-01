package org.yahid.grandLine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yahid.grandLine.model.Business;
import org.yahid.grandLine.model.BusinessInfo;

import java.util.Optional;
import java.util.List;

public interface BusinessRepository extends JpaRepository<Business, Integer> {
    Optional<BusinessInfo> getBusinessesByEmail(String email);

    List<BusinessInfo> getBusinessesByBusinessNameContaining(String businessName);
}