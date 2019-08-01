/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class ApiUtils
{
    private static PasswordEncoder clientPasswordEncoder = new BCryptPasswordEncoder(4);
    public static final String DEFAULT_AVATAR_URL = "https://damianosdw.ovh/icp/images/logo-its3.png";
    public static final String CLIENT_ID = "";
    public static final String CLIENT_SECRET = clientPasswordEncoder.encode("");

    public static boolean checkIfValueIsCorrect(Object object)
    {
        if(object instanceof Integer)
            return (int) object != 0;
        else
            return object != null;
    }
}
