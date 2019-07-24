/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.errorreports;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.ErrorReport;

import java.util.List;

public interface ErrorReportsRepository extends JpaRepository<ErrorReport, Integer>
{
    List<ErrorReport> getErrorReportsByProblemSolved(boolean problemSolved);
    List<ErrorReport> getErrorReportsByUserId(int userId);
    List<ErrorReport> getErrorReportsByUserIdAndProblemSolved(int userId, boolean problemSolved);
}
