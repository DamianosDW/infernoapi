/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.notificationsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.Notification;

import java.util.List;

public interface NotificationSystemRepository extends JpaRepository<Notification, Integer>, NotificationSystemCustom
{
    int countBySendToPersonAndActiveIsTrue(int userId);
    List<Notification> getNotificationsBySendToPersonAndActiveIsTrue(int userId);
}
