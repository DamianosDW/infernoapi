/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.infernotv;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.InfernoTVMovie;

public interface InfernoTVRepository extends JpaRepository<InfernoTVMovie, Integer>, InfernoTVCustom
{

}
