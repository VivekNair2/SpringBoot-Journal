package com.example.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.entity.User;
import com.example.journalApp.repositiory.JournalEntryRepository;
@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;
    public void saveEntry(JournalEntry entry,String userName) {
        User user=userService.findyByUserName(userName);
        entry.setDate(LocalDateTime.now());
        JournalEntry saved =journalEntryRepository.save(entry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    }
    public void saveEntry(JournalEntry entry) {
        
        journalEntryRepository.save(entry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }
    
    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return Optional.ofNullable(journalEntryRepository.findById(id).orElse(null));
    }
    public void deleteById(ObjectId id,String userName) {
        User user=userService.findyByUserName(userName);
        user.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);
    }
    
}
//controller --> service --> repository --> entity --> main class