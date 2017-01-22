package russier.laurent.fr.cleanarchitecture;

import java.util.List;

import io.reactivex.Observable;

class AlbumRepositoryImpl implements AlbumRepository {

    private final AlbumService albumService;

    AlbumRepositoryImpl(AlbumService albumService) {
        this.albumService = albumService;
    }

    @Override
    public Observable<List<Album>> getAlbums() {
        return null;
    }
}
