/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.workevaluation;

import org.springframework.stereotype.Repository;
import ovh.damianosdw.infernoapi.dbmodels.WorkEvaluation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class WorkEvaluationCustomImpl implements WorkEvaluationCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void updateWorkEvaluation(WorkEvaluation workEvaluation)
    {
        entityManager.createNativeQuery("UPDATE work_evaluation SET evaluation = ?, reason = ? WHERE WORK_EVALUATION_ID = ?")
                .setParameter(1, workEvaluation.getEvaluation())
                .setParameter(2, workEvaluation.getReason())
                .setParameter(3, workEvaluation.getWorkEvaluationId())
                .executeUpdate();
    }
}
