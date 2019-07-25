/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.notificationsystem;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.dbmodels.Notification;
import ovh.damianosdw.infernoapi.exceptions.InvalidParameterValueException;
import ovh.damianosdw.infernoapi.exceptions.ResourceNotFoundException;
import ovh.damianosdw.infernoapi.exceptions.SqlQueryErrorException;
import ovh.damianosdw.infernoapi.utils.ApiUtils;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/infernoapi/notifications")
@AllArgsConstructor
public class NotificationSystemController
{
    private final NotificationSystemRepository notificationSystemRepository;

    @GetMapping("{notificationId}")
    public Notification getNotificationById(@PathVariable("notificationId") int notificationId) throws ResourceNotFoundException
    {
        Optional<Notification> notification = notificationSystemRepository.findById(notificationId);

        if(notification.isPresent())
            return notification.get();
        else
            throw new ResourceNotFoundException("This notification doesn't exist in database!");
    }

    @GetMapping("{userId}/numberOfNotifications")
    public int getNumberOfNotificationsByUserId(@PathVariable("userId") int userId) throws SqlQueryErrorException
    {
        try {
            return notificationSystemRepository.countBySendToPersonAndActiveIsTrue(userId);
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with getting number of notifications! Try again later.");
        }
    }

    @GetMapping("{userId}/active")
    public List<Notification> getActiveNotificationsByUserId(@PathVariable("userId") int userId) throws ResourceNotFoundException
    {
        List<Notification> notificationsForProperUser = notificationSystemRepository.getNotificationsBySendToPersonAndActiveIsTrue(userId);

        if(notificationsForProperUser.isEmpty())
            throw new ResourceNotFoundException("This user doesn't have notifications!");
        else
            return notificationsForProperUser;
    }

    @PostMapping("changeStatus")
    public void changeNotificationStatus(@RequestBody Notification notification) throws SqlQueryErrorException, InvalidParameterValueException
    {
        if(ApiUtils.checkIfValueIsCorrect(notification) && ApiUtils.checkIfValueIsCorrect(notification.getNotificationId()) && ApiUtils.checkIfValueIsCorrect(notification.isActive()))
        {
            try {
                notificationSystemRepository.updateNotificationStatus(notification.getNotificationId(), notification.isActive());
            } catch(Exception e) {
                throw new SqlQueryErrorException("There was a problem with changing notification status! Try again later.");
            }
        }
        else
            throw new InvalidParameterValueException("Parameters are invalid!");
    }

    @PostMapping("setAsInactive")
    public void setNotificationsAsInactive(int[] notificationIds) throws SqlQueryErrorException
    {
        try {
            notificationSystemRepository.setNotificationsAsInactive(notificationIds);
        } catch(Exception e) {
            throw new SqlQueryErrorException("There was a problem with setting notifications as inactive! Try again later.");
        }
    }

    @PutMapping("send")
    public void sendNotification(@RequestBody Notification notification) throws SqlQueryErrorException, InvalidParameterValueException
    {
        if(ApiUtils.checkIfValueIsCorrect(notification))
        {
            try {
                notificationSystemRepository.save(notification);
            } catch(Exception e) {
                throw new SqlQueryErrorException("There was a problem with saving data to database! Try again later.");
            }
        }
        else
            throw new InvalidParameterValueException("Parameters are invalid!");
    }
}
