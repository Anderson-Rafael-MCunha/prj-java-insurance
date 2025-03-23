package com.prj.agile.repository.insurance;

import com.prj.agile.entity.insurance.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
