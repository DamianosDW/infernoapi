/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.teammeetingsmodule;

import java.time.LocalDateTime;

public interface TeamMeetingsModuleCustom
{
    void resetTeamMeetingDates();
    void setTeamMeetingDateAsConfirmed(LocalDateTime teamMeetingDate);
}
