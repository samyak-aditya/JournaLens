package com.harsh.journalApp.controllers;

import com.harsh.journalApp.entities.JournalEntry;
import com.harsh.journalApp.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    // to get all the entries
    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    // to create a new entry in the db
    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    // to get a specific entry based on it's id
    @GetMapping("/id/{myId}")
    public JournalEntry getJournalById(@PathVariable ObjectId myId){
        return journalEntryService.getById(myId).orElse(null);
    }

    // to delete a specific entry based on it's id
    @DeleteMapping("/id/{myId}")
    public boolean deleteJournalById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;
    }

    // to update a specific entry based on it's id
    @PutMapping("/id/{myId}")
    public JournalEntry updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){
        JournalEntry prevEntry = journalEntryService.getById(myId).orElse(null);
        if(prevEntry != null){
            prevEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isBlank() ? newEntry.getTitle() : prevEntry.getTitle());
            prevEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : prevEntry.getContent());
        }

        journalEntryService.saveEntry(prevEntry);
        return prevEntry;
    }
}
