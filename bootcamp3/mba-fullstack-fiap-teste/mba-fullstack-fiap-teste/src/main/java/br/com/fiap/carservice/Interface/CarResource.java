package br.com.fiap.carservice.Interface;

import br.com.fiap.carservice.model.Car;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "cars",collectionResourceRel = "cars")
public interface CarResource  extends PagingAndSortingRepository<Car, Integer> {
    // a list of cars method find by color param
    List<Car> findCarByColor(@Param("color") String color);
}
