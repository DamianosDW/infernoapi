/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.spactivity;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class SpActivityModuleCustomImpl implements SpActivityModuleCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void updateChannelsInUse(int channelsInUse)
    {
        entityManager.createNativeQuery("UPDATE sp_activity_module SET channels_in_use = ?")
                .setParameter(1, channelsInUse)
                .executeUpdate();
    }

    @Override
    public void updateNumberOfChannels(int numberOfChannels)
    {
        entityManager.createNativeQuery("UPDATE sp_activity_module SET number_of_channels = ?")
                .setParameter(1, numberOfChannels)
                .executeUpdate();
    }
}
