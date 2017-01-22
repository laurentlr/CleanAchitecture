package russier.laurent.fr.cleanarchitecture;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import russier.laurent.fr.cleanarchitecture.data.JsonPhoto;
import russier.laurent.fr.cleanarchitecture.data.PhotoMapper;
import russier.laurent.fr.cleanarchitecture.data.PhotoRepositoryImpl;
import russier.laurent.fr.cleanarchitecture.data.PhotoService;
import russier.laurent.fr.cleanarchitecture.domain.Photo;
import russier.laurent.fr.cleanarchitecture.domain.PhotoRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PhotoRepositoryImplTest {

    private final int id = 1;
    private final int albumId = 123;
    private final String url = "url";

    private PhotoRepository repository;
    private PhotoService photoService;
    private PhotoMapper mapper = new PhotoMapper();

    @Before
    public void setUp() throws Exception {
        photoService = mock(PhotoService.class);
        repository = new PhotoRepositoryImpl(photoService, mapper);
    }

    @Test
    public void getPhotos() throws Exception {
        Observable<List<JsonPhoto>> just = Observable.just(getJsonPhotos());
        given(photoService.getPhotos()).willReturn(just);
        TestObserver<List<Photo>> testObserver = TestObserver.create();

        Observable<List<Photo>> res = repository.getPhotos();
        res.subscribe(testObserver);

        verify(photoService).getPhotos();
        List<Photo> photos = testObserver.values().get(0);
        Photo firstPhoto = photos.get(0);
        assertThat(photos.size()).isEqualTo(1);
        assertThat(firstPhoto.getAlbumId()).isEqualTo(albumId);
        assertThat(firstPhoto.getId()).isEqualTo(id);
        assertThat(firstPhoto.getThumbnailUrl()).isEqualTo(url);
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
        return jsonPhoto;
    }
}