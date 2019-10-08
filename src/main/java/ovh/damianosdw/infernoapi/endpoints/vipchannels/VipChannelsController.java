/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.vipchannels;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ovh.damianosdw.infernoapi.dbmodels.VipChannelsAdditionalInfo;
import ovh.damianosdw.infernoapi.dbmodels.VipFreeChannels;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/infernoapi/vipchannels/")
@AllArgsConstructor
public class VipChannelsController
{
    private final VipChannelsAdditionalInfoRepository vipChannelsAdditionalInfoRepository;
    private final VipFreeChannelsRepository vipFreeChannelsRepository;

    @GetMapping("inactive")
    public List<VipChannelsAdditionalInfo> getAllInactiveChannels()
    {
        return vipChannelsAdditionalInfoRepository.findAll().stream().filter(vipChannelsAdditionalInfo -> vipChannelsAdditionalInfo.isInactive()).collect(Collectors.toList());
    }

    @GetMapping("free")
    public List<VipFreeChannels> getAllFreeVipChannels()
    {
        return vipFreeChannelsRepository.findAll().stream().filter(vipFreeChannels -> vipFreeChannels.isFree()).collect(Collectors.toList());
    }

    @PostMapping("setAsFree")
    public void setChannelAsFree(int channelNumber)
    {
        vipFreeChannelsRepository.setChannelAsFree(channelNumber);
    }

    @PostMapping("setAsOccupied")
    public void setChannelAsOccupied(int channelNumber)
    {
        vipFreeChannelsRepository.setChannelAsOccupied(channelNumber);
    }

    @PostMapping("setAsInactive")
    public void setVipChannelAsInactive(int channelNumber)
    {
        vipChannelsAdditionalInfoRepository.setChannelAsInactive(channelNumber);
    }

    @PostMapping("setAsActive")
    public void setSpChannelAsActive(int channelNumber)
    {
        vipChannelsAdditionalInfoRepository.setChannelAsActive(channelNumber);
    }
}
