/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.vipchannels;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.VipFreeChannels;

public interface VipFreeChannelsRepository extends JpaRepository<VipFreeChannels, Integer>
{

}
