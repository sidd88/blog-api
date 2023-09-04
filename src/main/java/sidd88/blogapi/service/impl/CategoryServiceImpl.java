package sidd88.blogapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sidd88.blogapi.entity.Category;
import sidd88.blogapi.exception.ResourceNotFoundException;
import sidd88.blogapi.payload.CategoryDto;
import sidd88.blogapi.repository.CategoryRepository;
import sidd88.blogapi.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation: Category
 */
@Service
public class CategoryServiceImpl implements CategoryService {

  private CategoryRepository categoryRepository;
  private ModelMapper        modelMapper;

  public CategoryServiceImpl(
    CategoryRepository categoryRepository,
    ModelMapper        modelMapper
  ) {
    this.categoryRepository = categoryRepository;
    this.modelMapper        = modelMapper;
  }

  @Override
  public CategoryDto addCategory(CategoryDto categoryDto) {
    Category category      = modelMapper.map(categoryDto, Category.class);
    Category savedCategory = categoryRepository.save(category);
    return modelMapper.map(savedCategory, CategoryDto.class);
  }

  @Override
  public CategoryDto getCategory(Long categoryId) {
    Category category = categoryRepository.findById(categoryId).orElseThrow(
      () -> new ResourceNotFoundException("Category", "id", categoryId)
    );
    return modelMapper.map(category, CategoryDto.class);
  }

  @Override
  public List<CategoryDto> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();
    return categories
      .stream()
      .map(category -> modelMapper.map(category, CategoryDto.class))
      .collect(Collectors.toList());
  }

  @Override
  public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
    Category category = categoryRepository.findById(categoryId).orElseThrow(
      () -> new ResourceNotFoundException("Category", "id", categoryId)
    );

    category.setName(categoryDto.getName());
    category.setDescription(category.getDescription());
    Category updatedCategory = categoryRepository.save(category);

    return modelMapper.map(updatedCategory, CategoryDto.class);
  }

  @Override
  public void deleteCategory(Long categoryId) {
    Category category = categoryRepository.findById(categoryId).orElseThrow(
      () -> new ResourceNotFoundException("Category", "id", categoryId)
    );
    categoryRepository.delete(category);
  }
}
