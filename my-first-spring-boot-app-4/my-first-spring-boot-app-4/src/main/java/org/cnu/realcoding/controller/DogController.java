package org.cnu.realcoding.controller;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogController {

    @Autowired
    private DogManagementService dogManagementService;

    @PostMapping("/dogs")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDogs(@RequestBody Dog dog){
        dogManagementService.insertDog(dog);
    }

    @GetMapping("/dogs")
    public List<Dog> getAllDogs(){
        return dogManagementService.getAllDogs();
    }


    @GetMapping("/dogs/{name}")
    public List<Dog> getDogByName(@PathVariable String name){
        return dogManagementService.getDogByName(name);
    }

    @GetMapping("/dogs/{ownerName}")
    public List<Dog> getDogByOwnerName(@PathVariable String ownerName){
        return dogManagementService.getDogByOwnerName(ownerName);
    }

    @GetMapping("/dogs/{ownerPhoneNumber}")
    public List<Dog> getDogByOwnerPhoneNumber(@PathVariable String ownerPhoneNumber){
        return dogManagementService.getDogByOwnerPhoneNumber(ownerPhoneNumber);
    }

    @GetMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}")
    public Dog getDogByAll(@PathVariable String name,@PathVariable  String ownerName,@PathVariable  String ownerPhoneNumber){
        return dogManagementService.getDogByAll(name,ownerName,ownerPhoneNumber);
    }

    @PutMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}/{newName}/{newKind}/{newOwnerName}/{newOwnerPhoneNumber}")
    public Dog UpdateDogAll(@PathVariable String name, @PathVariable String ownerName, @PathVariable String ownerPhoneNumber,
                            @PathVariable String newName , @PathVariable String newKind , @PathVariable String newOwnerName ,
                            @PathVariable String newOwnerPhoneNumber) {
        return dogManagementService.updateDogAll(name,ownerName,ownerPhoneNumber,newName,newKind,newOwnerName,newOwnerPhoneNumber);
    }

    @GetMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}/{newKind}")
    public Dog updateDogByKind(@PathVariable String name, @PathVariable String ownerName, @PathVariable String ownerPhoneNumber, @PathVariable String newKind) { // 66666
        return dogManagementService.updateDogByKind(name,ownerName,ownerPhoneNumber,newKind);
    }

    @PatchMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}/{medicalReport}") // 77777
    public ResponseEntity<?> addMedicalReport(@PathVariable String name, @PathVariable  String ownerName, @PathVariable  String ownerPhoneNumber , @PathVariable String medicalReport){
        dogManagementService.medicalRecords(name,ownerName,ownerPhoneNumber,medicalReport);
        return ResponseEntity.ok("MedicalReport Updated");
    }

}