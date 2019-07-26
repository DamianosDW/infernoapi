/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.teammeetingsvotes;

import java.time.LocalDateTime;

public interface TeamMeetingsVotesCustom
{
    int getNumberOfUserVotes(int userId, LocalDateTime currentDateTime);
    void removeUserVotes(int userId);
}
