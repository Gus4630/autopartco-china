package anihc.autopartcobackend.controller;

import anihc.autopartcobackend.model.request.ContactRequest;
import anihc.autopartcobackend.model.response.ContactResponse;
import anihc.autopartcobackend.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
@Tag(name = "Contact", description = "Contact form endpoints")
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    @Operation(summary = "Submit contact form", description = "Saves contact form submission to database")
    public ResponseEntity<ContactResponse> submitContact(@Valid @RequestBody ContactRequest request) {
        ContactResponse response = contactService.saveContact(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
