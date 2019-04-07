/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.spactivity;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.SpActivityCheck;

public interface SpActivityCheckRepository extends JpaRepository<SpActivityCheck, Integer>
{

}
