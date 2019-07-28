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
@Table(name = "work_reports")
public class WorkReport
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPORT_ID")
    private int reportId;
    @Column(name = "USER_ID", nullable = false)
    private int userId;
    @Basic
    @Column(name = "sent_date", nullable = false)
    private LocalDateTime sentDate;
    @Column(name = "work_report", nullable = false)
    @Type(type = "text")
    private String workReport;
}
