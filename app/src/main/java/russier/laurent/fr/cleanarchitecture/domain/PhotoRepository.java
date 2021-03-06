package russier.laurent.fr.cleanarchitecture.domain;

import java.util.List;

import io.reactivex.Observable;

public interface PhotoRepository {
    Observable<List<Photo>> getPhotos();
}
