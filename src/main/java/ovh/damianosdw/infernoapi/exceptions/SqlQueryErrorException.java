/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.exceptions;

public class SqlQueryErrorException extends Exception
{
    public SqlQueryErrorException(String message)
    {
        super(message);
    }
}
