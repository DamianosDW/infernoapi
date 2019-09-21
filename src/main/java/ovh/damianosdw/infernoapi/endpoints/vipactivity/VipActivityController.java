/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.vipactivity;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.dbmodels.CandidatesVipActivityCheck;
import ovh.damianosdw.infernoapi.dbmodels.ChannelActivity;
import ovh.damianosdw.infernoapi.dbmodels.VipActivityCheck;
import ovh.damianosdw.infernoapi.utils.ChannelsActivityUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/infernoapi/vipactivity")
@AllArgsConstructor
public class VipActivityController
{
    private final VipActivityModuleRepository vipActivityModuleRepository;
    private final VipActivityCheckRepository vipActivityCheckRepository;
    private final CandidatesVipActivityCheckRepository candidatesVipActivityCheckRepository;

    // vip_activity_module
    @GetMapping("/numberOfChannels")
    public int getNumberOfChannels()
    {
        return vipActivityModuleRepository.findAll().get(0).getNumberOfChannels();
    }

    @GetMapping("/channelsInUse")
    public int getChannelsInUse()
    {
        return vipActivityModuleRepository.findAll().get(0).getChannelsInUse();
    }

    @PostMapping("/channelsInUse/update")
    public void updateChannelsInUse(int channelsInUse)
    {
        vipActivityModuleRepository.updateChannelsInUse(channelsInUse);
    }
    @PostMapping("/numberOfChannels/update")
    public void updateNumberOfChannels(int numberOfChannels)
    {
        vipActivityModuleRepository.updateNumberOfChannels(numberOfChannels);
    }

    @GetMapping("{userId}/numberOfActivityRecords")
    public int getUserNumberOfActivityRecords(@PathVariable("userId") int userId)
    {
        return vipActivityCheckRepository.countVipActivityChecksByUserId(userId);
    }

