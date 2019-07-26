/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.teammeetingsmodule;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.dbmodels.TeamMeetingsModule;
import ovh.damianosdw.infernoapi.exceptions.InvalidParameterValueException;
import ovh.damianosdw.infernoapi.exceptions.ResourceNotFoundException;
import ovh.damianosdw.infernoapi.exceptions.SqlQueryErrorException;
import ovh.damianosdw.infernoapi.utils.ApiUtils;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/infernoapi/teamMeetingsModule")
@AllArgsConstructor
public class TeamMeetingsModuleController
{
    private final TeamMeetingsModuleRepository teamMeetingsModuleRepository;

    @GetMapping("")
    public List<TeamMeetingsModule> getAllTeamMeetingDates() throws ResourceNotFoundException
    {
        List<TeamMeetingsModule> teamMeetingDates = teamMeetingsModuleRepository.findAll();

        if(teamMeetingDates.isEmpty())
            throw new ResourceNotFoundException("Team meeting dates don't exist in database!");
        else
            return teamMeetingDates;
    }

    @GetMapping("current")
    public List<TeamMeetingsModule> getAllCurrentTeamMeetingDates() throws ResourceNotFoundException
    {
        List<TeamMeetingsModule> teamMeetingsModules = teamMeetingsModuleRepository.getTeamMeetingsModulesByTeamMeetingDateBetweenOrderByTeamMeetingDateAsc(LocalDateTime.now(), LocalDateTime.now().plusYears(1));

        if(teamMeetingsModules.isEmpty())
            throw new ResourceNotFoundException("Current team meeting dates don't exist in database!");
        else
            return teamMeetingsModules;
    }

    @GetMapping("{teamMeetingDate}/teamMeetingId")
    public int getTeamMeetingIdByTeamMeetingDate(@PathVariable("teamMeetingDate") LocalDateTime teamMeetingDate) throws ResourceNotFoundException
    {
        TeamMeetingsModule teamMeetingsModule = teamMeetingsModuleRepository.getTeamMeetingsModuleByTeamMeetingDate(teamMeetingDate);

        if(ApiUtils.checkIfValueIsCorrect(teamMeetingsModule))
        {
            return teamMeetingsModule.getTeamMeetingsModuleId();
        }
        else
            throw new ResourceNotFoundException("This team meeting date doesn't exist in database!");
    }

    @GetMapping("confirmedDate")
    public LocalDateTime getConfirmedTeamMeetingDate() throws ResourceNotFoundException
    {
        TeamMeetingsModule teamMeetingsModule = teamMeetingsModuleRepository.getTeamMeetingsModuleByDateConfirmedIsTrueAndTeamMeetingDateBetween(LocalDateTime.now(), LocalDateTime.now().plusYears(1));

        if(teamMeetingsModule != null)
            return teamMeetingsModule.getTeamMeetingDate();
        else
            throw new ResourceNotFoundException("Confirmed team meeting date doesn't exist in database!");
    }

    @PostMapping("resetTeamMeetingDates")
    public void resetTeamMeetingDates() throws SqlQueryErrorException
    {
        try {
            teamMeetingsModuleRepository.resetTeamMeetingDates();
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with resetting team meeting dates! Try again later.");
        }
    }

    @PostMapping("setAsConfirmed")
    public void setTeamMeetingDateAsConfirmed(@RequestBody TeamMeetingsModule teamMeetingDate) throws InvalidParameterValueException, SqlQueryErrorException
    {
        if(ApiUtils.checkIfValueIsCorrect(teamMeetingDate) && ApiUtils.checkIfValueIsCorrect(teamMeetingDate.getTeamMeetingDate()))
        {
            try {
                teamMeetingsModuleRepository.setTeamMeetingDateAsConfirmed(teamMeetingDate.getTeamMeetingDate());
            } catch(Exception e) {
                throw new SqlQueryErrorException("There was a problem with updating team meeting date! Try again later.");
            }
        }
        else
            throw new InvalidParameterValueException("Parameter is invalid!");
    }

    @PutMapping("send")
    public void sendTeamMeetingDate(@RequestBody TeamMeetingsModule teamMeetingsModule) throws SqlQueryErrorException
    {
        try {
            teamMeetingsModuleRepository.save(teamMeetingsModule);
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with saving team meeting date to database! Try again later.");
        }
    }
}
