package russier.laurent.fr.cleanarchitecture;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

interface PhotoService {

    @GET("photos")
    Observable<List<JsonPhoto>> getPhotos();
}
