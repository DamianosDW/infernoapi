/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.workreports;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.dbmodels.WorkReport;
import ovh.damianosdw.infernoapi.exceptions.InvalidParameterValueException;
import ovh.damianosdw.infernoapi.exceptions.ResourceNotFoundException;
import ovh.damianosdw.infernoapi.exceptions.SqlQueryErrorException;
import ovh.damianosdw.infernoapi.utils.ApiUtils;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/infernoapi/workReports")
@AllArgsConstructor
public class WorkReportsController
{
    private final WorkReportsRepository workReportsRepository;

    @GetMapping("{adminLeaderPositionId}/adminReports")
    public List<WorkReport> getAllAdminReports(@PathVariable("adminLeaderPositionId") int adminLeaderPositionId) throws ResourceNotFoundException
    {
        List<WorkReport> workReports = workReportsRepository.getAdminReportsAndOrderThemBySentDateDesc(adminLeaderPositionId);

        if(workReports.isEmpty())
            throw new ResourceNotFoundException("Work reports (sent by admins) don't exist in database!");
        else
            return workReports;
    }

    @GetMapping("{adminLeaderPositionId}/adminLeaderReports")
    public List<WorkReport> getAllAdminLeaderReports(@PathVariable("adminLeaderPositionId") int adminLeaderPositionId) throws ResourceNotFoundException
    {
        List<WorkReport> workReports = workReportsRepository.getAdminLeaderReportsAndOrderBySentDateDesc(adminLeaderPositionId);

        if(workReports.isEmpty())
            throw new ResourceNotFoundException("Work reports (sent by admin leaders) don't exist in database!");
        else
            return workReports;
    }

    @GetMapping("{userId}/lastUserReportSentDate")
    public LocalDateTime getLastUserReportSentDate(@PathVariable("userId") int userId) throws ResourceNotFoundException
    {
        LocalDateTime lastUserReportSentDate = workReportsRepository.getProperUserWorkReportSentDate(userId);

        if(ApiUtils.checkIfValueIsCorrect(lastUserReportSentDate))
            return lastUserReportSentDate;
        else
            throw new ResourceNotFoundException("This user didn't send work report!");
    }

    @GetMapping("{userId}/userReport")
    public List<WorkReport> getWorkReportsByUserId(@PathVariable("userId") int userId) throws ResourceNotFoundException
    {
        List<WorkReport> workReports = workReportsRepository.getWorkReportsByUserId(userId);

        if(workReports.isEmpty())
            throw new ResourceNotFoundException("This user didn't send work reports!");
        else
            return workReports;
    }

    @PutMapping("send")
    public void sendWorkReport(@RequestBody WorkReport workReport) throws InvalidParameterValueException, SqlQueryErrorException
    {
        if(ApiUtils.checkIfValueIsCorrect(workReport))
        {
            try {
                workReportsRepository.save(workReport);
            } catch(Exception e) {
                throw new SqlQueryErrorException("There was a problem with saving data to database! Try again later.");
            }
        }
        else
            throw new InvalidParameterValueException("Parameters are invalid!");
    }
}