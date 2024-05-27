package com.javeriana.trasnportistas.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.javeriana.trasnportistas.services.ElServicio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ElController {

    @Autowired
    private ElServicio servicio;

    @GetMapping("/api/saludar")
    public String hola() {
        return servicio.saludar();
    }

    @PostMapping("/api/costo")
    public double reestablecer(@RequestBody Map<String, Object> request) {
        double latitudSalida = (double) request.get("latitudSalida");
        double longitudSalida = (double) request.get("longitudSalida");
        double latitudLlegada = (double) request.get("latitudLlegada");
        double longitudLlegada = (double) request.get("longitudLlegada");
        int cantidadPaquetes = (int) request.get("cantidadPaquetes");

        double costo = servicio.calculo_costo(latitudSalida, longitudSalida, latitudLlegada, longitudLlegada,
                cantidadPaquetes);

        return costo;
    }

}
