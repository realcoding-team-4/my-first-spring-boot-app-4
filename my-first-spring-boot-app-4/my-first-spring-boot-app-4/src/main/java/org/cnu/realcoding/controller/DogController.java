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


    @GetMapping("/dogs/_{name}") //인자가 하나인 url들의 모호한 mapping 주소 방지를 위해 _ 추가
    public List<Dog> getDogByName(@PathVariable String name){ // 같은 이름을 가진 강아지들의 정보를 List<Dog> 타입으로 반환
        return dogManagementService.getDogByName(name);
    }

    @GetMapping("/dogs/__{ownerName}") //인자가 하나인 url들의 모호한 mapping 주소 방지를 위해 __ 추가
    public List<Dog> getDogByOwnerName(@PathVariable String ownerName){// 같은 주인이름을 가진 강아지들의 정보를 List<Dog> 타입으로 반환
        return dogManagementService.getDogByOwnerName(ownerName);
    }

    @GetMapping("/dogs/___{ownerPhoneNumber}")//인자가 하나인 url들의 모호한 mapping 주소 방지를 위해 ___ 추가
    public List<Dog> getDogByOwnerPhoneNumber(@PathVariable String ownerPhoneNumber){// 같은 주인번호를 가진 강아지들의 정보를 List<Dog> 타입으로 반환
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

    @PatchMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}/_{newKind}") //인자가 네개인 url들의 모호한 mapping 주소 방지를 위해 _ 추가
    public Dog updateDogByKind(@PathVariable String name, @PathVariable String ownerName, @PathVariable String ownerPhoneNumber, @PathVariable String newKind) { // 66666
        return dogManagementService.updateDogByKind(name,ownerName,ownerPhoneNumber,newKind);
    }

    @PatchMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}/__{medicalReport}") //인자가 네개인 url들의 모호한 mapping 주소 방지를 위해 __ 추가
    public ResponseEntity<?> addMedicalReport(@PathVariable String name, @PathVariable  String ownerName, @PathVariable  String ownerPhoneNumber , @PathVariable String medicalReport){
        dogManagementService.medicalRecords(name,ownerName,ownerPhoneNumber,medicalReport);
        return ResponseEntity.ok("MedicalReport Updated");
    }


    // 실전코딩 Spring : my-first-spring-boot-app-4 구현 <동물병원 강아지 관리를 위한 BackEnd 서버 구현>
}