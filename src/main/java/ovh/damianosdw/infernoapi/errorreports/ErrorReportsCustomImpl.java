/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.errorreports;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ErrorReportsCustomImpl implements ErrorReportsCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void changeErrorReportStatus(int reportId, boolean problemSolved)
    {
        entityManager.createNativeQuery("UPDATE error_reports SET problem_solved = ? WHERE REPORT_ID = ?")
                .setParameter(1, problemSolved)
                .setParameter(2, reportId)
                .executeUpdate();
    }
}
