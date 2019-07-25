/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.infernotv;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.dbmodels.InfernoTVMovie;
import ovh.damianosdw.infernoapi.exceptions.InvalidParameterValueException;
import ovh.damianosdw.infernoapi.exceptions.ResourceNotFoundException;
import ovh.damianosdw.infernoapi.exceptions.SqlQueryErrorException;
import ovh.damianosdw.infernoapi.utils.ApiUtils;

import java.util.List;

@RestController
@RequestMapping("/infernoapi/infernoTV")
@AllArgsConstructor
public class InfernoTVController
{
    private final InfernoTVRepository infernoTVRepository;

    @GetMapping("")
    public List<InfernoTVMovie> getAllInfernoTVMovies() throws ResourceNotFoundException
    {
        List<InfernoTVMovie> infernoTVMovies = infernoTVRepository.findAll();

        if(infernoTVMovies.isEmpty())
            throw new ResourceNotFoundException("Inferno TV movies don't exist in database!");
        else
            return infernoTVMovies;
    }

    @PostMapping("changeTitle")
    public void changeInfernoTVMovieTitle(@RequestBody InfernoTVMovie infernoTVMovie) throws SqlQueryErrorException, InvalidParameterValueException
    {
        if(ApiUtils.checkIfValueIsCorrect(infernoTVMovie) && ApiUtils.checkIfValueIsCorrect(infernoTVMovie.getMovieId()) && ApiUtils.checkIfValueIsCorrect(infernoTVMovie.getTitle()))
        {
            try {
                infernoTVRepository.changeMovieTitle(infernoTVMovie.getMovieId(), infernoTVMovie.getTitle());
            } catch(Exception e) {
                throw new SqlQueryErrorException("There was a problem with changing movie title to database! Try again later.");
            }
        }
        else
            throw new InvalidParameterValueException("Parameters are invalid!");
    }

    @PostMapping("changeUrl")
    public void changeInfernoTVMovieUrl(@RequestBody InfernoTVMovie infernoTVMovie) throws SqlQueryErrorException, InvalidParameterValueException
    {
        if(ApiUtils.checkIfValueIsCorrect(infernoTVMovie) && ApiUtils.checkIfValueIsCorrect(infernoTVMovie.getMovieId()) && ApiUtils.checkIfValueIsCorrect(infernoTVMovie.getUrl()))
        {
            try {
                infernoTVRepository.changeMovieUrl(infernoTVMovie.getMovieId(), infernoTVMovie.getUrl());
            } catch(Exception e) {
                throw new SqlQueryErrorException("There was a problem with changing movie title to database! Try again later.");
            }
        }
        else
            throw new InvalidParameterValueException("Parameters are invalid!");
    }

    @PutMapping("send")
    public void sendInfernoTVMovie(@RequestBody InfernoTVMovie infernoTVMovie) throws SqlQueryErrorException
    {
        try {
            infernoTVRepository.save(infernoTVMovie);
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with saving data to database! Try again later.");
        }
    }
}
