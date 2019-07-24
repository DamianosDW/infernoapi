/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.errorreports;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.dbmodels.ErrorReport;
import ovh.damianosdw.infernoapi.exceptions.ResourceNotFoundException;
import ovh.damianosdw.infernoapi.exceptions.SqlQueryErrorException;

import java.util.List;

@RestController
@RequestMapping("/infernoapi/errorReports")
@AllArgsConstructor
public class ErrorReportsController
{
    private final ErrorReportsRepository errorReportsRepository;

    @GetMapping("")
    public List<ErrorReport> getAllErrorReports() throws ResourceNotFoundException
    {
        List<ErrorReport> errorReports = errorReportsRepository.findAll();

        if(errorReports.isEmpty())
            throw new ResourceNotFoundException("Error reports don't exist in database!");
        else
            return errorReports;
    }

    @GetMapping("active")
    public List<ErrorReport> getActiveErrorReports() throws ResourceNotFoundException
    {
        List<ErrorReport> activeErrorReports = errorReportsRepository.getErrorReportsByProblemSolved(false);

        if(activeErrorReports.isEmpty())
            throw new ResourceNotFoundException("Active error reports don't exist in database!");
        else
            return activeErrorReports;
    }

    @GetMapping("{userId}")
    public List<ErrorReport> getErrorReportsByUserId(@PathVariable("userId") int userId) throws ResourceNotFoundException
    {
        List<ErrorReport> errorReportsSentByUser = errorReportsRepository.getErrorReportsByUserId(userId);

        if(errorReportsSentByUser.isEmpty())
            throw new ResourceNotFoundException("This user didn't send any error reports!");
        else
            return errorReportsSentByUser;
    }

    @GetMapping("active/{userId}")
    public List<ErrorReport> getActiveErrorReportsByUserId(@PathVariable("userId") int userId) throws ResourceNotFoundException
    {
        List<ErrorReport> activeErrorReportsSentByUser = errorReportsRepository.getErrorReportsByUserIdAndProblemSolved(userId, false);

        if(activeErrorReportsSentByUser.isEmpty())
            throw new ResourceNotFoundException("All error reports sent by user are inactive!");
        else
            return activeErrorReportsSentByUser;
    }

    @PostMapping("{reportId}/changeStatus/{problemSolved}")
    public void changeErrorReportStatus(@PathVariable("reportId") int reportId, @PathVariable("problemSolved") boolean problemSolved) throws SqlQueryErrorException
    {
        try {
            errorReportsRepository.changeErrorReportStatus(reportId, problemSolved);
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with changing error report status! Try again later.");
        }
    }

    @PutMapping("send")
    public void sendErrorReport(@RequestBody ErrorReport errorReport) throws SqlQueryErrorException
    {
        try {
            errorReportsRepository.save(errorReport);
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with saving data to database! Try again later.");
        }
    }
}
