package com.starknetworks.boundarycontext.profilemanagement.boundary;

import com.starknetworks.boundarycontext.profilemanagement.control.transportobject.ProfileTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("profile")
@CrossOrigin
public class ProfileManagementRestController {

    private final ProfileManagementBci profileManagement;

    @Autowired
    public ProfileManagementRestController(ProfileManagementBci profileManagement) {
        this.profileManagement = profileManagement;
    }

    @GetMapping("{id}")
    public ProfileTo getProfile(@PathVariable("id") @Valid Long id) {
        return profileManagement.getProfile(id);
    }

    @PostMapping
    public ProfileTo saveProfile(@RequestBody @Valid ProfileTo profileTo) {
        return profileManagement.saveProfile(profileTo);
    }

    @GetMapping("{id}/image")
    public String getAvatar(@PathVariable("id") @Valid Long id) {
        return profileManagement.getAvatar(id);
    }

    @PostMapping("{id}/image")
    public String saveAvatar(@RequestParam("file") @NotNull MultipartFile file,
                             @PathVariable("id") @Valid Long id) {
        return profileManagement.saveAvatar(id, file);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> constraintViolation(ConstraintViolationException e) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> entityNotFound(EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
