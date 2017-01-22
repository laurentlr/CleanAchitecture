package russier.laurent.fr.cleanarchitecture;

import java.util.List;

import io.reactivex.Observable;

interface AlbumRepository {
    Observable<List<Album>> getAlbums();
}
