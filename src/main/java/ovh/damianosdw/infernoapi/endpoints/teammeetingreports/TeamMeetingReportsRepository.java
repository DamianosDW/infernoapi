/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.teammeetingreports;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.TeamMeetingReport;

public interface TeamMeetingReportsRepository extends JpaRepository<TeamMeetingReport, Integer>
{

}
