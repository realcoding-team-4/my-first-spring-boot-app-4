package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DogRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Dog> findDogByName(String name) {
        return mongoTemplate
                .find(
                        Query.query(Criteria.where("name").is(name)),
                        Dog.class // T -> generic 여기서는 Dog 객체
                        ,"name");
    }

    public List<Dog> findDogByOwnerName(String ownerName) {
        return mongoTemplate
                .find(
                        Query.query(Criteria.where("ownerName").is(ownerName)),
                        Dog.class // T -> generic 여기서는 Dog 객체
                        ,"ownerName");
    }

    public List<Dog> findDogByOwnerPhoneNumber(String ownerPhoneNumber) {
        return mongoTemplate
                .find(
                        Query.query(Criteria.where("ownerPhoneNumber").is(ownerPhoneNumber)),
                        Dog.class // T -> generic 여기서는 Dog 객체
                        ,"ownerPhoneNumber");

    }

    public Dog findDogByKind(String kind) {
        return mongoTemplate
                .findOne(
                        Query.query(Criteria.where("kind").is(kind)),
                        Dog.class
                );
    }

    //==============================
    public void updateDogByKind(String name, String ownerName, String ownerPhoneNumber, String newKind) { // 66666
        Update update = new Update();
        update.set("kind", newKind);

        mongoTemplate.updateMulti(
                Query.query(Criteria.where("name").is(name)
                        .andOperator(Criteria.where("ownerName").is(ownerName)
                                .andOperator(Criteria.where("ownerPhoneNumber").is(ownerPhoneNumber)))),
                update, Dog.class);
    }

    public void addMedicalReport(String name, String ownerName, String ownerPhoneNumber, String report) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("name").is(name)
                        .andOperator(Criteria.where("ownerName").is(ownerName)
                                .andOperator(Criteria.where("ownerPhoneNumber").is(ownerPhoneNumber)))),
                new Update().push("medicalRecords", report), Dog.class);
    }

    public void updateDogAll(String name, String ownerName, String ownerPhoneNumber,
                             String newName, String newKind, String newOwnerName,
                             String newOwnerPhoneNumber) {
        Update update = new Update();
        update.set("name",newName);
        update.set("kind",newKind);
        update.set("ownerName",newOwnerName);
        update.set("ownerPhoneNumber",newOwnerPhoneNumber);


        mongoTemplate.updateMulti(
                Query.query(Criteria.where("name").is(name)
                        .andOperator(Criteria.where("ownerName").is(ownerName)
                                .andOperator(Criteria.where("ownerPhoneNumber").is(ownerPhoneNumber)))),
                update,Dog.class);

    }

    //==============================
    public void insertDog(Dog dog) {
        mongoTemplate.insert(dog);
    }

    public List<Dog> findAllDog() {
        return mongoTemplate.findAll(Dog.class);
    }

    public Dog findDogByAll(String name, String ownerName, String ownerPhoneNumber) {
        return mongoTemplate
                .findOne(
                        Query.query(Criteria.where("name").is(name)
                                .andOperator(Criteria.where("ownerName").is(ownerName)
                                        .andOperator(Criteria.where("ownerPhoneNumber").is(ownerPhoneNumber)))),
                        Dog.class // T -> generic 여기서는 Dog 객체
                );
    }
}