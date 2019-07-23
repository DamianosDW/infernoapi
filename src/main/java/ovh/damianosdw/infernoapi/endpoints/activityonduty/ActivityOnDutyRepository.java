/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.activityonduty;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.ActivityOnDuty;

import java.util.List;

public interface ActivityOnDutyRepository extends JpaRepository<ActivityOnDuty, Integer>
{
    List<ActivityOnDuty> getActivityOnDutyByUserId(int user);
}
