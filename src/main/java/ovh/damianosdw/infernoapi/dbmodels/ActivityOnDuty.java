/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.dbmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ovh.damianosdw.infernoapi.endpoints.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "activity_on_duty")
public class ActivityOnDuty
{
    @Id
    @GeneratedValue
    @Column(name = "ACTIVITY_ID", nullable = false)
    private int activityId;
    @OneToMany
    private User userId;
    @Basic
    @Column(name = "activity_date", nullable = false)
    private LocalDateTime activityDate;
    @Column(name = "activity_description", nullable = false)
    private String activityDescription;

}
