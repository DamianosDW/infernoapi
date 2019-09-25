/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.usersactivity;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/infernoapi/usersactivity")
@AllArgsConstructor
public class UsersActivityController
{
    private final UserActivityRepository userActivityRepository;

    @GetMapping("")
    public List<UserActivity> getAll()
    {
        return userActivityRepository.findAll();
    }

    @GetMapping("{userId}/numberOfSuccessfulLogin")
    public int getNumberOfSuccessfulLoginByUserId(@PathVariable("userId") int userId)
    {
        return userActivityRepository.getNumberOfSuccessfulLogin(userId);
    }

    @GetMapping("/{month}/usersActivity")
    public List<UserActivity> getUsersActivityByMonth(@PathVariable("month") int month)
    {
        return userActivityRepository.findAll().stream().filter(userActivity -> userActivity.getActivityDate().getMonthValue() == month).collect(Collectors.toList());
    }

    @GetMapping("/{userId}/{month}/userActivity")
    public List<UserActivity> getUserActivityByMonth(@PathVariable("userId") int userId, @PathVariable("month") int month)
    {
        return userActivityRepository.findAll().stream().filter(userActivity -> userActivity.getUserId() == userId && userActivity.getActivityDate().getMonthValue() == month).collect(Collectors.toList());
    }

    @GetMapping("/{userId}/userActivity")
    public List<UserActivity> getUserActivityByUserId(@PathVariable("userId") int id)
    {
        return userActivityRepository.findAll().stream().filter(userActivity -> userActivity.getUserId() == id).collect(Collectors.toList());
    }

    @PutMapping("/send")
    public void sendUserActivity(@RequestBody UserActivity userActivity)
    {
        userActivityRepository.save(userActivity);
    }
}
