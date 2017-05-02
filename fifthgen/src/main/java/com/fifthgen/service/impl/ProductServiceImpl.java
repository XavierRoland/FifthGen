package com.fifthgen.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fifthgen.domain.Category;
import com.fifthgen.domain.Product;
import com.fifthgen.domain.ProductInventory;
import com.fifthgen.dto.ProductDto;
import com.fifthgen.repository.CategoryRepository;
import com.fifthgen.repository.ProductInventoryRepository;
import com.fifthgen.repository.ProductRepository;
import com.fifthgen.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Resource
	CategoryRepository categoryRepository;

	@Resource
	ProductRepository productRepository;

	@Resource
	ProductInventoryRepository productInventoryRepository;

	@Override
	@Transactional
	public boolean addProduct(ProductDto productDto) {

		if (productDto != null) {
			Product product = new Product();
			Category category = getCategoryById(productDto.getCategoryId());
			if (category != null) {
				product.setCategory(category);
			}

			product.setProductName(productDto.getProductName());
			product.setProductDesc(productDto.getProductDesc());
			product.setProductPrice(productDto.getProductPrice());
			product.setProductQuantity(productDto.getProductQuantity());
			product.setCreatedDate(new Date());
			product.setModifiedDate(new Date());

			Product product2 = productRepository.save(product);

			ProductInventory productInventory = new ProductInventory();
			productInventory.setProduct(product2);
			productInventory.setPriceUpdated(product2.getProductPrice());
			productInventory.setQuantityAdded(productDto.getProductQuantity());
			productInventory.setCreatedDate(new Date());

			productInventoryRepository.save(productInventory);

			return true;

		} else {
			return false;
		}

	}

	public Product getProductById(int productId) {
		Product product = new Product();
		if (productId != 0) {
			product = productRepository.findById(productId);
		}

		return product;

	}

	@Override
	@Transactional
	public boolean updateProduct(ProductDto productDto) {
		if (productDto != null) {
			Product product = getProductById(productDto.getProductId());

			if (product != null) {
				product.setProductPrice(productDto.getProductPrice());
				product.setProductQuantity(productDto.getProductQuantity());
				product.setModifiedDate(new Date());
				Product product2 = productRepository.save(product);

				ProductInventory productInventory = new ProductInventory();
				productInventory.setProduct(product2);
				productInventory.setPriceUpdated(productDto.getProductPrice());
				productInventory.setQuantityAdded(productDto.getProductQuantity());
				productInventory.setCreatedDate(new Date());
				productInventoryRepository.save(productInventory);
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	@Override
	public List<ProductDto> searchProducts(String searchKey) {
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		if (searchKey != null && !searchKey.equals("")) {
			List<Product> products = productRepository.findByProductNameIgnoreCaseContaining(searchKey);
			if (products != null && products.size() != 0) {
				for (Product product : products) {
					ProductDto productDto = new ProductDto();
					productDto.setProductId(product.getId());
					productDto.setProductName(product.getProductName());
					productDto.setProductDesc(product.getProductDesc());
					productDto.setProductPrice(product.getProductPrice());
					productDto.setProductQuantity(product.getProductQuantity());
					productDto.setCategoryId(product.getCategory().getId());
					productDto.setCategoryName(product.getCategory().getCategoryName());
					productDtos.add(productDto);
				}
			}
		}

		return productDtos;
	}

	public Category getCategoryById(int categoryId) {
		Category category = new Category();
		if (categoryId != 0) {
			category = categoryRepository.findById(categoryId);
		}
		return category;
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		List<Product> products = productRepository.findAll();
		if (products != null) {
			for (Product product : products) {
				ProductDto productDto = new ProductDto();
				productDto.setCategoryName(product.getCategory().getCategoryName());
				productDto.setCategoryId(product.getCategory().getId());
				productDto.setProductId(product.getId());
				productDto.setProductName(product.getProductName());
				productDto.setProductDesc(product.getProductDesc());
				productDto.setProductPrice(product.getProductPrice());
				productDto.setProductQuantity(product.getProductQuantity());
				productDtos.add(productDto);
			}
		}

		return productDtos;
	}

}
