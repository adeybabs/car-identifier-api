package com.simple.Car.Identifier.Elastic;

import com.simple.Car.Identifier.entity.CarPromotion;
import com.simple.Car.Identifier.repository.CarPromotionElasticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarPromotionElasticDatasource {

    @Autowired
    private CarPromotionElasticRepository carPromotionElasticRepository;

    private static final Logger LOG = LoggerFactory.getLogger(CarPromotionElasticDatasource.class);

    @EventListener(ApplicationReadyEvent.class)
    public void populateData() {
        carPromotionElasticRepository.deleteAll();

        List<CarPromotion> carPromotions = new ArrayList<>();

        CarPromotion promotion1 = new CarPromotion();
        promotion1.setType("discount");
        promotion1.setDescription("Buy two and get a 25% discount");

        CarPromotion promotion2 = new CarPromotion();
        promotion2.setType("bonus");
        promotion2.setDescription("Buy luxury car and get free supply of fuel for a month");

        CarPromotion promotion3 = new CarPromotion();
        promotion3.setType("bonus");
        promotion3.setDescription("Buy on our anniversary and get a free dinner in maldives with your family");

        CarPromotion promotion4 = new CarPromotion();
        promotion4.setType("discount");
        promotion4.setDescription("Pay in cash and 10% discount");

        CarPromotion promotion5 = new CarPromotion();
        promotion5.setType("bonus");
        promotion5.setDescription("Buy now this christmas season and receive gift packs");

        carPromotions.add(promotion1);
        carPromotions.add(promotion2);
        carPromotions.add(promotion3);
        carPromotions.add(promotion4);
        carPromotions.add(promotion5);

        carPromotionElasticRepository.saveAll(carPromotions);

        LOG.info("Saved all promotion data : {}", carPromotionElasticRepository.count());
    }
}