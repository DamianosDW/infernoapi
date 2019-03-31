package ovh.damianosdw.infernoapi.endpoints.spactivity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sp_activity_module")
public class SpActivityModule
{
    @Column(name = "OPTIONS_ID", nullable = false, unique = true)
    @Id
    @GeneratedValue
    private int optionsId;
    private int numberOfChannels;
    private int channelsInUse;
}
