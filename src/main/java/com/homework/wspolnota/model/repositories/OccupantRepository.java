package com.homework.wspolnota.model.repositories;

import com.homework.wspolnota.model.Occupant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccupantRepository extends JpaRepository<Occupant,Long> {



}
