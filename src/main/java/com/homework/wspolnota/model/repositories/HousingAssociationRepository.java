package com.homework.wspolnota.model.repositories;

import com.homework.wspolnota.model.HousingAssociation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingAssociationRepository extends JpaRepository<HousingAssociation, Long> {


}
