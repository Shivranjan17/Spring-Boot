package net.engineeringdigest.journalApp.service;


import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // Change to Service annotation
import java.util.List;
import org.bson.types.ObjectId;

import java.util.Optional;
@Service
public class UserService {

    @Autowired
    private UserEntryRepository UserEntryRepository; // Follow camelCase naming convention

    // Save a journal entry
    public void saveEntry(User user) {
        UserEntryRepository.save(user);
    }

    // You can add other service methods here (like get, update, delete) as needed

    public List<User> getAll(){
        return UserEntryRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return UserEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        UserEntryRepository.deleteById(id);

    }

    public User findByUserName(String userName){
        return UserEntryRepository.findByUserName(userName);
    }
}


//controller ---calls----> service ----calls--->repository