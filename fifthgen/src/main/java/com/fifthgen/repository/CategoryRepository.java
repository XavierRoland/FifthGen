package com.fifthgen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fifthgen.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findById(int categoryId);
}
