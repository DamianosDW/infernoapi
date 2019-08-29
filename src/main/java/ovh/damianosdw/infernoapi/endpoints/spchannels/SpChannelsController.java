/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.spchannels;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.dbmodels.SpFreeChannels;
import ovh.damianosdw.infernoapi.dbmodels.VipChannelsAdditionalInfo;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/infernoapi/spchannels")
@AllArgsConstructor
public class SpChannelsController
{
    private final SpChannelsAdditionalInfoRepository spChannelsAdditionalInfoRepository;
    private final SpFreeChannelsRepository spFreeChannelsRepository;

    // sp_channels_additional_info
    @GetMapping("/inactive")
    public List<VipChannelsAdditionalInfo> getAllInactiveChannels()
    {
        return spChannelsAdditionalInfoRepository.findAll().stream().filter(spChannelsAdditionalInfo -> spChannelsAdditionalInfo.isInactive()).collect(Collectors.toList());
    }

    @PutMapping("/set/inactive/{channelNumber}")
    public void setSpChannelAsInactive(@PathVariable("channelNumber") int channelNumber)
    {
        spChannelsAdditionalInfoRepository.setChannelAsInactive(channelNumber);
    }

    // sp_free_channels
    @GetMapping("/free")
    public List<SpFreeChannels> getAllFreeSpChannels()
    {
        return spFreeChannelsRepository.findAll().stream().filter(spFreeChannels -> spFreeChannels.isFree()).collect(Collectors.toList());
    }
}
