package ru.stc.services;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.stc.model.Car;

import java.util.List;

public interface HomeService {

    Car getSomeCar();

}
