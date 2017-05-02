package com.fifthgen.service;

import java.util.List;

import com.fifthgen.dto.ProductDto;

public interface ProductService {

	public List<ProductDto> getAllProducts();

	public boolean addProduct(ProductDto productDto);

	public boolean updateProduct(ProductDto productDto);

	public List<ProductDto> searchProducts(String searchKey);
}
