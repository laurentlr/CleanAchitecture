package russier.laurent.fr.cleanarchitecture;

import java.util.List;

import io.reactivex.Observable;

interface PhotoUseCase {
    Observable<List<Photo>> getPhotos();
}
