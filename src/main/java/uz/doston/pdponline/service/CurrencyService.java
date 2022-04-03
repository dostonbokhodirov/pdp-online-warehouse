package uz.doston.pdponline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.doston.pdponline.dto.ApiResponse;
import uz.doston.pdponline.entity.Currency;
import uz.doston.pdponline.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

import static uz.doston.pdponline.constants.ResponseConstants.NOT_FOUND;
import static uz.doston.pdponline.constants.ResponseConstants.SUCCESS;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public ApiResponse add(Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findByName(currency.getName());
        if (optionalCurrency.isEmpty())
            return new ApiResponse(NOT_FOUND,false);
        currencyRepository.save(currency);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse getList() {
        List<Currency> all = currencyRepository.findAll();
        return new ApiResponse(SUCCESS, true, all);
    }

    public ApiResponse get(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isEmpty())
            return new ApiResponse(NOT_FOUND,false);
        return new ApiResponse(SUCCESS,true,optionalCurrency.get());
    }

    public ApiResponse delete(Integer id){
        currencyRepository.deleteById(id);
        return new ApiResponse(SUCCESS,true);
    }

    public ApiResponse edit(Integer id, Currency currency){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isEmpty())
            return new ApiResponse(NOT_FOUND,false);
        currency.setId(optionalCurrency.get().getId());
        currencyRepository.save(currency);
        return new ApiResponse(SUCCESS,true);
    }

}
