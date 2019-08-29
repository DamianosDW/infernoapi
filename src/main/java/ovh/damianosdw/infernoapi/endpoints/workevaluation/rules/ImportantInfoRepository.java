/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.workevaluation.rules;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.damianosdw.infernoapi.dbmodels.ImportantInfo;

public interface ImportantInfoRepository extends JpaRepository<ImportantInfo, Integer>, ImportantInfoCustom
{
    ImportantInfo getImportantInfoByInfoType(String infoType);
}
