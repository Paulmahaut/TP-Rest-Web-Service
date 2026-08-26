package com.example.Lab1;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RentingRest {

	private final Map<String, Car> cars = new ConcurrentHashMap<>();

	public RentingRest() {
		cars.put("11AA22", new Car("11AA22", "Ferrari", 100));
		cars.put("AA11BB", new Car("AA11BB", "Renault", 50));
	}

	public static void main(String[] args) {
		SpringApplication.run(Lab1Application.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "Car rental service is running";
	}

	@GetMapping("/cars")
	public List<Car> listOfUnrentedCars() {
		return cars.values().stream()
				.filter(car -> !car.isRented())
				.toList();
	}

	@GetMapping("/cars/{plateNumber}")
	public Car aCar(@PathVariable String plateNumber) {
		return findCar(plateNumber);
	}

	@PutMapping("/cars/{plateNumber}")
	@ResponseStatus(HttpStatus.OK)
	public Car rentOrGetBack(
			@PathVariable String plateNumber,
			@RequestParam boolean rent,
			@RequestBody(required = false) Dates dates) {
		Car car = findCar(plateNumber);

		if (rent) {
			if (car.isRented()) {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "Car is already rented");
			}
			if (dates == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rental dates are required");
			}
			car.rent(dates);
		} else {
			car.getBack();
		}

		return car;
	}

	private Car findCar(String plateNumber) {
		Car car = cars.get(plateNumber);
		if (car == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
		}
		return car;
	}
}