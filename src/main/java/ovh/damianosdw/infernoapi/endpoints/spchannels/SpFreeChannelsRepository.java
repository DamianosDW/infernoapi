/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.spchannels;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.SpFreeChannels;

public interface SpFreeChannelsRepository extends JpaRepository<SpFreeChannels, Integer>, SpFreeChannelsCustom
{

}
