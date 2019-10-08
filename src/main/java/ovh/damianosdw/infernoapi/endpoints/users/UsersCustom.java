/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.users;

import java.util.List;

public interface UsersCustom
{
    UserInfo getUserInfoByUsername(String username);
    boolean getUserAccountStatus(String username);
    boolean updateUserPassword(int userId, String password);
    List<UserInfo> getInfoAboutAdmins();
    List<UserInfo> getInfoAboutCandidates();
    List<UserInfo> getInfoAboutTesters();
}
