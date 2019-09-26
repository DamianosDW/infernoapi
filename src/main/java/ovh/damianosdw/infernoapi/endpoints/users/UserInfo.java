/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo
{
    private int userId;
    private String username;
    private String position;
    private String avatarUrl;

    public UserInfo(int userId, String username, String position)
    {
        this.userId = userId;
        this.username = username;
        this.position = position;
        this.avatarUrl = "-";
    }
}
