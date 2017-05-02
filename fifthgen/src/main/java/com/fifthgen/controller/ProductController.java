package com.fifthgen.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fifthgen.dto.ProductDto;
import com.fifthgen.service.ProductService;

@Controller
@RequestMapping(value = "/product/")
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/allProducts")
	@ResponseBody
	public List<ProductDto> getAllProducts() {
		List<ProductDto> productDtos = productService.getAllProducts();

		return productDtos;
	}

	@RequestMapping(value = "/inventory/add", method = RequestMethod.POST)
	@ResponseBody
	public boolean addProduct(ProductDto productDto) {

		boolean status = false;
		if (productDto.getProductName() != null && productDto.getCategoryId() != 0
				&& productDto.getProductPrice() != null && productDto.getProductQuantity() != null) {
			status = productService.addProduct(productDto);
		}

		return status;
	}

	@RequestMapping(value = "/inventory/update", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateProduct(ProductDto productDto) {
		boolean status = false;
		if (productDto.getProductId() != 0 && productDto.getProductPrice() != null
				&& productDto.getProductQuantity() != null) {

			status = productService.updateProduct(productDto);
		}

		return status;
	}

	@RequestMapping(value = "/inventory/search", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductDto> searchProduct(String searchKey) {

		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		if (searchKey != null && !searchKey.equals("")) {
			productDtos = productService.searchProducts(searchKey);
		}

		return productDtos;
	}

}
