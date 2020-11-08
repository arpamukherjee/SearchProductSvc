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

import com.tech.enthusiasts.products.search.svc.facade.SupplierInfoFacade;
import com.tech.enthusiasts.products.search.svc.vo.request.SupplierInfoReq;
import com.tech.enthusiasts.products.search.svc.vo.response.SupplierInfoView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/supply")
public class SupplyMgmtController {

	private SupplierInfoFacade supplierInfoFacade;

	@Autowired
	public SupplyMgmtController(final SupplierInfoFacade supplierInfoFacade) {
		this.supplierInfoFacade = supplierInfoFacade;
	}

	@ApiOperation(nickname = "createNewSupplierAndSuppliedProducts", value = "This service creates a new supplier and the associated products that can be supplied", 
							 response = SupplierInfoView.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = String.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error (Server Error)", response = Error.class),
			@ApiResponse(code = 502, message = "Bad Gateway", response = Error.class),
			@ApiResponse(code = 503, message = "Service Unavailable", response = Error.class),
			@ApiResponse(code = 504, message = "Gateway Timeout", response = Error.class) })
	@PostMapping("/create")
	public ResponseEntity<SupplierInfoView> addSupplierDetails(
			@RequestBody @Valid final SupplierInfoReq supplierInfoReq) {
		return supplierInfoFacade.addSupplierDetails(supplierInfoReq);
	}

	@ApiOperation(nickname = "deleteSupplierById", value = "This service removes a supplier and the associated products", 
							 response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = String.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error (Server Error)", response = Error.class),
			@ApiResponse(code = 502, message = "Bad Gateway", response = Error.class),
			@ApiResponse(code = 503, message = "Service Unavailable", response = Error.class),
			@ApiResponse(code = 504, message = "Gateway Timeout", response = Error.class) })
	@DeleteMapping("/clear/supplier/{supplierId}")
	public ResponseEntity<String> deleteSupplierById(@PathVariable("supplierId") final Long supplierId) {
		return supplierInfoFacade.deleteSupplierById(supplierId);
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
	@PostMapping("/verify/supplier/name")
	public ResponseEntity<Boolean> validateSupplierByName(@RequestParam ("supplier") final String brandName){
		return supplierInfoFacade.validateSupplierByName(brandName);
	}
}