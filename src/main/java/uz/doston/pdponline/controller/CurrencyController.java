package uz.doston.pdponline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.doston.pdponline.dto.ApiResponse;
import uz.doston.pdponline.entity.Currency;
import uz.doston.pdponline.service.CurrencyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping("/add")
    public ApiResponse add(@RequestBody Currency currency){
        return currencyService.add(currency);
    }

    @GetMapping("/get")
    public ApiResponse get(){
        return currencyService.getList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse get(@PathVariable Integer id){
        return currencyService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return currencyService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody Currency currency){
        return currencyService.edit(id, currency);
    }

}
