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
@RequestMapping("/infernoapi/vipchannels")
@AllArgsConstructor
public class VipChannelsController
{
    private final VipChannelsAdditionalInfoRepository vipChannelsAdditionalInfoRepository;
    private final VipFreeChannelsRepository vipFreeChannelsRepository;

    // vip_channels_additional_info
    @GetMapping("/inactive")
    public List<VipChannelsAdditionalInfo> getAllInactiveChannels()
    {
        return vipChannelsAdditionalInfoRepository.findAll().stream().filter(vipChannelsAdditionalInfo -> vipChannelsAdditionalInfo.isInactive()).collect(Collectors.toList());
    }

    @PutMapping("/set/inactive/{channelNumber}")
    public void setVipChannelAsInactive(@PathVariable("channelNumber") int channelNumber)
    {
        vipChannelsAdditionalInfoRepository.setChannelAsInactive(channelNumber);
    }

    // vip_free_channels
    @GetMapping("/free")
    public List<VipFreeChannels> getAllFreeVipChannels()
    {
        return vipFreeChannelsRepository.findAll().stream().filter(vipFreeChannels -> vipFreeChannels.isFree()).collect(Collectors.toList());
    }
}
