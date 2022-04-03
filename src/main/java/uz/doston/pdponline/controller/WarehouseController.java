package uz.doston.pdponline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.doston.pdponline.dto.ApiResponse;
import uz.doston.pdponline.entity.Warehouse;
import uz.doston.pdponline.service.WarehouseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping("/add")
    public ApiResponse add(@RequestBody Warehouse warehouse){
        return warehouseService.add(warehouse);
    }

    @GetMapping("/get")
    public ApiResponse get(){
        return warehouseService.getList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse get(@PathVariable Integer id){
        return warehouseService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return warehouseService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody Warehouse warehouse){
        return warehouseService.edit(id, warehouse);
    }

}
