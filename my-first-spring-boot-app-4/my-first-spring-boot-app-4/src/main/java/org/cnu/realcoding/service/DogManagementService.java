package org.cnu.realcoding.service;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogNotFoundException;
import org.cnu.realcoding.exception.ExistDogException;
import org.cnu.realcoding.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogManagementService {

    @Autowired
    private DogRepository dogRepository;

    public void insertDog(Dog dog) {
        List<Dog> dogList = getAllDogs();
        for (int i = 0; i < dogList.size(); i++) {
            Dog check = dogList.get(i);
            if (check.getName().equals(dog.getName()) && check.getOwnerName().equals(dog.getOwnerName()) && check.getOwnerPhoneNumber().equals(dog.getOwnerPhoneNumber())) {
                throw new ExistDogException();
            }
        }
        dogRepository.insertDog(dog);
    }


    public List<Dog> getDogByName(String name) {
        List<Dog> dog = dogRepository.findDogByName(name);

        if (dog == null) {
            throw new DogNotFoundException();
        }

        return dog;
    }

    public List<Dog> getAllDogs() {
        return dogRepository.findAllDog();
    }


    public List<Dog> getDogByOwnerName(String ownerName) {
        List<Dog> dog = dogRepository.findDogByOwnerName(ownerName);

        if (dog == null) {
            throw new DogNotFoundException();
        }

        return dog;
    }

    public List<Dog> getDogByOwnerPhoneNumber(String ownerPhoneNumber) {
        List<Dog> dog = dogRepository.findDogByOwnerPhoneNumber(ownerPhoneNumber);

        if (dog == null) {
            throw new DogNotFoundException();
        }

        return dog;
    }

    //==========================================
    public Dog updateDogByKind(String name, String ownerName, String ownerPhoneNumber , String newKind) { // 66666
        Dog dog = getDogByAll(name,ownerName,ownerPhoneNumber);

        if(dog == null){
            throw new DogNotFoundException();
        }

        dogRepository.updateDogByKind(name, ownerName , ownerPhoneNumber ,newKind);
        return dog;
    }

    public void medicalRecords(String name, String ownerName, String ownerPhoneNumber, String report) { // 77777
        Dog dog = getDogByAll(name,ownerName,ownerPhoneNumber);

        if (dog == null) {
            throw new DogNotFoundException();
        }

        dogRepository.addMedicalReport(name,ownerName,ownerPhoneNumber,report);
    }

    public Dog updateDogAll(String name, String ownerName, String ownerPhoneNumber,
                            String newName, String newKind, String newOwnerName,
                            String newOwnerPhoneNumber) {

        Dog dog = dogRepository.findDogByAll(name, ownerName, ownerPhoneNumber);

        if(dog == null){
            throw new DogNotFoundException();
        }

        Dog existDog = dogRepository.findDogByAll(newName,newOwnerName,newOwnerPhoneNumber);

        if(existDog != null){
            throw new ExistDogException();
        }

        dogRepository.updateDogAll(name, ownerName , ownerPhoneNumber ,newName , newKind
                , newOwnerName, newOwnerPhoneNumber);

        return dog;
    }

    //=====================================
    public Dog getDogByAll(String name, String ownerName, String ownerPhoneNumber) {
        Dog dog = dogRepository.findDogByAll(name, ownerName, ownerPhoneNumber);

        if (dog == null) {
            throw new DogNotFoundException();
        }

        return dog;
    }
}