    // vip_activity_check (admins)
    @GetMapping("{channelNumber}/monthly/{month}")
    public List<ChannelActivity> getMonthlyVipActivity(@PathVariable("channelNumber") int channelNumber, @PathVariable("month") int month)
    {
        List<ChannelActivity> allVipActivity = new ArrayList<>();
        List<VipActivityCheck> monthlyActivity = vipActivityCheckRepository.findAll()
                .stream()
                .filter(vipActivity -> vipActivity.getCheckDate().getMonthValue() == month && vipActivity.getCheckDate().getYear() == LocalDateTime.now().getYear())
                .collect(Collectors.toList());

        // Get max channel activity
        if(!monthlyActivity.isEmpty())
        {
            String activityDate = monthlyActivity.get(0).getCheckDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int maxActivity = 0;

            for (VipActivityCheck vipActivity : monthlyActivity)
            {
                // Save max activity, update activity date and reset max activity
                if(!activityDate.equals(vipActivity.getCheckDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                {
                    allVipActivity.add(new ChannelActivity(channelNumber, activityDate, maxActivity));
                    activityDate = vipActivity.getCheckDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    maxActivity = 0;
                }

                if(ChannelsActivityUtils.getVipChannelActivity(channelNumber, vipActivity) > maxActivity)
                    maxActivity = ChannelsActivityUtils.getVipChannelActivity(channelNumber, vipActivity);
            }
            return allVipActivity;
        }
        else
            return new ArrayList<>();
    }

    @GetMapping("weekly")
    public List<ChannelActivity> getAllWeeklyVipActivity()
    {
        int channelsInUse = vipActivityModuleRepository.findAll().get(0).getChannelsInUse();

        // Prepare HashMap
        HashMap<Integer, Integer> tempVipActivity = new HashMap<>();
        for(int i = 1; i <= channelsInUse; i++)
            tempVipActivity.put(i, 0);

        LocalDateTime currentDateTime = LocalDateTime.now();

        List<VipActivityCheck> weeklyVipActivity = vipActivityCheckRepository.findAll()
                .stream()
                .filter(spActivity -> spActivity.getCheckDate().isAfter(currentDateTime.minusWeeks(1).minusDays(1)))
                .collect(Collectors.toList());

        // Get vip channels activity
        for(VipActivityCheck vipActivity : weeklyVipActivity)
        {
            for(int i = 1; i <= channelsInUse; i++)
            {
                int maxActivity = Math.max(ChannelsActivityUtils.getVipChannelActivity(i, vipActivity), tempVipActivity.get(i));
                tempVipActivity.put(i, maxActivity);
            }
        }

        List<ChannelActivity> allVipActivity = new ArrayList<>();

        for(Map.Entry<Integer, Integer> activity : tempVipActivity.entrySet())
            allVipActivity.add(new ChannelActivity(activity.getKey(), null, activity.getValue()));

        return allVipActivity;
    }

    @GetMapping("weekly/{channelNumber}")
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

    @PutMapping("send")
    public void sendVipActivity(@RequestBody VipActivityCheck vipActivityCheck)
    {
        vipActivityCheckRepository.save(vipActivityCheck);
    }

    // candidates_vip_activity_check
    @GetMapping("monthly/candidates/{channelNumber}/{month}")
    public Map<String, Integer> getMonthlyCandidatesVipActivity(@PathVariable("channelNumber") int channelNumber, @PathVariable("month") int month)
    {
        HashMap<String, Integer> allVipActivity = new HashMap<>();
        List<CandidatesVipActivityCheck> monthlyActivity = candidatesVipActivityCheckRepository.findAll()
                .stream()
                .filter(vipActivity -> vipActivity.getCheckDate().getMonthValue() == month && vipActivity.getCheckDate().getYear() == LocalDateTime.now().getYear())
                .collect(Collectors.toList());

        // Get max channel activity
        String activityDate = monthlyActivity.get(0).getCheckDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int maxActivity = 0;

        for (CandidatesVipActivityCheck vipActivity : monthlyActivity)
        {
            // Save max activity, update activity date and reset max activity
            if(!activityDate.equals(vipActivity.getCheckDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
            {
                allVipActivity.put(activityDate, maxActivity);
                activityDate = vipActivity.getCheckDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                maxActivity = 0;
            }

            if(ChannelsActivityUtils.getCandidatesVipChannelActivity(channelNumber, vipActivity) > maxActivity)
                maxActivity = ChannelsActivityUtils.getCandidatesVipChannelActivity(channelNumber, vipActivity);
        }
        return allVipActivity;
    }

    @GetMapping("weekly/candidates")
    public Map<Integer, Integer> getAllWeeklyCandidatesVipActivity()
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

    @GetMapping("weekly/candidates/{channelNumber}/")
    public int getWeeklyCandidatesVipActivity(@PathVariable("channelNumber") int channelNumber)
    {
        int channelVipActivity = 0;
        LocalDateTime currentDateTime = LocalDateTime.now();

        List<CandidatesVipActivityCheck> weeklyVipActivity = candidatesVipActivityCheckRepository.findAll()
                .stream()
                .filter(vipActivity -> vipActivity.getCheckDate().isAfter(currentDateTime.minusWeeks(1).minusDays(1)))
                .collect(Collectors.toList());

        // Get vip channel activity
        for (CandidatesVipActivityCheck vipActivity : weeklyVipActivity)
        {
            if(ChannelsActivityUtils.getCandidatesVipChannelActivity(channelNumber, vipActivity) > channelVipActivity)
            {
                channelVipActivity = ChannelsActivityUtils.getCandidatesVipChannelActivity(channelNumber, vipActivity);
            }
        }

        return channelVipActivity;
    }

    @PutMapping("candidates/send")
    public void sendCandidatesVipActivity(@RequestBody CandidatesVipActivityCheck candidatesVipActivityCheck)
    {
        candidatesVipActivityCheckRepository.save(candidatesVipActivityCheck);
    }
}
