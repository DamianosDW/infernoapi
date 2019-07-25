/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.teammeetingreports;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.TeamMeetingsModule;

public interface TeamMeetingsModuleRepository extends JpaRepository<TeamMeetingsModule, Integer>
{

}
