package gads.web.financeOn.infrastructure.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collation = "user_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    private String id;
    private String PrimaryName;
    private String SecondName;
    private String email;
    private String password;
    private String document;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

}
