/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.utils;

public abstract class ApiUtils
{
    public static boolean checkIfValueIsCorrect(Object object)
    {
        if(object instanceof Integer)
            return (int) object != 0;
        else
            return object != null;
    }
}
