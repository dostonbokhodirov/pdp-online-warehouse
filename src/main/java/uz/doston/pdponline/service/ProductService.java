package uz.doston.pdponline.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.doston.pdponline.dto.ApiResponse;
import uz.doston.pdponline.dto.ProductDto;
import uz.doston.pdponline.entity.Attachment;
import uz.doston.pdponline.entity.Category;
import uz.doston.pdponline.entity.Measurement;
import uz.doston.pdponline.entity.Product;
import uz.doston.pdponline.repository.AttachmentRepository;
import uz.doston.pdponline.repository.CategoryRepository;
import uz.doston.pdponline.repository.MeasurementRepository;
import uz.doston.pdponline.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static uz.doston.pdponline.constants.ResponseConstants.NOT_FOUND;
import static uz.doston.pdponline.constants.ResponseConstants.SUCCESS;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MeasurementRepository measurementRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;

    public ApiResponse add(ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (optionalAttachment.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (optionalMeasurement.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setCode(UUID.randomUUID().toString());

        productRepository.save(product);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse getList(){
        List<Product> productList = productRepository.findAll();
        return new ApiResponse(SUCCESS, true, productList);
    }

    public ApiResponse get(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty())
            return new ApiResponse(NOT_FOUND, false);
        return new ApiResponse(SUCCESS, true, optionalProduct.get());
    }

    public ApiResponse delete(Integer id){
        productRepository.deleteById(id);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse edit(Integer id, ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (optionalAttachment.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (optionalMeasurement.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setMeasurement(optionalMeasurement.get());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setCode(UUID.randomUUID().toString());

        productRepository.save(product);
        return new ApiResponse(SUCCESS, true);

    }
}
