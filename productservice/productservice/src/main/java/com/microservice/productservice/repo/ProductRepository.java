package com.microservice.productservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.productservice.enity.Product;


@Repository
public interface ProductRepository  extends JpaRepository<Product,Long>{

}
