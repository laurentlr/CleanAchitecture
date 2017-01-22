package russier.laurent.fr.cleanarchitecture;

import java.util.List;

import io.reactivex.Observable;

interface AlbumUseCase {
    Observable<List<Album>> getAlbums();
}
