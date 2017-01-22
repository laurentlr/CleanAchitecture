package russier.laurent.fr.cleanarchitecture;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

interface AlbumService {

    @GET("photos")
    Observable<List<JsonAlbum>> getAlbums();
}
