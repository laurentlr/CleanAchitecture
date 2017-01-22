package russier.laurent.fr.cleanarchitecture.data;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PhotoService {

    @GET("photos")
    Observable<List<JsonPhoto>> getPhotos();
}
