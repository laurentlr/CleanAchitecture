package russier.laurent.fr.cleanarchitecture;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

class PhotoRepositoryImpl implements PhotoRepository {

    private final PhotoService photoService;
    private final PhotoMapper photoMapper;

    PhotoRepositoryImpl(PhotoService photoService, PhotoMapper photoMapper) {
        this.photoService = photoService;
        this.photoMapper = photoMapper;
    }

    @Override
    public Observable<List<Photo>> getPhotos() {
        return photoService.getPhotos().map(new Function<List<JsonPhoto>, List<Photo>>() {
            @Override
            public List<Photo> apply(List<JsonPhoto> jsonPhotos) throws Exception {
                return photoMapper.transform(jsonPhotos);
            }
        });
    }
}
