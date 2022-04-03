package uz.doston.pdponline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.doston.pdponline.dto.ApiResponse;
import uz.doston.pdponline.service.AttachmentService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attachment")
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping("/add")
    public ApiResponse add(MultipartHttpServletRequest request) throws IOException {
        return attachmentService.add(request);
    }

    @GetMapping("/get")
    public ApiResponse get(){
        return attachmentService.getList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse get(@PathVariable Integer id){
        return attachmentService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return attachmentService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable Integer id, MultipartHttpServletRequest request) throws IOException {
        return attachmentService.edit(id, request);
    }
}
