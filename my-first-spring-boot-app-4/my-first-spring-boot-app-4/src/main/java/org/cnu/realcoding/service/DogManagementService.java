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

    public void insertDog(Dog dog){
        List<Dog> dogList = getAllDogs();
        for(int i = 0; i < dogList.size(); i++){
            Dog check = dogList.get(i);
            if(check.getName().equals(dog.getName()) && check.getOwnerName().equals(dog.getOwnerName()) && check.getOwnerPhoneNumber().equals(dog.getOwnerPhoneNumber())){
                throw new ExistDogException();
            }
        }
        dogRepository.insertDog(dog);
    }



    public Dog getDogByName(String name){
        Dog dog = dogRepository.findDogByName(name);

        if (dog == null){
            throw new DogNotFoundException();
        }

        return dog;
    }

    public List<Dog> getAllDogs() {
        return dogRepository.findAllDog();
    }


    public Dog getDogByOwnerName(String ownerName) {
        Dog dog = dogRepository.findDogByOwnerName(ownerName);

        if (dog == null){
            throw new DogNotFoundException();
        }

        return dog;
    }

    public Dog getDogByOwnerPhoneNumber(String ownerPhoneNumber) {
        Dog dog = dogRepository.findDogByOwnerPhoneNumber(ownerPhoneNumber);

        if (dog == null){
            throw new DogNotFoundException();
        }

        return dog;
    }

    public Dog getDogByAll(String name, String ownerName, String ownerPhoneNumber) {
        Dog dog = dogRepository.findDogByAll(name, ownerName, ownerPhoneNumber);

        if (dog == null){
            throw new DogNotFoundException();
        }

        return dog;
    }
}