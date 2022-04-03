package uz.doston.pdponline.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.doston.pdponline.dto.ApiResponse;
import uz.doston.pdponline.dto.SupplierDto;
import uz.doston.pdponline.service.SupplierService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping("/add")
    public ApiResponse add(@RequestBody SupplierDto supplierDto){
        return supplierService.add(supplierDto);
    }

    @GetMapping("/get")
    public ApiResponse get(){
        return supplierService.getList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse get(@PathVariable Integer id){
        return supplierService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return supplierService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody SupplierDto supplierDto){
        return supplierService.edit(id, supplierDto);
    }
}
