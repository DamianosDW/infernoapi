/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.teammeetingsvotes;

import org.springframework.stereotype.Repository;
import ovh.damianosdw.infernoapi.endpoints.users.UserAvatar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class TeamMeetingsVotesCustomImpl implements TeamMeetingsVotesCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int getNumberOfUserVotes(int userId, LocalDateTime currentDateTime)
    {
        return entityManager.createNativeQuery("SELECT COUNT(*) FROM team_meetings_votes INNER JOIN team_meetings_module ON team_meetings_votes.TMM_ID = team_meetings_module.TMM_ID WHERE USER_ID = ? AND team_meeting_date BETWEEN ? AND ?")
                .setParameter(1, userId)
                .setParameter(2, currentDateTime)
                .setParameter(3, currentDateTime.plusYears(1))
                .getFirstResult();
    }

    @Override
    public void removeUserVotes(int userId)
    {
        entityManager.createNativeQuery("DELETE FROM team_meetings_votes WHERE USER_ID = ?")
                .setParameter(1, userId)
                .executeUpdate();
    }
}
