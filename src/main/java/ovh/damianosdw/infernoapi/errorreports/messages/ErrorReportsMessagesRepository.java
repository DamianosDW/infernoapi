/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.errorreports.messages;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.ErrorReportMessage;

import java.util.List;

public interface ErrorReportsMessagesRepository extends JpaRepository<ErrorReportMessage, Integer>
{
    List<ErrorReportMessage> getErrorReportMessagesByReportId(int reportId);
}
