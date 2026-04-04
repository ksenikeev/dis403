package ru.itis.dis403.lab2_7.docker.controller;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MachineStatusService {
    private static final Logger logger = LoggerFactory.getLogger(MachineStatusService.class);
    private Map<Integer, MachineStatus> statusMap = new HashMap<>();
    private Long startTime;

    public MachineStatusService() {
        MachineStatus status1 = new MachineStatus();
        status1.setTemp1(12.3);
        status1.setTemp2(12.3);
        status1.setTemp3(12.3);
        status1.setPressure(2.5);
        status1.setResource(100.);

        statusMap.put(1,status1);

        MachineStatus status2 = new MachineStatus();
        status2.setTemp1(12.3);
        status2.setTemp2(12.3);
        status2.setTemp3(12.3);
        status2.setPressure(2.5);
        status2.setResource(100.);

        statusMap.put(2,status2);

        startTime = new Date().getTime();
        logger.info("MachineStatusService инициализирован. Начальное время: {}", startTime);
    }

    public MachineStatus getStatus(Integer id) {
        MachineStatus ms = statusMap.get(id);
        if (ms.getResource() < 0) return new MachineStatus();
        Long d = new Date().getTime() - startTime;

        ms.setTemp1( ms.getTemp1() + d/1_000 );
        ms.setTemp2( ms.getTemp2() + Math.sin(d/1_000) );
        ms.setTemp3( ms.getTemp3() + d/20_000 );
        ms.setPressure(2.5 * ms.getTemp1()/12.5);
        ms.setResource(ms.getResource() - d/1_000);
        return ms;
    }

}
