package anihc.autopartcobackend.controller;

import anihc.autopartcobackend.model.response.FactoryResponse;
import anihc.autopartcobackend.service.FactoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/factories")
@RequiredArgsConstructor
@Tag(name = "Factories", description = "Factory management endpoints")
public class FactoryController {

    private final FactoryService factoryService;

    @GetMapping
    @Operation(summary = "Get all factories", description = "Returns list of all manufacturing factories")
    public ResponseEntity<List<FactoryResponse>> getAllFactories() {
        List<FactoryResponse> response = factoryService.getAllFactories();
        return ResponseEntity.ok(response);
    }
}
