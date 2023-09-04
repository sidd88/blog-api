package sidd88.blogapi.service;

import sidd88.blogapi.payload.CategoryDto;

import java.util.List;

/**
 * Service: Category
 */
public interface CategoryService {

  CategoryDto addCategory(CategoryDto categoryDto);

  CategoryDto getCategory(Long categoryId);

  List<CategoryDto> getAllCategories();

  CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

  void deleteCategory(Long categoryId);
}
