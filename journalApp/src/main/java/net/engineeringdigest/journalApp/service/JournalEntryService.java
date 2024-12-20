package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // Change to Service annotation

import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;

import java.util.Optional;
@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository; // Follow camelCase naming convention
    @Autowired
    private  UserService userService;

    // Save a journal entry
    public void saveEntry(JournalEntry journalEntry , String userName) {
        User user = userService.findByUserName(userName);
        journalEntry.setDate((LocalDateTime.now()) );
        JournalEntry saved =journalEntryRepository.save(journalEntry);
        user.getJournal_entries().add(saved);
        userService.saveEntry(user);


    }

    public void saveEntry(JournalEntry journalEntry ) {
        journalEntryRepository.save(journalEntry);


    }





    // You can add other service methods here (like get, update, delete) as needed

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournal_entries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);


    }
}


//controller ---calls----> service ----calls--->repository