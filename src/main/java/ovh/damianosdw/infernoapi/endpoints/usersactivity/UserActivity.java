package ovh.damianosdw.infernoapi.endpoints.usersactivity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_activity")
public class UserActivity
{
    @Column(name = "ACTIVITY_ID", nullable = false, unique = true)
    @Id
    @GeneratedValue
    private int activityId;
    @Column(name = "USER_ID", nullable = false)
    private int userId;
    @Basic
    private LocalDateTime activityDate;
    private String activityDescription;
}
