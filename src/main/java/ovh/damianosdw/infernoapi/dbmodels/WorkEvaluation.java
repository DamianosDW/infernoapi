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
@Table(name = "work_evaluation")
public class WorkEvaluation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WORK_EVALUATION_ID")
    private int workEvaluationId;
    @Column(name = "ADMIN_ID", nullable = false)
    private int adminId;
    @Column(name = "USER_ID", nullable = false)
    private int userId;
    @Basic
    @Column(name = "sent_date", nullable = false)
    private LocalDateTime sentDate;
    @Column(name = "evaluation", nullable = false)
    private String evaluation;
    @Column(name = "reason", nullable = false)
    @Type(type = "text")
    private String reason;
}
