package sidd88.blogapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sidd88.blogapi.payload.CategoryDto;
import sidd88.blogapi.service.CategoryService;

import java.util.List;

/**
 * Controller: Category
 */
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  // Build Add Category REST API
  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<CategoryDto> addCategory(
    @RequestBody CategoryDto categoryDto
  ) {
    CategoryDto savedCategory = categoryService.addCategory(categoryDto);
    return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
  }

  // Build Get Category REST API
  @GetMapping("{id}")
  public ResponseEntity<CategoryDto> getCategory(
    @PathVariable("id") Long categoryId
  ) {
    CategoryDto categoryDto = categoryService.getCategory(categoryId);
    return ResponseEntity.ok(categoryDto);
  }

  // Build Get All Categories REST API
  @GetMapping
  public ResponseEntity<List<CategoryDto>> getCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories());
  }

  // Build Update Category REST API
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("{id}")
  public ResponseEntity<CategoryDto> updateCategory(
    @RequestBody CategoryDto categoryDto,
    @PathVariable("id") Long categoryId
  ) {
    return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId));
  }

  // Build Delete Category REST API
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteCategory(
    @PathVariable("id") Long categoryId
  ) {
    categoryService.deleteCategory(categoryId);
    return ResponseEntity.ok("Category deleted successfully!.");
  }
}
