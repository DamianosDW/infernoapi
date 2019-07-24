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
@Table(name = "error_reports_messages")
public class ErrorReportMessage
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID")
    private int messageId;
    @Column(name = "REPORT_ID", nullable = false)
    private int reportId;
    @Column(name = "USER_ID", nullable = false)
    private int userId;
    @Basic
    @Column(name = "message_sent_date", nullable = false)
    private LocalDateTime messageSentDate;
    @Column(name = "message", nullable = false)
    @Type(type = "text")
    private String message;
}
