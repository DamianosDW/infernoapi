/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.infernotv;

public interface InfernoTVCustom
{
    void changeMovieTitle(int movieId, String title);
    void changeMovieUrl(int movieId, String url);
}
