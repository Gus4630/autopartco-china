package anihc.autopartcobackend.controller;

import anihc.autopartcobackend.model.response.CategoryResponse;
import anihc.autopartcobackend.service.AutoPartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Product categories endpoints")
public class CategoryController {

    private final AutoPartService autoPartService;

    @GetMapping
    @Operation(summary = "Get all categories", description = "Returns all distinct categories with product counts")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> response = autoPartService.getAllCategories();
        return ResponseEntity.ok(response);
    }
}
