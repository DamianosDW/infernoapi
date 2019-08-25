/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.spactivity;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.dbmodels.CandidatesSpActivityCheck;
import ovh.damianosdw.infernoapi.dbmodels.SpActivityCheck;
import ovh.damianosdw.infernoapi.utils.ChannelsActivityUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/infernoapi/spactivity")
@AllArgsConstructor
public class SpActivityController
{
    private final SpActivityModuleRepository spActivityModuleRepository;
    private final SpActivityCheckRepository spActivityCheckRepository;
    private final CandidatesSpActivityCheckRepository candidatesSpActivityCheckRepository;

    // sp_activity_module
    @GetMapping("/numberOfChannels")
    public int getNumberOfChannels()
    {
        return spActivityModuleRepository.findAll().get(0).getNumberOfChannels();
    }

    @GetMapping("/channelsInUse")
    public int getChannelsInUse()
    {
        return spActivityModuleRepository.findAll().get(0).getChannelsInUse();
    }

    @PutMapping("/channelsInUse/{channelsInUse}/update")
    public void updateChannelsInUse(@PathVariable("channelsInUse") int channelsInUse)
    {
        spActivityModuleRepository.updateChannelsInUse(channelsInUse);
    }
    @PutMapping("/numberOfChannels/{numberOfChannels}/update")
    public void updateNumberOfChannels(@PathVariable("numberOfChannels") int numberOfChannels)
    {
        spActivityModuleRepository.updateNumberOfChannels(numberOfChannels);
    }

    @GetMapping("{userId}/numberOfActivityRecords")
    public int getUserNumberOfActivityRecords(@PathVariable("userId") int userId)
    {
        return spActivityCheckRepository.countVipActivityChecksByUserId(userId);
    }

