package russier.laurent.fr.cleanarchitecture.domain;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PhotoUseCaseImpl implements PhotoUseCase {
    private final PhotoRepository repository;

    @Inject
    public PhotoUseCaseImpl(PhotoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<Photo>> getPhotos() {
        return repository.getPhotos();
    }
}
