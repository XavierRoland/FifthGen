package com.fifthgen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fifthgen.domain.ProductInventory;

@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Integer> {

}