    // sp_activity_check (admins)
    @GetMapping("monthly/{channelNumber}/{month}")
    public Map<String, Integer> getMonthlySpActivity(@PathVariable("channelNumber") int channelNumber, @PathVariable("month") int month)
    {
        HashMap<String, Integer> allSpActivity = new HashMap<>();
        List<SpActivityCheck> monthlyActivity = spActivityCheckRepository.findAll()
                .stream()
                .filter(vipActivity -> vipActivity.getCheckDate().getMonthValue() == month && vipActivity.getCheckDate().getYear() == LocalDateTime.now().getYear())
                .collect(Collectors.toList());

        // Get max channel activity
        String activityDate = monthlyActivity.get(0).getCheckDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int maxActivity = 0;

        for (SpActivityCheck spActivity : monthlyActivity)
        {
            // Save max activity, update activity date and reset max activity
            if(!activityDate.equals(spActivity.getCheckDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
            {
                allSpActivity.put(activityDate, maxActivity);
                activityDate = spActivity.getCheckDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                maxActivity = 0;
            }

            if(ChannelsActivityUtils.getPrivateChannelActivity(channelNumber, spActivity) > maxActivity)
                maxActivity = ChannelsActivityUtils.getPrivateChannelActivity(channelNumber, spActivity);
        }
        return allSpActivity;
    }

    @GetMapping("weekly")
    public Map<Integer, Integer> getAllWeeklySpActivity()
    {
        int channelsInUse = spActivityModuleRepository.findAll().get(0).getChannelsInUse();

        // Prepare HashMap
        HashMap<Integer, Integer> allSpActivity = new HashMap<>();
        for(int i = 1; i <= channelsInUse; i++)
            allSpActivity.put(i, 0);

        LocalDateTime currentDateTime = LocalDateTime.now();

        List<SpActivityCheck> weeklySpActivity = spActivityCheckRepository.findAll()
                .stream()
                .filter(spActivity -> spActivity.getCheckDate().isAfter(currentDateTime.minusWeeks(1).minusDays(1)))
                .collect(Collectors.toList());

        // Get sp channels activity
        for (SpActivityCheck spActivity : weeklySpActivity)
        {
            for(int i = 1; i <= channelsInUse; i++)
            {
                if( ChannelsActivityUtils.getPrivateChannelActivity(i, spActivity) > allSpActivity.get(i))
                {
                    allSpActivity.put(i, ChannelsActivityUtils.getPrivateChannelActivity(i, spActivity));
                }
            }
        }
        return allSpActivity;
    }

    @GetMapping("weekly/{channelNumber}")
    public int getWeeklySpActivity(@PathVariable("channelNumber") int channelNumber)
    {
        int channelActivity = 0;
        LocalDateTime currentDateTime = LocalDateTime.now();

        List<SpActivityCheck> weeklySpActivity = spActivityCheckRepository.findAll()
                .stream()
                .filter(activity -> activity.getCheckDate().isAfter(currentDateTime.minusWeeks(1).minusDays(1)))
                .collect(Collectors.toList());

        // Get sp channel activity
        for (SpActivityCheck weeklyActivity : weeklySpActivity)
        {
            if(ChannelsActivityUtils.getPrivateChannelActivity(channelNumber, weeklyActivity) > channelActivity)
            {
                channelActivity = ChannelsActivityUtils.getPrivateChannelActivity(channelNumber, weeklyActivity);
            }
        }

        return channelActivity;
    }

    @PutMapping("send")
    public void sendSpActivity(@RequestBody SpActivityCheck spActivityCheck)
    {
        spActivityCheckRepository.save(spActivityCheck);
    }

    // candidates_sp_activity_check
    @GetMapping("monthly/candidates/{channelNumber}/{month}")
    public Map<String, Integer> getMonthlyCandidatesSpActivity(@PathVariable("channelNumber") int channelNumber, @PathVariable("month") int month)
    {
        HashMap<String, Integer> allSpActivity = new HashMap<>();
        List<CandidatesSpActivityCheck> monthlyActivity = candidatesSpActivityCheckRepository.findAll()
                .stream()
                .filter(activity -> activity.getCheckDate().getMonthValue() == month && activity.getCheckDate().getYear() == LocalDateTime.now().getYear())
                .collect(Collectors.toList());

        // Get max channel activity
        String activityDate = monthlyActivity.get(0).getCheckDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int maxActivity = 0;

        for (CandidatesSpActivityCheck activity : monthlyActivity)
        {
            // Save max activity, update activity date and reset max activity
            if(!activityDate.equals(activity.getCheckDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
            {
                allSpActivity.put(activityDate, maxActivity);
                activityDate = activity.getCheckDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                maxActivity = 0;
            }

            if(ChannelsActivityUtils.getCandidatesPrivateChannelActivity(channelNumber, activity) > maxActivity)
                maxActivity = ChannelsActivityUtils.getCandidatesPrivateChannelActivity(channelNumber, activity);
        }
        return allSpActivity;
    }

    @GetMapping("weekly/candidates")
    public Map<Integer, Integer> getAllWeeklyCandidatesSpActivity()
    {
        int channelsInUse = spActivityModuleRepository.findAll().get(0).getChannelsInUse();

        // Prepare HashMap
        HashMap<Integer, Integer> allSpActivity = new HashMap<>();
        for(int i = 1; i <= channelsInUse; i++)
            allSpActivity.put(i, 0);

        LocalDateTime currentDateTime = LocalDateTime.now();

        List<SpActivityCheck> weeklySpActivity = spActivityCheckRepository.findAll()
                .stream()
                .filter(activity -> activity.getCheckDate().isAfter(currentDateTime.minusWeeks(1).minusDays(1)))
                .collect(Collectors.toList());

        // Get sp channels activity
        for (SpActivityCheck activity : weeklySpActivity)
        {
            for(int i = 1; i <= channelsInUse; i++)
            {
                if( ChannelsActivityUtils.getPrivateChannelActivity(i, activity) > allSpActivity.get(i))
                {
                    allSpActivity.put(i, ChannelsActivityUtils.getPrivateChannelActivity(i, activity));
                }
            }
        }
        return allSpActivity;
    }

    @GetMapping("weekly/candidates/{channelNumber}/")
    public int getWeeklyCandidatesSpActivity(@PathVariable("channelNumber") int channelNumber)
    {
        int channelActivity = 0;
        LocalDateTime currentDateTime = LocalDateTime.now();

        List<CandidatesSpActivityCheck> weeklySpActivity = candidatesSpActivityCheckRepository.findAll()
                .stream()
                .filter(activity -> activity.getCheckDate().isAfter(currentDateTime.minusWeeks(1).minusDays(1)))
                .collect(Collectors.toList());

        // Get sp channel activity
        for (CandidatesSpActivityCheck activity : weeklySpActivity)
        {
            if(ChannelsActivityUtils.getCandidatesPrivateChannelActivity(channelNumber, activity) > channelActivity)
            {
                channelActivity = ChannelsActivityUtils.getCandidatesPrivateChannelActivity(channelNumber, activity);
            }
        }

        return channelActivity;
    }

    @PutMapping("candidates/send")
    public void sendCandidatesSpActivity(@RequestBody CandidatesSpActivityCheck candidatesSpActivityCheck)
    {
        candidatesSpActivityCheckRepository.save(candidatesSpActivityCheck);
    }
}
