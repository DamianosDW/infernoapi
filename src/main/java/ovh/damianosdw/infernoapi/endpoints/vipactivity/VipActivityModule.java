package ovh.damianosdw.infernoapi.endpoints.vipactivity;

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
}
