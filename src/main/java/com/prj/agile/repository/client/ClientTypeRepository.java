package com.prj.agile.repository.client;

import com.prj.agile.entity.client.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientTypeRepository extends JpaRepository<ClientType, Integer> {

}
