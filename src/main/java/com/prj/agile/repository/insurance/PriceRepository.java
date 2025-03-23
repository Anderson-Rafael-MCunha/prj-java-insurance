package com.prj.agile.repository.insurance;

import com.prj.agile.entity.insurance.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer>{

    Optional<Price> findByProtocol(String protocol);

}
