/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.dbmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "team_meetings_votes")
public class TeamMeetingVote
{
    @Id
    @GeneratedValue
    @Column(name = "TMV_ID")
    private int teamMeetingVoteId;
    @Column(name = "TMM_ID", nullable = false)
    private int teamMeetingsModuleId;
    @Column(name = "USER_ID", nullable = false)
    private int userId;
}
