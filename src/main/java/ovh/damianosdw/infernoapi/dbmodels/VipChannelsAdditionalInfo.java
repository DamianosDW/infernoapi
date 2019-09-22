/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.dbmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vip_channels_additional_info")
public class VipChannelsAdditionalInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int channelInfoId;
    private int channelCreatorId;
    private int channelNumber;
    private String channelOwner;
    @ColumnDefault("0")
    private boolean inactive;
    @Basic
    private LocalDateTime channelInactivityDate;

}
