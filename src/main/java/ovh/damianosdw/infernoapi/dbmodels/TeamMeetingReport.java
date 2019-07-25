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
@Table(name = "team_meeting_reports")
public class TeamMeetingReport
{
    @Id
    @GeneratedValue
    @Column(name = "REPORT_ID")
    private int reportId;
    @Column(name = "USER_ID", nullable = false)
    private int userId;
    @Basic
    @Column(name = "sent_date", nullable = false)
    private LocalDateTime sentDate;
    @Column(name = "team_meeting_report", nullable = false)
    @Type(type = "text")
    private String teamMeetingReport;
}
