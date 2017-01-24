package russier.laurent.fr.cleanarchitecture.data;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import russier.laurent.fr.cleanarchitecture.domain.Photo;
import russier.laurent.fr.cleanarchitecture.domain.PhotoRepository;


public class PhotoRepositoryImpl implements PhotoRepository {

    private final PhotoService photoService;
    private final PhotoMapper photoMapper;
    private final DataBase dataBase;

    @Inject
    public PhotoRepositoryImpl(PhotoService photoService, PhotoMapper photoMapper, DataBase dataBase) {
        this.photoService = photoService;
        this.photoMapper = photoMapper;
        this.dataBase = dataBase;
    }

    @Override
    public Observable<List<Photo>> getPhotos() {
        //More logic should be added here to handle update, difference from WS, etc.
        if (dataBase.getPhotoCount() == 0L) {
            return photoService.getPhotos().map(new Function<List<JsonPhoto>, List<Photo>>() {
                @Override
                public List<Photo> apply(List<JsonPhoto> jsonPhotos) throws Exception {
                    return photoMapper.transform(jsonPhotos);
                }
            }).doAfterNext(new Consumer<List<Photo>>() {
                @Override
                public void accept(List<Photo> photos) throws Exception {
                    //Save to DataBase
                    for (Photo photo : photos) {
                        DataBasePhoto dataBasePhoto = photoMapper.transformToDataBasePhoto(photo);
                        dataBase.save(dataBasePhoto);
                    }
                }
            }).doAfterNext(new Consumer<List<Photo>>() {
                @Override
                public void accept(List<Photo> photos) throws Exception {
                    for (Photo photo : photos) {
                        dataBase.savePhoto(photo.getId());
                    }
                }
            });
        } else {
            return Observable.fromCallable(new Callable<List<Photo>>() {
                @Override
                public List<Photo> call() throws Exception {
                    List<DataBasePhoto> dataBasePhotos = dataBase.getPhotos();
                    return photoMapper.transformFormDataBase(dataBasePhotos);
                }
            });
        }
    }
}
