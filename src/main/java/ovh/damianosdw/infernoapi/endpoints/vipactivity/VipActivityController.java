/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.vipactivity;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.dbmodels.VipActivityCheck;
import ovh.damianosdw.infernoapi.utils.ChannelsActivityUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/infernoapi")
@AllArgsConstructor
public class VipActivityController
{
    private final VipActivityModuleRepository vipActivityModuleRepository;
    private final VipActivityCheckRepository vipActivityCheckRepository;

    // vip_activity_module
    @GetMapping("/vipactivity/numberofchannels")
    public int getNumberOfChannels()
    {
        return vipActivityModuleRepository.findAll().get(0).getNumberOfChannels();
    }

    @GetMapping("/vipactivity/channelsinuse")
    public int getChannelsInUse()
    {
        return vipActivityModuleRepository.findAll().get(0).getChannelsInUse();
    }

    // vip_activity_check
    @GetMapping("/vipactivity/monthly/{channelNumber}/{month}")
    public Map<String, Integer> getMonthlyVipActivity(@PathVariable("channelNumber") int channelNumber, @PathVariable("month") int month)
    {
        HashMap<String, Integer> allVipActivity = new HashMap<>();
        List<VipActivityCheck> monthlyActivity = vipActivityCheckRepository.findAll()
                .stream()
                .filter(vipActivity -> vipActivity.getCheckDate().getMonthValue() == month && vipActivity.getCheckDate().getYear() == LocalDateTime.now().getYear())
                .collect(Collectors.toList());

        // Get max channel activity
        String activityDate = monthlyActivity.get(0).getCheckDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int maxActivity = 0;

        for (VipActivityCheck vipActivity : monthlyActivity)
        {
            // Save max activity, update activity date and reset max activity
            if(!activityDate.equals(vipActivity.getCheckDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
            {
                allVipActivity.put(activityDate, maxActivity);
                activityDate = vipActivity.getCheckDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                maxActivity = 0;
            }

            if(ChannelsActivityUtils.getVipChannelActivity(channelNumber, vipActivity) > maxActivity)
                maxActivity = ChannelsActivityUtils.getVipChannelActivity(channelNumber, vipActivity);
        }
        return allVipActivity;
    }

    @GetMapping("/vipactivity/weekly")
    public Map<Integer, Integer> getAllWeeklyVipActivity()
    {
        int channelsInUse = vipActivityModuleRepository.findAll().get(0).getChannelsInUse();

        // Prepare HashMap
        HashMap<Integer, Integer> allVipActivity = new HashMap<>();
        for(int i = 1; i <= channelsInUse; i++)
            allVipActivity.put(i, 0);

        LocalDateTime currentDateTime = LocalDateTime.now();

        List<VipActivityCheck> weeklyVipActivity = vipActivityCheckRepository.findAll()
                .stream()
                .filter(vipActivity -> vipActivity.getCheckDate().isAfter(currentDateTime.minusWeeks(1).minusDays(1)))
                .collect(Collectors.toList());

        // Get vip channels activity
        for (VipActivityCheck vipActivity : weeklyVipActivity)
        {
            for(int i = 1; i <= channelsInUse; i++)
            {
                if( ChannelsActivityUtils.getVipChannelActivity(i, vipActivity) > allVipActivity.get(i))
                {
                    allVipActivity.put(i, ChannelsActivityUtils.getVipChannelActivity(i, vipActivity));
                }
            }
        }
        return allVipActivity;
    }
    @GetMapping("/vipactivity/weekly/{channelNumber}")
    public int getWeeklyVipActivity(@PathVariable("channelNumber") int channelNumber)
    {
        int channelVipActivity = 0;
        LocalDateTime currentDateTime = LocalDateTime.now();

        List<VipActivityCheck> weeklyVipActivity = vipActivityCheckRepository.findAll()
                .stream()
                .filter(vipActivity -> vipActivity.getCheckDate().isAfter(currentDateTime.minusWeeks(1).minusDays(1)))
                .collect(Collectors.toList());

        // Get vip channel activity
        for (VipActivityCheck vipActivity : weeklyVipActivity)
        {
            if(ChannelsActivityUtils.getVipChannelActivity(channelNumber, vipActivity) > channelVipActivity)
            {
                channelVipActivity = ChannelsActivityUtils.getVipChannelActivity(channelNumber, vipActivity);
            }
        }

        return channelVipActivity;
    }
}
