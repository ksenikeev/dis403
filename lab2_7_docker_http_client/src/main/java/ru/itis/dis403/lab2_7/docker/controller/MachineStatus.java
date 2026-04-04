package ru.itis.dis403.lab2_7.docker.controller;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class MachineStatus {
    private double temp1;
    private double temp2;
    private double temp3;
    private double pressure;
    private double resource;
}
