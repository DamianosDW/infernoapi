/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.teammeetingsvotes;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.dbmodels.TeamMeetingVote;
import ovh.damianosdw.infernoapi.exceptions.ResourceNotFoundException;
import ovh.damianosdw.infernoapi.exceptions.SqlQueryErrorException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/infernoapi/teamMeetingVotes")
@AllArgsConstructor
public class TeamMeetingsVotesController
{
    private final TeamMeetingsVotesRepository teamMeetingsVotesRepository;

    @GetMapping("{userId}")
    public List<TeamMeetingVote> getTeamMeetingVotesByUserId(@PathVariable("userId") int userId) throws ResourceNotFoundException
    {
        List<TeamMeetingVote> teamMeetingVotes = teamMeetingsVotesRepository.getTeamMeetingVotesByUserId(userId);

        if(teamMeetingVotes.isEmpty())
            throw new ResourceNotFoundException("This user didn't vote for team meeting dates!");
        else
            return teamMeetingVotes;
    }

    @GetMapping("userCanVote/{userId}")
    public boolean checkIfUserCanVote(@PathVariable("userId") int userId)
    {
        int numberOfVotes = teamMeetingsVotesRepository.getNumberOfUserVotes(userId, LocalDateTime.now());
        return numberOfVotes < 3;
    }

    @PutMapping("send")
    public void sendTeamMeetingVote(@RequestBody TeamMeetingVote teamMeetingVote) throws SqlQueryErrorException
    {
        try {
            teamMeetingsVotesRepository.save(teamMeetingVote);
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with saving data to database! Try again later. Error: " + e.fillInStackTrace());
        }
    }

    @DeleteMapping("delete/{userId}")
    public void deleteUserTeamMeetingVotes(@PathVariable("userId") int userId) throws SqlQueryErrorException
    {
        try {
            teamMeetingsVotesRepository.removeUserVotes(userId);
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with deleting user votes from database! Try again later.");
        }
    }

}
