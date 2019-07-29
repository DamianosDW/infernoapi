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
        List resultList = entityManager.createNativeQuery("SELECT users.USER_ID, user, position_name, avatar_url, login_token FROM " +
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
        return entityManager.createQuery("SELECT active FROM User WHERE username = ?1", Boolean.class)
                .setParameter(1, username)
                .getSingleResult();
    }
}
