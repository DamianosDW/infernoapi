/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.vipactivity;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.VipActivityCheck;

public interface VipActivityCheckRepository extends JpaRepository<VipActivityCheck, Integer>
{

}
