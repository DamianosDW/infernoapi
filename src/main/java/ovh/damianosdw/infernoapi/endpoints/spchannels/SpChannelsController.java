/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.spchannels;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ovh.damianosdw.infernoapi.dbmodels.SpChannelsAdditionalInfo;
import ovh.damianosdw.infernoapi.dbmodels.SpFreeChannels;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/infernoapi/spchannels/")
@AllArgsConstructor
public class SpChannelsController
{
    private final SpChannelsAdditionalInfoRepository spChannelsAdditionalInfoRepository;
    private final SpFreeChannelsRepository spFreeChannelsRepository;

    @GetMapping("inactive")
    public List<SpChannelsAdditionalInfo> getAllInactiveChannels()
    {
        return spChannelsAdditionalInfoRepository.findAll().stream().filter(spChannelsAdditionalInfo -> spChannelsAdditionalInfo.isInactive()).collect(Collectors.toList());
    }

    @GetMapping("free")
    public List<SpFreeChannels> getAllFreeSpChannels()
    {
        return spFreeChannelsRepository.findAll().stream().filter(spFreeChannels -> spFreeChannels.isFree()).collect(Collectors.toList());
    }

    @PostMapping("setAsFree")
    public void setChannelAsFree(int channelNumber)
    {
        spFreeChannelsRepository.setChannelAsFree(channelNumber);
    }

    @PostMapping("setAsOccupied")
    public void setChannelAsOccupied(int channelNumber)
    {
        spFreeChannelsRepository.setChannelAsOccupied(channelNumber);
    }

    @PostMapping("setAsInactive")
    public void setSpChannelAsInactive(int channelNumber)
    {
        spChannelsAdditionalInfoRepository.setChannelAsInactive(channelNumber);
    }

    @PostMapping("setAsActive")
    public void setSpChannelAsActive(int channelNumber)
    {
        spChannelsAdditionalInfoRepository.setChannelAsActive(channelNumber);
    }
}
