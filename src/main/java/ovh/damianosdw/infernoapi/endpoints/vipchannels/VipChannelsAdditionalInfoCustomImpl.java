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
public class VipChannelsAdditionalInfoCustomImpl implements VipChannelsAdditionalInfoCustom
{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setChannelAsInactive(int channelNumber)
    {
        entityManager.createNativeQuery("UPDATE vip_channels_additional_info SET inactive = true WHERE channel_number = ?")
                .setParameter(1, channelNumber)
                .executeUpdate();
    }

    @Override
    public void setChannelAsActive(int channelNumber)
    {
        entityManager.createNativeQuery("UPDATE vip_channels_additional_info SET inactive = 0, channel_inactivity_date = NULL WHERE channel_number = ?")
                .setParameter(1, channelNumber)
                .executeUpdate();
    }
}
