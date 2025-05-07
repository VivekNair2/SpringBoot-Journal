package com.example.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.journalApp.entitry.JournalEntry;
import com.example.journalApp.repositiory.JournalEntryRepository;
@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry entry) {
        journalEntryRepository.save(entry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }
    
    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return Optional.ofNullable(journalEntryRepository.findById(id).orElse(null));
    }
    public void deleteById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }
    
}
//controller --> service --> repository --> entity --> main class