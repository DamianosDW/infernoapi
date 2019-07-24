/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.activityonduty;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.dbmodels.ActivityOnDuty;
import ovh.damianosdw.infernoapi.dbmodels.CandidatesActivity;
import ovh.damianosdw.infernoapi.exceptions.ResourceNotFoundException;
import ovh.damianosdw.infernoapi.exceptions.SqlQueryErrorException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/infernoapi/activityOnDuty")
@AllArgsConstructor
public class ActivityOnDutyController
{
    private final ActivityOnDutyRepository activityOnDutyRepository;
    private final CandidatesActivityRepository candidatesActivityRepository;

    @GetMapping("adminActivities")
    public List<ActivityOnDuty> getAllAdminActivitiesOnDuty() throws ResourceNotFoundException
    {
        List<ActivityOnDuty> adminActivities = activityOnDutyRepository.findAll();

        if(adminActivities.isEmpty())
            throw new ResourceNotFoundException("Admin activities don't exist in database!");
        else
            return adminActivities;
    }

    @GetMapping("candidateActivities")
    public List<CandidatesActivity> getAllCandidateActivitiesOnDuty() throws ResourceNotFoundException
    {
        List<CandidatesActivity> candidateActivities = candidatesActivityRepository.findAll();

        if(candidateActivities.isEmpty())
            throw new ResourceNotFoundException("Admin activities don't exist in database!");
        else
            return candidateActivities;
    }

    @GetMapping("/adminActivityId/{activityOnDutyId}")
    public ActivityOnDuty getAdminActivityOnDutyByActivityId(@PathVariable("activityOnDutyId") int activityOnDutyId) throws ResourceNotFoundException
    {
        Optional<ActivityOnDuty> activityOnDuty = activityOnDutyRepository.findById(activityOnDutyId);

        if(activityOnDuty.isPresent())
            return activityOnDuty.get();
        else
            throw new ResourceNotFoundException("Admin activity info doesn't exist in database!");
    }

    @GetMapping("/candidateActivityId/{activityOnDutyId}")
    public CandidatesActivity getCandidateActivityOnDutyByActivityId(@PathVariable("activityOnDutyId") int activityOnDutyId) throws ResourceNotFoundException
    {
        Optional<CandidatesActivity> activityOnDuty = candidatesActivityRepository.findById(activityOnDutyId);

        if(activityOnDuty.isPresent())
            return activityOnDuty.get();
        else
            throw new ResourceNotFoundException("Candidate activity info doesn't exist in database!");
    }

    @GetMapping("/adminActivity/{userId}")
    public List<ActivityOnDuty> getAdminActivityOnDutyByUserId(@PathVariable("userId") int userId) throws ResourceNotFoundException
    {
        List<ActivityOnDuty> userActivities = activityOnDutyRepository.getActivityOnDutyByUserId(userId);

        if(userActivities.isEmpty())
            throw new ResourceNotFoundException("This user doesn't have activities on duty!");
        else
            return userActivities;
    }

    @GetMapping("/candidateActivity/{userId}")
    public List<CandidatesActivity> getCandidateActivityOnDutyByUserId(@PathVariable("userId") int userId) throws ResourceNotFoundException
    {
        List<CandidatesActivity> userActivities = candidatesActivityRepository.getActivityOnDutyByUserId(userId);

        if(userActivities.isEmpty())
            throw new ResourceNotFoundException("This user doesn't have activities on duty!");
        else
            return userActivities;
    }

    @PutMapping("/adminActivity/send")
    public void sendAdminActivityOnDuty(@RequestBody ActivityOnDuty activityOnDuty) throws SqlQueryErrorException
    {
        sendActivityOnDutyToDatabase(activityOnDuty);
    }

    @PutMapping("/candidateActivity/send")
    public void sendCandidateActivityOnDuty(@RequestBody CandidatesActivity activityOnDuty) throws SqlQueryErrorException
    {
        sendActivityOnDutyToDatabase(activityOnDuty);
    }

    private void sendActivityOnDutyToDatabase(Object activityOnDuty) throws SqlQueryErrorException
    {
        try {
            if(activityOnDuty instanceof ActivityOnDuty)
                activityOnDutyRepository.save((ActivityOnDuty) activityOnDuty);
            else if(activityOnDuty instanceof CandidatesActivity)
                candidatesActivityRepository.save((CandidatesActivity) activityOnDuty);
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with saving data to database! Try again later.");
        }
    }
}
