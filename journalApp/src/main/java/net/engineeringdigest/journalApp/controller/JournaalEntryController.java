package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.engineeringdigest.journalApp.entity.JournalEntry;

@RestController
@RequestMapping("/journal")
public class JournaalEntryController {
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    // Endpoint to get all journal entries

    //Method Inside a Controller class should be public so that they can be 
    //accessed and invoked by the spring framework or external http requests
    @GetMapping //("/abc")
    public List<JournalEntry> getAll() {                        //method
        return new ArrayList<>(journalEntries.values());        //method
    }//method



    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(),myEntry);
        return true;

    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId){
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntry(@PathVariable Long myId){
        return journalEntries.remove(myId);
    }


    @PutMapping("/id/{id}")
    public JournalEntry updateJournalById(@PathVariable Long id ,@RequestBody JournalEntry myEntry  ){
        return journalEntries.put(id,myEntry);
    }
}
