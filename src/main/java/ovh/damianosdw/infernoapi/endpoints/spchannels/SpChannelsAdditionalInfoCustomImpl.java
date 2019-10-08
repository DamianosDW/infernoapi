/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.spchannels;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class SpChannelsAdditionalInfoCustomImpl implements SpChannelsAdditionalInfoCustom
{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setChannelAsInactive(int channelNumber)
    {
        entityManager.createNativeQuery("UPDATE sp_channels_additional_info SET inactive = true WHERE channel_number = ?")
                .setParameter(1, channelNumber)
                .executeUpdate();
    }

    @Override
    public void setChannelAsActive(int channelNumber)
    {
        entityManager.createNativeQuery("UPDATE sp_channels_additional_info SET inactive = 0, channel_inactivity_date = NULL WHERE channel_number = ?")
                .setParameter(1, channelNumber)
                .executeUpdate();
    }
}
