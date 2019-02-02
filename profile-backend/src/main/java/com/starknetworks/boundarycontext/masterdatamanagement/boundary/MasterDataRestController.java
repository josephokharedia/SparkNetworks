package com.starknetworks.boundarycontext.masterdatamanagement.boundary;

import com.starknetworks.boundarycontext.masterdatamanagement.control.transferobject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("masterdata")
public class MasterDataRestController {

    private final MasterDataManagementBci masterDataManagementBci;

    @Autowired
    public MasterDataRestController(MasterDataManagementBci masterDataManagementBci) {
        this.masterDataManagementBci = masterDataManagementBci;
    }

    @GetMapping("gender")
    public List<GenderTo> getGender() {
        return masterDataManagementBci.getAllGenders();
    }

    @GetMapping("city")
    public List<CityTo> getCities() {
        return masterDataManagementBci.getAllCities();
    }

    @GetMapping("city/{name}")
    public List<CityTo> getCities(@PathVariable @NotNull String name) {
        return masterDataManagementBci.findCitiesByName(name);
    }

    @GetMapping("ethnicity")
    public List<EthnicityTo> getEthnicity() {
        return masterDataManagementBci.getAllEthnicity();
    }

    @GetMapping("figure")
    public List<FigureTo> getFigures() {
        return masterDataManagementBci.getAllFigures();
    }

    @GetMapping("maritalStatus")
    public List<MaritalStatusTo> getMaritalStatuses() {
        return masterDataManagementBci.getAllMaritalStatuses();
    }

    @GetMapping("religion")
    public List<ReligionTo> getReligions() {
        return masterDataManagementBci.getAllReligions();
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
