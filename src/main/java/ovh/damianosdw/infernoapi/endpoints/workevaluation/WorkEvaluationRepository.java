/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.workevaluation;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.WorkEvaluation;

import java.util.List;

public interface WorkEvaluationRepository extends JpaRepository<WorkEvaluation, Integer>, WorkEvaluationCustom
{
    int countWorkEvaluationsByUserId(int userId);
    List<WorkEvaluation> getWorkEvaluationsByUserIdOrderBySentDateDesc(int userId);
}