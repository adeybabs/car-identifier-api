package com.simple.Car.Identifier.api;

import com.course.practicaljava.entity.CarPromotion;
import com.course.practicaljava.exception.IllegalApiParamException;
import com.course.practicaljava.repository.CarPromotionElasticRepository;
import com.course.practicaljava.service.CarPromotionService;
import com.simple.Car.Identifier.entity.CarPromotion;
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
public class CarPromotionApi {

    @Autowired
    private CarPromotionService carPromotionService;

    @Autowired
    private CarPromotionElasticRepository carPromotionElasticRepository;

    @GetMapping(value = "/promotions")
    public List<CarPromotion> listAvailablePromotions(
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
