package com.prj.agile.repository;

import com.prj.agile.entity.client.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {

}
