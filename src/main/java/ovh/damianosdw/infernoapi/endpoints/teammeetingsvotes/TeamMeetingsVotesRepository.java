/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.teammeetingsvotes;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.TeamMeetingVote;

import java.util.List;

public interface TeamMeetingsVotesRepository extends JpaRepository<TeamMeetingVote, Integer>, TeamMeetingsVotesCustom
{
    List<TeamMeetingVote> getTeamMeetingVotesByUserId(int userId);
}
