package anihc.autopartcobackend.controller;

import anihc.autopartcobackend.model.response.AutoPartResponse;
import anihc.autopartcobackend.service.AutoPartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auto-parts")
@RequiredArgsConstructor
@Tag(name = "Auto Parts", description = "Auto parts management endpoints")
public class AutoPartController {

    private final AutoPartService autoPartService;

    @GetMapping
    @Operation(summary = "Get all auto parts with filters",
            description = "Returns paginated list of auto parts with optional filtering")
    public ResponseEntity<Page<AutoPartResponse>> getAllParts(
            @Parameter(description = "Filter by category (English name)")
            @RequestParam(required = false) String category,
            @Parameter(description = "Search in part name or part number")
            @RequestParam(required = false) String search,
            @Parameter(description = "Page number (0-based)")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size")
            @RequestParam(defaultValue = "20") int size
    ) {
        Page<AutoPartResponse> response = autoPartService.getAllParts(category, search, page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{slug}")
    @Operation(summary = "Get auto part by slug", description = "Returns auto part details with file information")
    public ResponseEntity<AutoPartResponse> getBySlug(@PathVariable String slug) {
        AutoPartResponse response = autoPartService.getBySlug(slug);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/related")
    @Operation(summary = "Get related auto parts", description = "Returns auto parts in the same category")
    public ResponseEntity<List<AutoPartResponse>> getRelatedProducts(
            @PathVariable Long id,
            @Parameter(description = "Maximum number of related products to return")
            @RequestParam(defaultValue = "4") int limit
    ) {
        List<AutoPartResponse> response = autoPartService.getRelatedProducts(id, limit);
        return ResponseEntity.ok(response);
    }
}
