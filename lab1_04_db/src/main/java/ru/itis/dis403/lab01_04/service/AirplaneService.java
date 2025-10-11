package ru.itis.dis403.lab01_04.service;

import jakarta.servlet.http.HttpServletRequest;
import ru.itis.dis403.lab01_04.model.Airplane;
import ru.itis.dis403.lab01_04.repository.AirplaneRepository;
import java.util.List;

public class AirplaneService {

    private AirplaneRepository repository = new AirplaneRepository();

    public void fillAttributes(HttpServletRequest request) {
        List<Airplane> airplanes = repository.findAll();
        request.setAttribute("airplanes", airplanes);
    }
}
