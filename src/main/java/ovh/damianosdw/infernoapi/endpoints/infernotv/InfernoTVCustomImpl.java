/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.endpoints.infernotv;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class InfernoTVCustomImpl implements InfernoTVCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void changeMovieTitle(int movieId, String title)
    {
        entityManager.createNativeQuery("UPDATE inferno_tv SET title = ? WHERE MOVIE_ID = ?")
                .setParameter(1, title)
                .setParameter(2, movieId)
                .executeUpdate();
    }

    @Override
    public void changeMovieUrl(int movieId, String url)
    {
        entityManager.createNativeQuery("UPDATE inferno_tv SET url = ? WHERE MOVIE_ID = ?")
                .setParameter(1, url)
                .setParameter(2, movieId)
                .executeUpdate();
    }
}
