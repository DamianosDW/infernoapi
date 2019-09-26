/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.users;

import org.springframework.stereotype.Repository;
import ovh.damianosdw.infernoapi.utils.ApiUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UsersCustomImpl implements UsersCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserInfo getUserInfoByUsername(String username)
    {
        List resultList = entityManager.createNativeQuery("SELECT users.USER_ID, user, position_name, avatar_url FROM " +
                "users INNER JOIN positions ON users.POSITION_ID = positions.POSITION_ID WHERE user = ?")
                .setParameter(1, username)
                .getResultList();

        if(!resultList.isEmpty())
            return prepareUserInfo((Object[]) resultList.get(0));
        else
            return null;
    }

    private UserInfo prepareUserInfo(Object[] data)
    {
        int userId = (int) data[0];
        String username = (String) data[1];
        String position = (String) data[2];
        String avatarUrl = prepareUserAvatarUrl((String) data[3]);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setUsername(username);
        userInfo.setPosition(position);
        userInfo.setAvatarUrl(avatarUrl);

        return userInfo;
    }

    private String prepareUserAvatarUrl(String avatarUrl)
    {
        if(avatarUrl.matches("-"))
            return ApiUtils.DEFAULT_AVATAR_URL;
        else
            return avatarUrl;
    }

    @Override
    public boolean getUserAccountStatus(String username)
    {
        List result = entityManager.createQuery("SELECT active FROM User WHERE username = ?1", Boolean.class)
                .setParameter(1, username)
                .getResultList();

        if(result.isEmpty())
            return false;
        else
            return (Boolean) result.get(0);
    }

    @Override
    public boolean updateUserPassword(int userId, String password)
    {
        try {
            entityManager.createNativeQuery("UPDATE users SET password = ? WHERE USER_ID = ?")
                    .setParameter(1, password)
                    .setParameter(2, userId)
                    .executeUpdate();
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    public List<UserInfo> getInfoAboutAdmins()
    {
        List infoAboutAdmins = entityManager.createNativeQuery("SELECT USER_ID, user, position_name FROM users INNER JOIN positions ON users.POSITION_ID = positions.POSITION_ID WHERE users.POSITION_ID IN(6, 7, 8, 17) AND active=1")
                .getResultList();

        return prepareInfoAboutProperUsers(infoAboutAdmins);
    }

    @Override
    public List<UserInfo> getInfoAboutCandidates()
    {
        List infoAboutCandidates = entityManager.createNativeQuery("SELECT USER_ID, user, position_name FROM users INNER JOIN positions ON users.POSITION_ID = positions.POSITION_ID WHERE users.POSITION_ID IN(9, 10, 11, 16) AND active=1", UserInfo.class)
                .getResultList();

        return prepareInfoAboutProperUsers(infoAboutCandidates);
    }

    @Override
    public List<UserInfo> getInfoAboutTesters()
    {
        List infoAboutTesters = entityManager.createNativeQuery("SELECT USER_ID, user, position_name FROM users INNER JOIN positions ON users.POSITION_ID = positions.POSITION_ID WHERE users.POSITION_ID=15 AND active=1")
                .getResultList();

        return prepareInfoAboutProperUsers(infoAboutTesters);
    }

    private List<UserInfo> prepareInfoAboutProperUsers(List resultList)
    {
        List<UserInfo> infoAboutUsers = new ArrayList<>();

        for(Object result : resultList)
        {
            Object[] userData = (Object[]) result;
            int userId = (int) userData[0];
            String username = (String) userData[1];
            String position = (String) userData[2];

            infoAboutUsers.add(new UserInfo(userId, username, position));
        }

        return infoAboutUsers;
    }
}
