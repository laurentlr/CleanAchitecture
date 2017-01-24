package russier.laurent.fr.cleanarchitecture;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import russier.laurent.fr.cleanarchitecture.data.DataBase;
import russier.laurent.fr.cleanarchitecture.data.DataBasePhoto;
import russier.laurent.fr.cleanarchitecture.data.JsonPhoto;
import russier.laurent.fr.cleanarchitecture.data.PhotoMapper;
import russier.laurent.fr.cleanarchitecture.data.PhotoRepositoryImpl;
import russier.laurent.fr.cleanarchitecture.data.PhotoService;
import russier.laurent.fr.cleanarchitecture.domain.Photo;
import russier.laurent.fr.cleanarchitecture.domain.PhotoRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class PhotoRepositoryImplTest {

    private final int id = 1;
    private final int albumId = 123;
    private final String url = "url";
    private final String title = "title";
    private final PhotoMapper mapper = new PhotoMapper();
    @Rule public MockitoRule rule = MockitoJUnit.rule();
    private PhotoRepository repository;
    @Mock private PhotoService photoService;
    @Mock private DataBase dataBase;

    private TestObserver<List<Photo>> testObserver;
    private Observable<List<Photo>> res;
    private List<Photo> photos;

    @Before
    public void setUp() throws Exception {
        repository = new PhotoRepositoryImpl(photoService, mapper, dataBase);
    }

    @Test
    public void getPhotos_FromWS_AndSaveThenToDataBase() throws Exception {
        List<JsonPhoto> jsonPhotos = getJsonPhotos();
        Observable<List<JsonPhoto>> just = Observable.just(jsonPhotos);
        given(photoService.getPhotos()).willReturn(just);
        given(dataBase.getPhotoCount()).willReturn(0L);
        testObserver = TestObserver.create();

        res = repository.getPhotos();
        res.subscribe(testObserver);

        verify(photoService).getPhotos();
        verify(dataBase).save(any(DataBasePhoto.class));
        photos = testObserver.values().get(0);
        Photo firstPhoto = photos.get(0);
        assertThat(photos.size()).isEqualTo(1);
        assertThat(firstPhoto.getAlbumId()).isEqualTo(albumId);
        assertThat(firstPhoto.getId()).isEqualTo(id);
        assertThat(firstPhoto.getThumbnailUrl()).isEqualTo(url);
        assertThat(firstPhoto.getTitle()).isEqualTo(title);
    }

    @Test
    public void shouldGetPhotosFromDataBase() throws Exception {
        testObserver = TestObserver.create();
        given(dataBase.getPhotoCount()).willReturn(1L);
        given(dataBase.getPhotos()).willReturn(Collections.singletonList(getDataBasePhoto()));

        res = repository.getPhotos();
        res.subscribe(testObserver);

        photos = testObserver.values().get(0);
        assertThat(photos.size()).isEqualTo(1);
        Photo firstPhoto = photos.get(0);
        assertThat(photos.size()).isEqualTo(1);
        assertThat(firstPhoto.getAlbumId()).isEqualTo(albumId);
        assertThat(firstPhoto.getId()).isEqualTo(id);
        assertThat(firstPhoto.getThumbnailUrl()).isEqualTo(url);
        assertThat(firstPhoto.getTitle()).isEqualTo(title);
    }

    @NonNull
    private DataBasePhoto getDataBasePhoto() {
        DataBasePhoto dataBasePhoto = new DataBasePhoto();
        dataBasePhoto.setTitle(title);
        dataBasePhoto.setPhotoId(id);
        dataBasePhoto.setAlbumId(albumId);
        dataBasePhoto.setThumbnailUrl(url);
        return dataBasePhoto;
    }

    private List<JsonPhoto> getJsonPhotos() {
        return Collections.singletonList(getJsonPhoto());
    }

    @NonNull
    private JsonPhoto getJsonPhoto() {
        JsonPhoto jsonPhoto = new JsonPhoto();
        jsonPhoto.setAlbumId(albumId);
        jsonPhoto.setId(id);
        jsonPhoto.setThumbnailUrl(url);
        jsonPhoto.setTitle(title);
        return jsonPhoto;
    }
}