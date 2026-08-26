package com.example.Lab1;

public class Car {

	private final String plateNumber;
	private final String brand;
	private final int price;
	private boolean rented;
	private String begin;
	private String end;

	public Car(String plateNumber, String brand, int price) {
		this.plateNumber = plateNumber;
		this.brand = brand;
		this.price = price;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public String getBrand() {
		return brand;
	}

	public int getPrice() {
		return price;
	}

	public boolean isRented() {
		return rented;
	}

	public String getBegin() {
		return begin;
	}

	public String getEnd() {
		return end;
	}

	public void rent(Dates dates) {
		rented = true;
		begin = dates.begin();
		end = dates.end();
	}

	public void getBack() {
		rented = false;
		begin = null;
		end = null;
	}
}