package com.simple.Car.Identifier.repository;

import com.simple.Car.Identifier.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car, String> {

//    List<Car> findByBrandAndColor(String brand, String color);
    Page<Car> findByBrandAndColor(String brand, String color, Pageable pageable);

    List<Car> findByFirstReleaseDateAfter(LocalDate date);
}
