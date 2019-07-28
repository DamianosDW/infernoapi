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
@Table(name = "positions")
public class Position
{
    @Id
    @GeneratedValue
    @Column(name = "POSITION_ID")
    private int positionId;
    @Column(name = "position_name", nullable = false)
    private String positionName;
}
