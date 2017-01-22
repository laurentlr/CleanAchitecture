package russier.laurent.fr.cleanarchitecture.data;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import russier.laurent.fr.cleanarchitecture.domain.Photo;
import russier.laurent.fr.cleanarchitecture.domain.PhotoRepository;


public class PhotoRepositoryImpl implements PhotoRepository {

    private final PhotoService photoService;
    private final PhotoMapper photoMapper;

    @Inject
    public PhotoRepositoryImpl(PhotoService photoService, PhotoMapper photoMapper) {
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
