package com.tech.enthusiasts.products.search.svc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.enthusiasts.products.search.svc.facade.ProductInfoFacade;
import com.tech.enthusiasts.products.search.svc.facade.SupplierInfoFacade;
import com.tech.enthusiasts.products.search.svc.vo.response.ProductInfoView;
import com.tech.enthusiasts.products.search.svc.vo.response.SupplierInfoView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductBrandView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductColorView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.ProductSizeView;
import com.tech.enthusiasts.products.search.svc.vo.response.groups.SuppliedProductsPriceView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/search")
public class ProductSearchController {

	private ProductInfoFacade productInfoFacade;
	private SupplierInfoFacade supplierInfoFacade;

	@Autowired
	public ProductSearchController(final ProductInfoFacade productInfoFacade, final SupplierInfoFacade supplierInfoFacade) {
		this.productInfoFacade = productInfoFacade;
		this.supplierInfoFacade = supplierInfoFacade;
	}

	@ApiOperation(nickname = "findAllProductsGroupedByBrand", value = "This service returns all the products grouped by their respective brand names", response = ProductBrandView.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductBrandView.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error (Server Error)", response = Error.class),
			@ApiResponse(code = 502, message = "Bad Gateway", response = Error.class),
			@ApiResponse(code = 503, message = "Service Unavailable", response = Error.class),
			@ApiResponse(code = 504, message = "Gateway Timeout", response = Error.class) })
	@GetMapping("/products/group/brand")
	public ResponseEntity<List<ProductBrandView>> findAllProductsGroupedByBrand() {
		return productInfoFacade.findAllProductsGroupedByBrand();
	}
	
	@ApiOperation(nickname = "findAllProductsGroupedByBrand", value = "This service returns all the products grouped by their respective brand names", response = SuppliedProductsPriceView.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = SuppliedProductsPriceView.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error (Server Error)", response = Error.class),
			@ApiResponse(code = 502, message = "Bad Gateway", response = Error.class),
			@ApiResponse(code = 503, message = "Service Unavailable", response = Error.class),
			@ApiResponse(code = 504, message = "Gateway Timeout", response = Error.class) })
	@GetMapping("/products/group/price")
	public ResponseEntity<List<SuppliedProductsPriceView>> findAllProductsGroupedByPrice() {
		return supplierInfoFacade.findAllProductsGroupedByPrice();
	}

	@ApiOperation(nickname = "findAllProductsGroupedByPrice", value = "This service returns all the products grouped by their respective product color", 
							 response = ProductColorView.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductColorView.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error (Server Error)", response = Error.class),
			@ApiResponse(code = 502, message = "Bad Gateway", response = Error.class),
			@ApiResponse(code = 503, message = "Service Unavailable", response = Error.class),
			@ApiResponse(code = 504, message = "Gateway Timeout", response = Error.class) })
	@GetMapping("/products/group/color")
	public ResponseEntity<List<ProductColorView>> findAllProductsGroupedByColor() {
		return productInfoFacade.findAllProductsGroupedByColor();
	}

	@ApiOperation(nickname = "findAllProductsGroupedByBrand", value = "This service returns all the products grouped by their respective product sizes", 
							 response = ProductSizeView.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductSizeView.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error (Server Error)", response = Error.class),
			@ApiResponse(code = 502, message = "Bad Gateway", response = Error.class),
			@ApiResponse(code = 503, message = "Service Unavailable", response = Error.class),
			@ApiResponse(code = 504, message = "Gateway Timeout", response = Error.class) })
	@GetMapping("/products/group/size")
	public ResponseEntity<List<ProductSizeView>> findAllProductsGroupedBySize() {
		return productInfoFacade.findAllProductsGroupedBySize();
	}
	
	@ApiOperation(nickname = "findProductById", value = "This service returns details of a product according to the product SKU ID", 
			 response = ProductInfoView.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductInfoView.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error (Server Error)", response = Error.class),
			@ApiResponse(code = 502, message = "Bad Gateway", response = Error.class),
			@ApiResponse(code = 503, message = "Service Unavailable", response = Error.class),
			@ApiResponse(code = 504, message = "Gateway Timeout", response = Error.class) })
	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductInfoView> findProductById(@PathVariable("productId") final Long productId) {
		return productInfoFacade.findProductDetailsById(productId);
	}

	@ApiOperation(nickname = "findProductsBySupplier", value = "This service returns all the products and their available number according to the supplierId", 
							  response = SupplierInfoView.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = SupplierInfoView.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error (Server Error)", response = Error.class),
			@ApiResponse(code = 502, message = "Bad Gateway", response = Error.class),
			@ApiResponse(code = 503, message = "Service Unavailable", response = Error.class),
			@ApiResponse(code = 504, message = "Gateway Timeout", response = Error.class) })
	@GetMapping("/products/supplier/{supplierId}")
	public ResponseEntity<SupplierInfoView> findProductsBySupplier(@PathVariable("supplierId") final Long supplierId) {
		return supplierInfoFacade.findProductsBySupplier(supplierId);
	}
	
	@ApiOperation(nickname = "validateProductName", value = "This service validates whether there exist any product against the given name", 
			 response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Boolean.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error (Server Error)", response = Error.class),
			@ApiResponse(code = 502, message = "Bad Gateway", response = Error.class),
			@ApiResponse(code = 503, message = "Service Unavailable", response = Error.class),
			@ApiResponse(code = 504, message = "Gateway Timeout", response = Error.class) })
	@PostMapping("/product/name")
	public ResponseEntity<Boolean> validateProductByName(@RequestParam ("productName") final String productName){
		return productInfoFacade.validateProductByName(productName);
	}

}