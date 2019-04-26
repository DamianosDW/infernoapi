/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.dbmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class VipChannelsAdditionalInfo
{
    @Id
    @GeneratedValue
    private int channelInfoId;
    private int channelCreatorId;
    private int channelNumber;
    private String channelOwner;
    @ColumnDefault("0")
    private boolean inactive;
    @Basic
    private LocalDateTime channelInactivityDate;

}
