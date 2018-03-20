package ru.stc.services;

import org.springframework.stereotype.Service;
import ru.stc.model.Car;

@Service
public class HomeServiceImpl implements HomeService {
    @Override
    public Car getSomeCar() {
        return new Car(1, "WAZ", 15);
    }
}
