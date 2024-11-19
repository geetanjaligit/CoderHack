package com.project.CoderHack.entity;

import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
// import javax.validation.constraints.NotNull;

@Document(collection="users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @NonNull
    private String id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    @Min(value = 0, message = "Score must be at least 0")
    @Max(value = 100, message = "Score must be at most 100")
    private int score=0;
    private Set<String> badges = new HashSet<>();
}
