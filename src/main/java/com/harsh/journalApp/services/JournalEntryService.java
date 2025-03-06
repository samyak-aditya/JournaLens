package com.harsh.journalApp.services;

import com.harsh.journalApp.entities.JournalEntry;
import com.harsh.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;  // dependency injection

    // method that saves an entry in the db using the mongo repository
    public void saveEntry(JournalEntry myEntry){
        journalEntryRepository.save(myEntry);
    }

    // method that returns all the journals present in our db
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    // method that returns a specific journal based on it's id
    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    // method that delete a specific journal based on it's id
    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }
}
