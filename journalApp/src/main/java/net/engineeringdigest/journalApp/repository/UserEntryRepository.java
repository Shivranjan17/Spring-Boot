package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.bson.types.ObjectId;




@Repository
public interface UserEntryRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);
}
