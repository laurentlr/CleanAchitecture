package russier.laurent.fr.cleanarchitecture;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

class AlbumRepositoryImpl implements AlbumRepository {

    private final AlbumService albumService;
    private final AlbumMapper albumMapper;

    AlbumRepositoryImpl(AlbumService albumService, AlbumMapper albumMapper) {
        this.albumService = albumService;
        this.albumMapper = albumMapper;
    }

    @Override
    public Observable<List<Album>> getAlbums() {
        return albumService.getAlbums().map(new Function<List<JsonAlbum>, List<Album>>() {
            @Override
            public List<Album> apply(List<JsonAlbum> jsonAlbums) throws Exception {
                return albumMapper.transform(jsonAlbums);
            }
        });
    }
}
