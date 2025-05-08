package com.example.journalApp.controller;

import java.time.LocalDateTime;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.journalApp.entity.User;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.service.JournalEntryService;
import com.example.journalApp.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    // all the endpoints in the controller class should be public
    @GetMapping()
    public ResponseEntity<JournalEntry> getAllJournalEntriesOfUsers(@PathVariable String userName) {
        // Get the user by username
        User user = userService.findyByUserName(userName);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        if (journalEntries != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry, @PathVariable String userName) {
        try {
            entry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(entry, userName);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId id) {
        Optional<JournalEntry> journalentry = journalEntryService.getEntryById(id);
        if (journalentry.isPresent()) {
            return new ResponseEntity<>(journalentry.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id/{userName}/{id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId id, @PathVariable String userName) {
        journalEntryService.deleteById(id, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry entry) {
        Optional<JournalEntry> oldOptional = journalEntryService.getEntryById(id);
        if (oldOptional.isPresent()) {
            JournalEntry old = oldOptional.get();
            old.setTitle(entry.getTitle() != null && !entry.getTitle().isEmpty() ? entry.getTitle() : old.getTitle());
            old.setContent(entry.getContent() != null && !entry.getContent().isEmpty() ? entry.getContent()
                    : old.getContent());
            old.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(old);
            return ResponseEntity.ok(old);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
