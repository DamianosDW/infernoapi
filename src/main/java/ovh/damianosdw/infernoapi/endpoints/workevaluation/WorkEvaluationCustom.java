/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.workevaluation;

import ovh.damianosdw.infernoapi.dbmodels.WorkEvaluation;

import java.util.List;

public interface WorkEvaluationCustom
{
    List<WorkEvaluation> getUserWorkEvaluationsAndOrderThemBySentDateDesc(int userId);
}
