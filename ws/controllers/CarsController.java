package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarListDtoProj;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;

@RestController
@RequestMapping("api/cars")
@CrossOrigin
public class CarsController {
	private final CarService carService;


	public CarsController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("getall")
	public ResponseEntity<DataResult<List<CarListDto>>> getAll() {
		return ResponseEntity.ok(carService.getAll());
	}
	
	@GetMapping("getall/{pageNumber}/{pageSize}")
	public ResponseEntity<?> getAllPaged(@PathVariable int pageNumber, @PathVariable int pageSize){
		return ResponseEntity.ok(carService.getAll(pageNumber, pageSize));
	}
	
	@GetMapping("getallrentablecars")
	public ResponseEntity<DataResult<List<CarListDto>>> getAllRentableCars() {
		return ResponseEntity.ok(carService.getAllRentableCars());
	}
	@GetMapping("getrentablecars")
	public ResponseEntity<DataResult<List<CarListDto>>> getRentableCars() {
		return ResponseEntity.ok(carService.getRentableCars());
	}
	
	@GetMapping("getrentablecars/{pageNumber}")
	public ResponseEntity<DataResult<List<CarListDto>>> getRentableCars(@PathVariable int pageNumber) {
		return ResponseEntity.ok(carService.getRentableCars(pageNumber));
	}
	
	@GetMapping("getrentablecars/{pageNumber}/{pageSize}")
	public ResponseEntity<DataResult<List<CarListDto>>> getRentableCars(@PathVariable int pageNumber, @PathVariable int pageSize) 
	{
		return ResponseEntity.ok(carService.getRentableCars(pageNumber, pageSize));
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add(@RequestBody CreateCarRequest carCreateDto) {
		return ResponseEntity.ok(carService.addCar(carCreateDto));
	}

	@PostMapping("update")
	public ResponseEntity<?> update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {

		var result = carService.updateCar(updateCarRequest);		
		return result.isSuccess() ? ResponseEntity.ok(result): ResponseEntity.badRequest().body(result);
	}
	
	@GetMapping("getbyid/{id}")
	public ResponseEntity<DataResult<CarListDtoProj>> getById(@PathVariable int id) 
	{
		System.out.println(id + " bakalım ne geldi");
		return ResponseEntity.ok(carService.getCarListDto(id));
	}
}
