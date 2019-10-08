/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.vipchannels;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class VipFreeChannelsCustomImpl implements VipFreeChannelsCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setChannelAsFree(int channelNumber)
    {
        entityManager.createNativeQuery("UPDATE vip_free_channels SET free = 1 WHERE CHANNEL_ID = ?")
                .setParameter(1, channelNumber)
                .executeUpdate();
    }

    @Override
    public void setChannelAsOccupied(int channelNumber)
    {
        entityManager.createNativeQuery("UPDATE vip_free_channels SET free = 0 WHERE CHANNEL_ID = ?")
                .setParameter(1, channelNumber)
                .executeUpdate();
    }
}
