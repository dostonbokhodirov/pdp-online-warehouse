package uz.doston.pdponline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.doston.pdponline.dto.ApiResponse;
import uz.doston.pdponline.dto.UserDto;
import uz.doston.pdponline.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public ApiResponse add(@RequestBody UserDto userDto){
        return userService.add(userDto);
    }

    @GetMapping("/get")
    public ApiResponse get(){
        return userService.getList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse get(@PathVariable Integer id){
        return userService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return userService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody UserDto userDto){
        return userService.edit(id, userDto);
    }
}
