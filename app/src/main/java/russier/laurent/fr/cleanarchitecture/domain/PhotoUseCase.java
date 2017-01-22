package russier.laurent.fr.cleanarchitecture.domain;

import java.util.List;

import io.reactivex.Observable;

public interface PhotoUseCase {
    Observable<List<Photo>> getPhotos();
}
