/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.users;

import lombok.Data;

@Data
public class UserInfo
{
    private int userId;
    private String username;
    private String position;
    private String avatarUrl;
}
