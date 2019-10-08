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
@Table(name = "auto_login_module")
public class AutoLogin
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONFIG_ID")
    private int configId;
    @Column(name = "USER_ID", nullable = false)
    private int userId;
    @Column(name = "status", nullable = false)
    private boolean active;
    @Column(name = "login_token", nullable = false)
    private String loginToken;
}
