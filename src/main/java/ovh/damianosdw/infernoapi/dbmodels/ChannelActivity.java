/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.dbmodels;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChannelActivity
{
    private int channelNumber;
    private String activityDate;
    private int maxActivity;
}
