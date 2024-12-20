package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
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

    @Autowired
    private UserService userService;
    // Get all journal entries
    @GetMapping ("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
         User user = userService.findByUserName(userName);
     //List<JournalEntry>  all = journalEntryService.getAll();
        List<JournalEntry>  all = user.getJournal_entries();
      if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all ,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    
    }
    // Create a new journal entry=
    @PostMapping("{userName}")
    public ResponseEntity <JournalEntry>createEntry(@RequestBody JournalEntry myEntry, @PathVariable String  userName) {

        try {

            journalEntryService.saveEntry(myEntry , userName);
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
    @DeleteMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId myId, @PathVariable String userName) {
       journalEntryService.deleteById(myId ,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update a journal entry by ID
    @PutMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId,
                                               @RequestBody JournalEntry newEntry ,
                                               @PathVariable String userName) {
        JournalEntry old =journalEntryService.findById(myId).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("")? newEntry.getTitle():old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.equals("")? newEntry.getContent():old.getContent());
            journalEntryService.saveEntry(old );
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       
    }
} 
