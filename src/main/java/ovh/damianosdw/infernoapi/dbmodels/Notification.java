/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.dbmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "global_notifications")
public class Notification
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTIFICATION_ID")
    private int notificationId;
    @Column(name = "notification_title", nullable = false)
    private String notificationTitle;
    @Basic
    @Column(name = "send_after", nullable = false)
    private LocalDateTime sendAfter;
    @Column(name = "send_to_person", nullable = false)
    private int sendToPerson;
    @Column(name = "notification_content", nullable = false)
    @Type(type = "text")
    private String notificationContent;
    @Column(name = "active", nullable = false)
    private boolean active;
}
