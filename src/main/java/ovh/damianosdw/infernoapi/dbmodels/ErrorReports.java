/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.dbmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "error_reports")
public class ErrorReports
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPORT_ID")
    private int reportId;
    @Column(name = "USER_ID", nullable = false)
    private int userId;
    @Column(name = "report_title", nullable = false)
    private String reportTitle;
    @Basic
    @Column(name = "sent_date", nullable = false)
    private LocalDateTime sentDate;
    @Column(name = "report_priority", nullable = false)
    private String reportPriority;
    @Column(name = "report_description", nullable = false)
    private String reportDescription;
    @Column(name = "problem_solved", nullable = false)
    private boolean problemSolved;
}
