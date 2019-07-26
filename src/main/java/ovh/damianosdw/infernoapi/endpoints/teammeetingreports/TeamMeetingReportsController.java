/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.teammeetingreports;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.dbmodels.TeamMeetingReport;
import ovh.damianosdw.infernoapi.exceptions.ResourceNotFoundException;
import ovh.damianosdw.infernoapi.exceptions.SqlQueryErrorException;

import java.util.List;

@RestController
@RequestMapping("/infernoapi/teamMeetingReports")
@AllArgsConstructor
public class TeamMeetingReportsController
{
    private final TeamMeetingReportsRepository teamMeetingReportsRepository;

    @GetMapping("")
    public List<TeamMeetingReport> getAllTeamMeetingReports() throws ResourceNotFoundException
    {
        List<TeamMeetingReport> teamMeetingReports = teamMeetingReportsRepository.findAll();

        if(teamMeetingReports.isEmpty())
            throw new ResourceNotFoundException("Team meeting reports don't exist in database");
        else
            return teamMeetingReports;
    }

    @PutMapping("send")
    public void sendTeamMeetingReport(@RequestBody TeamMeetingReport teamMeetingReport) throws SqlQueryErrorException
    {
        try {
            teamMeetingReportsRepository.save(teamMeetingReport);
        } catch(Exception e) {
            throw new SqlQueryErrorException("THere was a problem with saving data to database! Try again later.");
        }
    }
}
