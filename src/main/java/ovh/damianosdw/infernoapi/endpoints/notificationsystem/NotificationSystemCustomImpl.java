/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.notificationsystem;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import ovh.damianosdw.infernoapi.dbmodels.Notification;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class NotificationSystemCustomImpl implements NotificationSystemCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void updateNotificationStatus(int notificationId, boolean active)
    {
        entityManager.createNativeQuery("UPDATE global_notifications SET active = ? WHERE NOTIFICATION_ID = ?")
                .setParameter(1, active)
                .setParameter(2, notificationId)
                .executeUpdate();
    }

    @Override
    public void setNotificationsAsInactive(@RequestBody int[] notificationIds)
    {
        Query nativeQuery = entityManager.createNativeQuery("UPDATE global_notifications SET active = 0 WHERE NOTIFICATION_ID IN (" + prepareWhereInClause(notificationIds.length) + ")");

        for(int i = 0; i < notificationIds.length; i++)
        {
            nativeQuery.setParameter(i + 1, notificationIds[i]);
        }

        nativeQuery.executeUpdate();
    }

    private String prepareWhereInClause(int numberOfNotifications)
    {
        StringBuilder whereInClause = new StringBuilder();

        for(int i = 0; i < numberOfNotifications; i++)
        {
            whereInClause.append("?, ");
        }

        return whereInClause.toString().substring(0, whereInClause.lastIndexOf(","));
    }

    private List<Integer> getNotificationIdsFromList(List<Notification> notifications)
    {
        List<Integer> notificationIds = new ArrayList<>();

        for(Notification notification : notifications)
            notificationIds.add(notification.getNotificationId());

        return notificationIds;
    }
}
