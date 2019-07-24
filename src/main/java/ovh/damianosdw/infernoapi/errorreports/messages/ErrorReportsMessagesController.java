/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.errorreports.messages;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.dbmodels.ErrorReportMessage;
import ovh.damianosdw.infernoapi.exceptions.ResourceNotFoundException;
import ovh.damianosdw.infernoapi.exceptions.SqlQueryErrorException;

import java.util.List;

@RestController
@RequestMapping("/infernoapi/errorReports/messages")
@AllArgsConstructor
public class ErrorReportsMessagesController
{
    private final ErrorReportsMessagesRepository errorReportsMessagesRepository;

    @GetMapping("{reportId}")
    public List<ErrorReportMessage> getErrorReportMessagesByReportId(@PathVariable("reportId") int reportId) throws ResourceNotFoundException
    {
        List<ErrorReportMessage> errorReportMessages = errorReportsMessagesRepository.getErrorReportMessagesByReportId(reportId);

        if(errorReportMessages.isEmpty())
            throw new ResourceNotFoundException("This error report doesn't have messages!");
        else
            return errorReportMessages;
    }

    @PutMapping("send")
    public void sendErrorReportMessage(@RequestBody ErrorReportMessage errorReportMessage) throws SqlQueryErrorException
    {
        try {
            errorReportsMessagesRepository.save(errorReportMessage);
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with saving data to database! Try again later.");
        }
    }
}
