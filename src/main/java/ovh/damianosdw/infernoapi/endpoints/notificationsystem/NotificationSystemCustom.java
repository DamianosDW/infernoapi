/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.notificationsystem;

public interface NotificationSystemCustom
{
    void updateNotificationStatus(int notificationId, boolean active);
    void setNotificationsAsInactive(int[] notificationIds);
}
