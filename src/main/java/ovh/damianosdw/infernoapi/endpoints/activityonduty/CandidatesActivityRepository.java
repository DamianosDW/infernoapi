/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.activityonduty;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.CandidatesActivity;

import java.util.List;

public interface CandidatesActivityRepository extends JpaRepository<CandidatesActivity, Integer>
{
    List<CandidatesActivity> getActivityOnDutyByUserId(int user);
}
