package com.example.journalApp.entitry;



import java.time.LocalDateTime;

import org.bson.types.ObjectId;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data // this will generat  e getters and setters for all the fields in the class 
// this does the work of @Setter and @Getter @ToString @EqualsAndHashCode @RequiredArgsConstructor
@Document(collection = "jorunal_entries")
public class JournalEntry {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
   
}