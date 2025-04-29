package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Warehouse addWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.addWarehouse(warehouse);
    }

    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public Optional<Warehouse> getWarehouseById(@PathVariable String id) {
        return warehouseService.getWarehouseById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWarehouse(@PathVariable String id) {
        warehouseService.deleteWarehouse(id);
    }

    @PostMapping("/{warehouseId}/products")
    public Warehouse addProductToWarehouse(@PathVariable String warehouseId, @RequestBody Product product) {
        return warehouseService.addProductToWarehouse(warehouseId, product);
    }

    @GetMapping("/products")
    public List<Map<String, Object>> getAllProductsWithLocations() {
        return warehouseService.getAllProductsWithLocations();
    }

    @GetMapping("/products/{productId}")
    public Map<String, Object> getProductById(@PathVariable String productId) {
        return warehouseService.getProductById(productId);
    }

    @DeleteMapping("/{warehouseId}/products/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductFromWarehouse(@PathVariable String warehouseId, @PathVariable String productId) {
        warehouseService.deleteProductFromWarehouse(warehouseId, productId);
    }
}
