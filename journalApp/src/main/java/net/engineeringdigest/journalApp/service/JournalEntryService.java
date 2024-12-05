package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // Change to Service annotation
import java.util.List;
import org.bson.types.ObjectId;

import java.util.Optional;
@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository; // Follow camelCase naming convention

    // Save a journal entry
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    // You can add other service methods here (like get, update, delete) as needed

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);

    }
}


//controller ---calls----> service ----calls--->repository