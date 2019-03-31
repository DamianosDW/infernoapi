package ovh.damianosdw.infernoapi.endpoints.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue
    @Column(name = "USER_ID", nullable = false, unique = true)
    private int userId;
    @Column(name = "user", nullable = false)
    private String username;
    private String password;
    @Column(name = "POSITION_ID", nullable = false)
    private int positionId;
    private boolean active;
    @Column(name = "avatar_url")
    private String avatarURL;
}
