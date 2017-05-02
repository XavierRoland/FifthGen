package com.fifthgen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fifthgen.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findById(int productId);

	List<Product> findByProductNameIgnoreCaseContaining(String searchKey);
}
