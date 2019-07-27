/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.workevaluation;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.dbmodels.WorkEvaluation;
import ovh.damianosdw.infernoapi.exceptions.InvalidParameterValueException;
import ovh.damianosdw.infernoapi.exceptions.ResourceNotFoundException;
import ovh.damianosdw.infernoapi.exceptions.SqlQueryErrorException;
import ovh.damianosdw.infernoapi.utils.ApiUtils;

import java.util.List;

@RestController
@RequestMapping("/infernoapi/workEvaluation/")
@AllArgsConstructor
public class WorkEvaluationController
{
    private final WorkEvaluationRepository workEvaluationRepository;

    @GetMapping("{userId}")
    public List<WorkEvaluation> getWorkEvaluationsByUserId(@PathVariable("userId") int userId) throws ResourceNotFoundException
    {
        List<WorkEvaluation> workEvaluations = workEvaluationRepository.getWorkEvaluationsByUserIdOrderBySentDateDesc(userId);

        if(workEvaluations.isEmpty())
            throw new ResourceNotFoundException("This user didn't get work evaluations!");
        else
            return workEvaluations;
    }

    @GetMapping("{userId}/numberOfWorkEvaluations")
    public int getNumberOfUserWorkEvaluations(@PathVariable("userId") int userId)
    {
        return workEvaluationRepository.countWorkEvaluationsByUserId(userId);
    }

    @PostMapping("change")
    public void changeUserWorkEvaluation(@RequestBody WorkEvaluation workEvaluation) throws InvalidParameterValueException, SqlQueryErrorException
    {
        if(ApiUtils.checkIfValueIsCorrect(workEvaluation) && ApiUtils.checkIfValueIsCorrect(workEvaluation.getWorkEvaluationId()) && ApiUtils.checkIfValueIsCorrect(workEvaluation.getEvaluation()) && ApiUtils.checkIfValueIsCorrect(workEvaluation.getReason()))
        {
            try {
                workEvaluationRepository.updateWorkEvaluation(workEvaluation);
            } catch(Exception e) {
                throw new SqlQueryErrorException("There was a problem with changing work evaluation! Try again later.");
            }
        }
        else
            throw new InvalidParameterValueException("Parameters are invalid (workEvaluationId, evaluation or reason)!");
    }

    @PutMapping("send")
    public void sendWorkEvaluation(@RequestBody WorkEvaluation workEvaluation) throws SqlQueryErrorException
    {
        try {
            workEvaluationRepository.save(workEvaluation);
        } catch(Exception e) {
            throw new SqlQueryErrorException("THere was a problem with saving data to database! Try again later.");
        }
    }
}
