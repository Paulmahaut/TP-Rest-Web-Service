package com.example.Lab1;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RentingRest {

    public static void main(String[] args) {
        SpringApplication.run(Lab1Application.class, args);
    }

    @GetMapping("/cars/{plateNumber}")
    public Car Carplate(@PathVariable("plateNumber") String plateNumber) {

        if (plateNumber.equals("11AA22")) {
            return new Car("11AA22", "Ferrari", 100);
        }

        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Car not found"
        );
    }

    public record Car(
        String plateNumber,
        String brand,
        int price
    ) {}
}