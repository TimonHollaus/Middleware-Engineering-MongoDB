package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final WarehouseRepository warehouseRepository;

    public DataInitializer(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public void run(String... args) {
        warehouseRepository.deleteAll();

        List<Product> products1 = Arrays.asList(
                new Product("1", "Kaffeemaschine", "Haushalt", 50),
                new Product("2", "Wasserkocher", "Haushalt", 85),
                new Product("3", "Fahrradhelm", "Sport", 60),
                new Product("4", "Yogamatte", "Sport", 120),
                new Product("5", "Pflanzenset", "Garten", 95),
                new Product("6", "Krimiroman", "Bücher", 240),
                new Product("7", "Kalender 2025", "Papeterie", 370),
                new Product("8", "Kugelschreiber-Set", "Papeterie", 150),
                new Product("9", "Schreibtisch", "Möbel", 200),
                new Product("10", "Regal", "Möbel", 110)
        );

        List<Product> products2 = Arrays.asList(
                new Product("11", "Smartphone", "Technologie", 300),
                new Product("12", "Laptop", "Technologie", 450),
                new Product("13", "Fitness Tracker", "Gesundheit", 130),
                new Product("14", "Massagegerät", "Gesundheit", 80),
                new Product("15", "Weinflaschenhalter", "Küche", 40),
                new Product("16", "Kochbuch", "Bücher", 180),
                new Product("17", "Tischlampe", "Beleuchtung", 220),
                new Product("18", "Vorhangset", "Wohnaccessoires", 260),
                new Product("19", "Laptoptasche", "Zubehör", 145),
                new Product("20", "USB-Hub", "Zubehör", 75)
        );


        Warehouse warehouse1 = new Warehouse("warehouse1", "Lager West", "Wien", products1);
        Warehouse warehouse2 = new Warehouse("warehouse2", "Lager Süd", "TGM", products2);

        warehouseRepository.saveAll(Arrays.asList(warehouse1, warehouse2));

        System.out.println("Daten wurden erfolgreich in 2 Lagerhäusern abgelegt.");
    }
}

