/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.workreports;

import ovh.damianosdw.infernoapi.dbmodels.WorkReport;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkReportsCustom
{
    List<WorkReport> getAdminReportsAndOrderThemBySentDateDesc(int adminLeaderPositionId);
    List<WorkReport> getAdminLeaderReportsAndOrderBySentDateDesc(int adminLeaderPositionId);
    LocalDateTime getProperUserWorkReportSentDate(int userId);
}
