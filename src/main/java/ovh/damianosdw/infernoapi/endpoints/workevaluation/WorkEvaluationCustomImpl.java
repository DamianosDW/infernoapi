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
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class WorkEvaluationCustomImpl implements WorkEvaluationCustom
{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<WorkEvaluation> getUserWorkEvaluationsAndOrderThemBySentDateDesc(int userId)
    {
        List resultList = entityManager.createNativeQuery("SELECT * FROM work_evaluation INNER JOIN infernocp.users ON infernocp.users.USER_ID = work_evaluation.USER_ID WHERE work_evaluation.USER_ID = ? ORDER BY sent_date DESC")
                .setParameter(1, userId)
                .getResultList();

        List<WorkEvaluation> workEvaluations = new ArrayList<>();

        for(Object result : resultList)
        {
            if(result instanceof WorkEvaluation)
            {
                WorkEvaluation workEvaluation = (WorkEvaluation) result;
                workEvaluations.add(workEvaluation);
            }
        }

        return workEvaluations;
    }
}
