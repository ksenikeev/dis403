package ru.itis.dis403.lab2_7.docker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MachineStatusRestController {

    private MachineStatusService machineStatusService;

    public MachineStatusRestController(MachineStatusService machineStatusService) {
        this.machineStatusService = machineStatusService;
    }

    @GetMapping("/api/status/{id}")
    public ResponseEntity<MachineStatus> getMachineStatus(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(machineStatusService.getStatus(id));
    }

    @PostMapping("/api/resource")
    public ResponseEntity<String> setResource(@RequestParam("resource") Double resource, @RequestParam("id") Integer id) {
        return ResponseEntity.ok("{\"status\":\"success\"}");
    }
}
