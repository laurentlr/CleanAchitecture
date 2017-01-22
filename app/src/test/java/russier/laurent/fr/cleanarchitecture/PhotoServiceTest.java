package russier.laurent.fr.cleanarchitecture;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import rule.ApiRule;

import static org.assertj.core.api.Assertions.assertThat;


public class PhotoServiceTest {

    @Rule public ApiRule apiRule = new ApiRule();
    private PhotoService photoService;

    @Before
    public void setUp() throws Exception {
        photoService = apiRule.getApi().getRetrofit().create(PhotoService.class);
    }

    @Test
    public void getPhotos() throws Exception {
        apiRule.getMockServer().enqueue(new MockResponse().setBody(apiRule.getJsonFromFile("photos.json")).setResponseCode(200));

        TestObserver<List<JsonPhoto>> observer = TestObserver.create();
        photoService.getPhotos().subscribe(observer);

        observer.awaitTerminalEvent();
        observer.assertNoErrors();
        observer.assertSubscribed();
        List<JsonPhoto> jsonPhotos = observer.values().get(0);
        assertThat(jsonPhotos.size()).isEqualTo(5000);

        JsonPhoto firstPhoto = jsonPhotos.get(0);
        assertThat(firstPhoto.getAlbumId()).isEqualTo(1);
        assertThat(firstPhoto.getId()).isEqualTo(1);
        assertThat(firstPhoto.getTitle()).isEqualTo("accusamus beatae ad facilis cum similique qui sunt");
        assertThat(firstPhoto.getUrl()).isEqualTo("http://placehold.it/600/92c952");
        assertThat(firstPhoto.getThumbnailUrl()).isEqualTo("http://placehold.it/150/30ac17");

    }
}