/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.workevaluation.rules;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ImportantInfoCustomImpl implements ImportantInfoCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void changeWorkEvaluationRules(String rules)
    {
        entityManager.createNativeQuery("UPDATE important_info SET content = ? WHERE info_type = ?")
                .setParameter(1, rules)
                .setParameter(2, "Zasady oceniania")
                .executeUpdate();
    }
}
