package br.com.fiap.carservice;

import br.com.fiap.carservice.Interface.CarResource;
import br.com.fiap.carservice.model.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


// MOCKANDO DADOS PARA TESTE SEM BANCO AINDA !!!!!!

@RunWith(MockitoJUnitRunner.class)
public class CarResourceMockTest {

    @Mock
    private CarResource carResource;

    @Test
    public void shouldFindCarByColor(){
        String color = "yellow";
        String model = "camaro";


        Car car = new Car(color, model);
        carResource.save(car);

        List<Car> cars = Arrays.asList(car);

        //        List<Car> cars = carResource.findCarByColor(color);
        Mockito.when(carResource.findCarByColor(color)).thenReturn(cars);

        assertEquals(cars.get(0).getColor(), color);
        assertEquals(cars.get(0).getModel(), model);
    }
}
