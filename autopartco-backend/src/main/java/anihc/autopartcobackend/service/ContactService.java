package anihc.autopartcobackend.service;

import anihc.autopartcobackend.dao.entity.ContactEntity;
import anihc.autopartcobackend.dao.repository.ContactRepository;
import anihc.autopartcobackend.model.request.ContactRequest;
import anihc.autopartcobackend.model.response.ContactResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    @Transactional
    public ContactResponse saveContact(ContactRequest request) {
        log.info("Saving contact form submission from: {}", request.getEmail());

        ContactEntity entity = ContactEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .subject(request.getSubject())
                .message(request.getMessage())
                .isRead(false)
                .build();

        ContactEntity saved = contactRepository.save(entity);

        return ContactResponse.builder()
                .id(saved.getId())
                .success(true)
                .message("Contact form submitted successfully. We will get back to you soon!")
                .build();
    }
}
