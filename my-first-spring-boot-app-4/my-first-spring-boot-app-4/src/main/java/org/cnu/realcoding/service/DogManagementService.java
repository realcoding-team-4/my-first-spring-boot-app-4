package org.cnu.realcoding.service;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogNotFoundException;
import org.cnu.realcoding.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogManagementService {

    @Autowired
    private DogRepository dogRepository;

    public void insertDog(Dog dog){
        dogRepository.insertDog(dog);
    }

    public Dog getDogByName(String name){
        Dog dog = dogRepository.findDog(name);

        if (dog == null){
            throw new DogNotFoundException();
        }

        return dog;
    }

    public List<Dog> getAllDogs() {
        return dogRepository.findAllDog();
    }
}
