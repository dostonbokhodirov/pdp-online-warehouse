package uz.doston.pdponline.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.doston.pdponline.dto.ApiResponse;
import uz.doston.pdponline.dto.UserDto;
import uz.doston.pdponline.entity.User;
import uz.doston.pdponline.entity.Warehouse;
import uz.doston.pdponline.repository.UserRepository;
import uz.doston.pdponline.repository.WarehouseRepository;

import java.util.*;

import static uz.doston.pdponline.constants.ResponseConstants.NOT_FOUND;
import static uz.doston.pdponline.constants.ResponseConstants.SUCCESS;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final WarehouseRepository warehouseRepository;
    private final ModelMapper modelMapper;

    public ApiResponse add(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
        if (optionalUser.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        Set<Warehouse> warehouses = new HashSet<>();
        for (Integer id : userDto.getWarehousesId()) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
            optionalWarehouse.ifPresent(warehouses::add);
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setCode(UUID.randomUUID().toString());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setWarehouses(warehouses);
        userRepository.save(user);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse getList() {
        List<User> userList = userRepository.findAll();
        return new ApiResponse(SUCCESS, true, userList);
    }

    public ApiResponse get(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return new ApiResponse(NOT_FOUND, false);
        return new ApiResponse(SUCCESS, true, optionalUser.get());
    }

    public ApiResponse delete(Integer id) {
        userRepository.deleteById(id);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse edit(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        User user = modelMapper.map(userDto, User.class);
        user.setId(optionalUser.get().getId());
        user.setCode(UUID.randomUUID().toString());
        userRepository.save(user);
        return new ApiResponse(SUCCESS, true);
    }

}
