/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.usersactivity;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/infernoapi")
@AllArgsConstructor
public class UsersActivityController
{
    private final UserActivityRepository userActivityRepository;

    @GetMapping("/usersactivity")
    public List<UserActivity> getAll()
    {
        return userActivityRepository.findAll();
    }

    @GetMapping("/usersactivity/{userid}")
    public Optional<UserActivity> getUserActivityByUserId(@PathVariable("userid") int id)
    {
        return userActivityRepository.findAll().stream().filter(userId -> userId.getUserId() == id).findAny();
    }

    @PutMapping("/usersactivity")
    public void sendUserActivity(@RequestBody UserActivity userActivity)
    {
        userActivityRepository.save(userActivity);
    }
}
