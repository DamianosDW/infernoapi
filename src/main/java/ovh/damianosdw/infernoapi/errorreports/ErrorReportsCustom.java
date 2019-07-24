/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.errorreports;

public interface ErrorReportsCustom
{
    void changeErrorReportStatus(int reportId, boolean problemSolved);
}
