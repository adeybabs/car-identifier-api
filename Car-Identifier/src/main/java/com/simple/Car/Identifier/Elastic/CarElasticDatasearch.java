package com.simple.Car.Identifier.Elastic;

import com.simple.Car.Identifier.entity.Car;
import com.simple.Car.Identifier.repository.CarElasticRepository;
import com.simple.Car.Identifier.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarElasticDatasearch {

    private static final Logger LOG = LoggerFactory.getLogger(CarElasticDatasearch.class);

    private CarElasticRepository carElasticRepository;
    private CarService carService;
    private WebClient webClient;

    @Autowired
    public CarElasticDatasearch(
            CarElasticRepository carElasticRepository,
            CarService carService, WebClient webClient) {

        this.carElasticRepository = carElasticRepository;
        this.carService = carService;
        this.webClient = webClient;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void populateData() {
        final String response = webClient.delete().uri("car-identifier")
                .retrieve().bodyToMono(String.class).block();

        LOG.info("End delete with response {}", response);
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            cars.add(carService.generateCar());
        }
        carElasticRepository.saveAll(cars);
        LOG.info("Saved {} cars", carElasticRepository.count());
    }
}