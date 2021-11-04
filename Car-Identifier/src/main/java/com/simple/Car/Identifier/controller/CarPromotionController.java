package com.simple.Car.Identifier.controller;

import com.simple.Car.Identifier.entity.CarPromotion;
import com.simple.Car.Identifier.exception.IllegalApiParamException;
import com.simple.Car.Identifier.repository.CarPromotionElasticRepository;
import com.simple.Car.Identifier.service.CarPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/car/v1")
public class CarPromotionController {

    @Autowired
    private CarPromotionService carPromotionService;

    @Autowired
    private CarPromotionElasticRepository carPromotionElasticRepository;

    @GetMapping(value = "/promotions")
    public List<CarPromotion> listOfAvailablePromotions(
            @RequestParam(name = "type") String promotionType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        if (!carPromotionService.isValidPromotionType(promotionType)) {
            throw new IllegalApiParamException("Invalid promotion type : " + promotionType);
        }
        PageRequest pageable = PageRequest.of(page, size);
        return carPromotionElasticRepository.findByType(promotionType, pageable).getContent();
    }
}
