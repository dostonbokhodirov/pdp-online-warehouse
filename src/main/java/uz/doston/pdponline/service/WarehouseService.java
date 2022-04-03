package uz.doston.pdponline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.doston.pdponline.dto.ApiResponse;
import uz.doston.pdponline.entity.Warehouse;
import uz.doston.pdponline.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

import static uz.doston.pdponline.constants.ResponseConstants.NOT_FOUND;
import static uz.doston.pdponline.constants.ResponseConstants.SUCCESS;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public ApiResponse add(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse getList() {
        List<Warehouse> all = warehouseRepository.findAll();
        return new ApiResponse(SUCCESS, true, all);
    }

    public ApiResponse get(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isEmpty())
            return new ApiResponse(NOT_FOUND,false);
        return new ApiResponse(SUCCESS,true,optionalWarehouse.get());
    }

    public ApiResponse delete(Integer id){
        warehouseRepository.deleteById(id);
        return new ApiResponse(SUCCESS,true);
    }

    public ApiResponse edit(Integer id, Warehouse warehouse){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isEmpty())
            return new ApiResponse(NOT_FOUND,false);
        warehouse.setId(optionalWarehouse.get().getId());
        warehouseRepository.save(warehouse);
        return new ApiResponse(SUCCESS,true);
    }

}
