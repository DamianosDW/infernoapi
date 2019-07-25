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
@Table(name = "team_meetings_module")
public class TeamMeetingsModule
{
    @Id
    @GeneratedValue
    @Column(name = "TMM_ID")
    private int teamMeetingsModuleId;
    @Basic
    @Column(name = "team_meeting_date", nullable = false)
    private LocalDateTime teamMeetingDate;
    @Column(name = "date_confirmed", nullable = false)
    private boolean dateConfirmed;
}
