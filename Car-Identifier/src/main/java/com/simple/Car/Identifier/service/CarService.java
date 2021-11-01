package com.simple.Car.Identifier.service;


import java.util.Arrays;
import java.util.List;

public interface CarService {

    List<String> BRANDS = Arrays
            .asList("Ferrari", "Range Rover", "Ford", "Benz", "Camry");

    List<String> COLORS = Arrays
            .asList("Red", "Black", "White", "Blue", "Silver");

    List<String> TYPES = Arrays
            .asList("Sedan", "SUV", "MPV", "Hatchback", "Convertible");

    List<String> MODELS = Arrays
            .asList("SF90", "ACCORD", "GW6", "A12", "RR16");

    List<String> FUELS = Arrays
            .asList("Petrol", "Electric", "Hybrid", "Water", "Gas");


  //  Car generateCar();
}
