/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.usersactivity;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigInteger;

@Repository
@Transactional
public class UsersActivityCustomImpl implements UsersActivityCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int getNumberOfSuccessfulLogin(int userId)
    {
        BigInteger numberOfSuccessfulLogin = (BigInteger) entityManager.createNativeQuery("SELECT COUNT(*) FROM users_activity WHERE USER_ID = ? AND activity_description='Zalogowano do aplikacji'")
                .setParameter(1, userId)
                .getSingleResult();
        return numberOfSuccessfulLogin.intValue();
    }
}
