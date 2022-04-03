package uz.doston.pdponline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.doston.pdponline.dto.ApiResponse;
import uz.doston.pdponline.dto.CategoryDto;
import uz.doston.pdponline.entity.Category;
import uz.doston.pdponline.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

import static uz.doston.pdponline.constants.ResponseConstants.NOT_FOUND;
import static uz.doston.pdponline.constants.ResponseConstants.SUCCESS;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ApiResponse add(CategoryDto categoryDto) {
        Category category = new Category();
        if (categoryDto.getParentCategoryId() != null){
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (optionalCategory.isEmpty())
                return new ApiResponse(NOT_FOUND, false);
            category.setParentCategory(optionalCategory.get());
        }
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse getList() {
        List<Category> all = categoryRepository.findAll();
        return new ApiResponse(SUCCESS, true, all);
    }

    public ApiResponse get(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            return new ApiResponse(NOT_FOUND, false);
        return new ApiResponse(SUCCESS, true, optionalCategory.get());
    }

    public ApiResponse delete(Integer id) {
        categoryRepository.deleteById(id);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse edit(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            return new ApiResponse(NOT_FOUND, false);
        Category category = new Category();
        if (categoryDto.getParentCategoryId() != null){
            Optional<Category> optionalCategory1 = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (optionalCategory1.isEmpty())
                return new ApiResponse(NOT_FOUND, false);
            category.setParentCategory(optionalCategory1.get());
        }
        category.setName(categoryDto.getName());
        category.setId(optionalCategory.get().getId());
        categoryRepository.save(category);
        return new ApiResponse(SUCCESS, true);
    }

}
