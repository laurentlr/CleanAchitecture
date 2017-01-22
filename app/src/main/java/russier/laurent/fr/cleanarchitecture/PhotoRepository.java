package russier.laurent.fr.cleanarchitecture;

import java.util.List;

import io.reactivex.Observable;

interface PhotoRepository {
    Observable<List<Photo>> getPhotos();
}
