package net.engineeringdigest.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    // Get all journal entries
    @GetMapping
    public ResponseEntity<?> getAll(){
     List<JournalEntry>  all = journalEntryService.getAll();
      if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all ,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    
    }
    // Create a new journal entry
    @PostMapping
    public ResponseEntity <JournalEntry>createEntry(@RequestBody JournalEntry myEntry) {

        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
       
    }

    // Get a journal entry by ID
    @GetMapping("/id/{myId}")
    public ResponseEntity <JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry= journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK); //as journalentry is object that why we need to remove it from box so we use .get
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a journal entry by ID
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId myId) {
       journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update a journal entry by ID
    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        JournalEntry old =journalEntryService.findById(id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("")? newEntry.getTitle():old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.equals("")? newEntry.getContent():old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       
    }
} 
