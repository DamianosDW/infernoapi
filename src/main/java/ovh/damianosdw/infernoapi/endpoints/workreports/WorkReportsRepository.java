/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.workreports;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.WorkReport;

import java.util.List;

public interface WorkReportsRepository extends JpaRepository<WorkReport, Integer>, WorkReportsCustom
{
    List<WorkReport> getWorkReportsByUserId(int userId);
}
