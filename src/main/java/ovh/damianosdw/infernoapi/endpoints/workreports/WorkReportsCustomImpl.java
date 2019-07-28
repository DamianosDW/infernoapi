/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.workreports;

import org.springframework.stereotype.Repository;
import ovh.damianosdw.infernoapi.dbmodels.WorkReport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class WorkReportsCustomImpl implements WorkReportsCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<WorkReport> getAdminReportsAndOrderThemBySentDateDesc(int adminLeaderPositionId)
    {
        List resultList = entityManager.createNativeQuery("SELECT REPORT_ID, work_reports.USER_ID, sent_date, work_report FROM work_reports INNER JOIN users ON work_reports.USER_ID = users.USER_ID WHERE POSITION_ID <> ? ORDER BY sent_date DESC")
                .setParameter(1, adminLeaderPositionId)
                .getResultList();

        List<WorkReport> workReports = new ArrayList<>();

        for(Object result : resultList)
        {
            workReports.add(convertDataToProperFormat((Object[]) result));
        }

        return workReports;
    }

    @Override
    public List<WorkReport> getAdminLeaderReportsAndOrderBySentDateDesc(int adminLeaderPositionId)
    {
        List resultList = entityManager.createNativeQuery("SELECT REPORT_ID, work_reports.USER_ID, sent_date, work_report FROM work_reports INNER JOIN users ON work_reports.USER_ID = users.USER_ID WHERE POSITION_ID = ? ORDER BY sent_date DESC")
                .setParameter(1, adminLeaderPositionId)
                .getResultList();

        List<WorkReport> workReports = new ArrayList<>();

        for(Object result : resultList)
        {
            workReports.add(convertDataToProperFormat((Object[]) result));
        }
        return workReports;
    }

    private WorkReport convertDataToProperFormat(Object[] data)
    {
        WorkReport workReport = new WorkReport();

        workReport.setReportId((int) data[0]);
        workReport.setUserId((int) data[1]);
        workReport.setSentDate(LocalDateTime.ofInstant(((Timestamp) data[2]).toInstant(), ZoneId.of("UTC+02:00")));
        workReport.setWorkReport((String) data[3]);

        return workReport;
    }

    @Override
    public LocalDateTime getProperUserWorkReportSentDate(int userId)
    {
        List resultList = entityManager.createNativeQuery("SELECT sent_date FROM work_reports WHERE USER_ID = ? AND YEAR(sent_date) = ? ORDER BY sent_date DESC LIMIT 1")
                .setParameter(1, userId)
                .setParameter(2, LocalDateTime.now())
                .getResultList();

        if(!resultList.isEmpty())
        {
            Timestamp timestamp = (Timestamp) resultList.get(0);
            return LocalDateTime.ofInstant(timestamp.toInstant(), ZoneId.of("UTC+02:00"));
        }
        else
            return null;
    }
}
