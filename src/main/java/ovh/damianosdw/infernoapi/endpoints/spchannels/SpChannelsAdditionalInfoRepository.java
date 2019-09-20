/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.spchannels;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.SpChannelsAdditionalInfo;

public interface SpChannelsAdditionalInfoRepository extends JpaRepository<SpChannelsAdditionalInfo, Integer>, SpChannelsAdditionalInfoCustom
{

}
