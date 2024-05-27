package com.javeriana.trasnportistas.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ElServicio {
    private static final Logger logger = LoggerFactory.getLogger(ElServicio.class);

    public String saludar() {
        logger.info("Saludar method called");
        return "Hola mundo";
    }

    public double calculo_costo(double latitudSalida, double longitudSalida, double latitudLlegada,
            double longitudLlegada,
            int cantidadPaquetes) {

        double distancia = Math.sqrt(Math.pow(latitudLlegada - latitudSalida, 2)
                + Math.pow(longitudLlegada - longitudSalida, 2));

        double costo = 0;

        if (distancia < 10) {
            costo = 10000;
        } else if (distancia < 50) {
            costo = 50000;
        } else if (distancia < 100) {
            costo = 100000;
        } else {
            costo = 200000;
        }

        costo = costo * cantidadPaquetes;

        return costo * 0.10;

    }
}