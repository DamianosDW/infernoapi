/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.users;

public interface UsersCustom
{
    UserInfo getUserInfoByUsername(String username);
    boolean getUserAccountStatus(String username);
    boolean updateUserPassword(int userId, String password);
}
