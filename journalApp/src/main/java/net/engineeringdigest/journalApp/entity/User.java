package net.engineeringdigest.journalApp.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.ArrayList;

import com.mongodb.lang.NonNull;

import lombok.Data;



@Document(collection = "users")
@Data
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique=true)
    @NonNull
    private String userName;
    @NonNull
    private String password;

    @DBRef
    private List<JournalEntry> journal_entries = new ArrayList<>();
}
