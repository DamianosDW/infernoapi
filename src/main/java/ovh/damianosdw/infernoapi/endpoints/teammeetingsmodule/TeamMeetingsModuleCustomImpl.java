/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.teammeetingsmodule;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
@Transactional
public class TeamMeetingsModuleCustomImpl implements TeamMeetingsModuleCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void resetTeamMeetingDates()
    {
        entityManager.createNativeQuery("UPDATE team_meetings_module SET date_confirmed = 0")
                .executeUpdate();
    }

    @Override
    public void setTeamMeetingDateAsConfirmed(LocalDateTime teamMeetingDate)
    {
        entityManager.createNativeQuery("UPDATE team_meetings_module SET date_confirmed = 1 WHERE team_meeting_date = ?")
                .setParameter(1, teamMeetingDate)
                .executeUpdate();
    }
}