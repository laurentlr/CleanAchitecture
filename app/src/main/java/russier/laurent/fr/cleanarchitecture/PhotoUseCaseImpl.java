package russier.laurent.fr.cleanarchitecture;

import java.util.List;

import io.reactivex.Observable;

class PhotoUseCaseImpl implements PhotoUseCase {
    private final PhotoRepository repository;

    PhotoUseCaseImpl(PhotoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<Photo>> getPhotos() {
        return repository.getPhotos();
    }
}
