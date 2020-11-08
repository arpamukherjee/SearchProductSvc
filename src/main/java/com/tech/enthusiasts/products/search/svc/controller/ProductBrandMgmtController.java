package com.tech.enthusiasts.products.search.svc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.enthusiasts.products.search.svc.facade.BrandInfoFacade;
import com.tech.enthusiasts.products.search.svc.vo.request.BrandInfoReq;
import com.tech.enthusiasts.products.search.svc.vo.response.BrandInfoView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/brand")
public class ProductBrandMgmtController {
	
	private BrandInfoFacade brandInfoFacade;
	
	@Autowired
	public ProductBrandMgmtController(BrandInfoFacade brandInfoFacade) {
		this.brandInfoFacade = brandInfoFacade;
	}

	@ApiOperation(nickname = "createNewBrandsAndProducts", value = "This service creates a new brand and the associated products", response = BrandInfoView.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = BrandInfoView.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error (Server Error)", response = Error.class),
			@ApiResponse(code = 502, message = "Bad Gateway", response = Error.class),
			@ApiResponse(code = 503, message = "Service Unavailable", response = Error.class),
			@ApiResponse(code = 504, message = "Gateway Timeout", response = Error.class) })
	@PostMapping("/create")
	public ResponseEntity<BrandInfoView> addSupplierDetails(
			@RequestBody @Valid final BrandInfoReq brandInfoReq) {
		return brandInfoFacade.addBrandDetails(brandInfoReq);
	}

	@ApiOperation(nickname = "deleteBrandById", value = "This service removes a brand and the associated products", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = String.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error (Server Error)", response = Error.class),
			@ApiResponse(code = 502, message = "Bad Gateway", response = Error.class),
			@ApiResponse(code = 503, message = "Service Unavailable", response = Error.class),
			@ApiResponse(code = 504, message = "Gateway Timeout", response = Error.class) })
	@DeleteMapping("/clear/brand/{brandId}")
	public ResponseEntity<String> deleteSupplierById(@PathVariable("brandId") final Long brandId) {
		return brandInfoFacade.deleteBrandById(brandId);
	}
	
	@ApiOperation(nickname = "validateBrandName", value = "This service validates whether there exist any brand against the given name", 
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
	@PostMapping("/verify/name")
	public ResponseEntity<Boolean> validateBrandByName(@RequestParam ("brand") final String brandName){
		return brandInfoFacade.validateBrandByName(brandName);
	}
}
