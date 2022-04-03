package uz.doston.pdponline.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.doston.pdponline.dto.ApiResponse;
import uz.doston.pdponline.dto.InputDto;
import uz.doston.pdponline.entity.Currency;
import uz.doston.pdponline.entity.Input;
import uz.doston.pdponline.entity.Supplier;
import uz.doston.pdponline.entity.Warehouse;
import uz.doston.pdponline.repository.CurrencyRepository;
import uz.doston.pdponline.repository.InputRepository;
import uz.doston.pdponline.repository.SupplierRepository;
import uz.doston.pdponline.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static uz.doston.pdponline.constants.ResponseConstants.NOT_FOUND;
import static uz.doston.pdponline.constants.ResponseConstants.SUCCESS;

@Service
@RequiredArgsConstructor
public class InputService {

    private final InputRepository inputRepository;
    private final WarehouseRepository warehouseRepository;
    private final SupplierRepository supplierRepository;
    private final CurrencyRepository currencyRepository;
    private final ModelMapper modelMapper;

    public ApiResponse add(InputDto inputDto) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) return new ApiResponse(NOT_FOUND, false);
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (optionalSupplier.isEmpty()) return new ApiResponse(NOT_FOUND, false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()) return new ApiResponse(NOT_FOUND, false);

        Input input = new Input();
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setCode(UUID.randomUUID().toString());
        input.setFactureNumber(UUID.randomUUID().toString());
        inputRepository.save(input);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse getList(){
        List<Input> inputList = inputRepository.findAll();
        return new ApiResponse(SUCCESS, true, inputList);
    }

    public ApiResponse get(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty())
            return new ApiResponse(NOT_FOUND, false);
        return new ApiResponse(SUCCESS, true, optionalInput.get());
    }

    public ApiResponse delete(Integer id){
        inputRepository.deleteById(id);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse edit(Integer id, InputDto inputDto){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) return new ApiResponse(NOT_FOUND, false);
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (optionalSupplier.isEmpty()) return new ApiResponse(NOT_FOUND, false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()) return new ApiResponse(NOT_FOUND, false);

        Input input = modelMapper.map(inputDto, Input.class);
        input.setId(optionalInput.get().getId());
        input.setCode(UUID.randomUUID().toString());
        input.setFactureNumber(UUID.randomUUID().toString());
        inputRepository.save(input);
        return new ApiResponse(SUCCESS, true);
    }

}
