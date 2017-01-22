package russier.laurent.fr.cleanarchitecture;

import java.util.List;

import io.reactivex.Observable;

class AlbumUseCaseImpl implements AlbumUseCase {
    private final AlbumRepository repository;

    AlbumUseCaseImpl(AlbumRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<Album>> getAlbums() {
        return repository.getAlbums();
    }
}
