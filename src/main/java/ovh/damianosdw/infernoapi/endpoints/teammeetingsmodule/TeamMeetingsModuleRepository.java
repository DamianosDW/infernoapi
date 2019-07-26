/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.teammeetingsmodule;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.TeamMeetingsModule;

import java.time.LocalDateTime;
import java.util.List;

public interface TeamMeetingsModuleRepository extends JpaRepository<TeamMeetingsModule, Integer>, TeamMeetingsModuleCustom
{
    TeamMeetingsModule getTeamMeetingsModuleByTeamMeetingDate(LocalDateTime teamMeetingDate);
    TeamMeetingsModule getTeamMeetingsModuleByDateConfirmedIsTrueAndTeamMeetingDateBetween(LocalDateTime currentDateTime, LocalDateTime currentDateTimePlusYear);
    List<TeamMeetingsModule> getTeamMeetingsModulesByTeamMeetingDateBetweenOrderByTeamMeetingDateAsc(LocalDateTime currentDateTime, LocalDateTime currentDateTimePlusYear);

}
