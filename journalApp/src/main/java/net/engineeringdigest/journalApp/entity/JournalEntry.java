package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Document(collection = "journal_entries") // Maps this class to the MongoDB collection "journal_entries"
@Getter
@Setter
@Data
@NoArgsConstructor

public class JournalEntry {

    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content; // Variable name updated to follow Java conventions
    private LocalDateTime date;

    // // Getter and Setter for id
    // public ObjectId getId() {
    //     return id;
    // }

    // public void setId(ObjectId id) {
    //     this.id = id;
    // }

    // // Getter and Setter for title
    // public String getTitle() {
    //     return title;
    // }

    // public void setTitle(String title) {
    //     this.title = title;
    // }

    // // Getter and Setter for content
    // public String getContent() {
    //     return content;
    // }

    // public void setContent(String content) {
    //     this.content = content;
    // }

    // // Getter and Setter for date
    // public LocalDateTime getDate() {
    //     return date;
    // }

    // public void setDate(LocalDateTime date) {
    //     this.date = date;
    // }
}
