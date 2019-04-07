/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.vipactivity;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.VipActivityModule;

public interface VipActivityModuleRepository extends JpaRepository<VipActivityModule, Integer>
{

}
