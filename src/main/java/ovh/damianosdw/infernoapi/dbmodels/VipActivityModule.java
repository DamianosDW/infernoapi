/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.dbmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vip_activity_module")
public class VipActivityModule
{
    @Column(name = "OPTIONS_ID", nullable = false, unique = true)
    @Id
    @GeneratedValue
    private int optionsId;
    private int numberOfChannels;
    private int channelsInUse;

    public VipActivityModule(int optionValue, boolean updateNumberOfChannels)
    {
        if(updateNumberOfChannels)
            this.numberOfChannels = optionValue;
        else
            this.channelsInUse = optionValue;
    }
}
