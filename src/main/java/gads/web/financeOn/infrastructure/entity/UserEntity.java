package gads.web.financeOn.infrastructure.entity;

import gads.web.financeOn.business.enums.PerfilUserStatus;
import lombok.*;
import org.apache.catalina.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document("user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity  {

    @Id
    private String id;
    private String PrimaryName;
    private String SecondName;
    @Indexed(unique = true)
    private String email;
    private String password;
    private PerfilUserStatus perfil;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
