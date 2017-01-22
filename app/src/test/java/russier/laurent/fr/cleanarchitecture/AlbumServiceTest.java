package russier.laurent.fr.cleanarchitecture;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import rule.ApiRule;

import static org.assertj.core.api.Assertions.assertThat;


public class AlbumServiceTest {

    @Rule public ApiRule apiRule = new ApiRule();
    private AlbumService albumService;

    @Before
    public void setUp() throws Exception {
        albumService = apiRule.getApi().getRetrofit().create(AlbumService.class);
    }

    @Test
    public void getAlbums() throws Exception {
        apiRule.getMockServer().enqueue(new MockResponse().setBody(apiRule.getJsonFromFile("albums.json")).setResponseCode(200));

        TestObserver<List<JsonAlbum>> observer = TestObserver.create();
        albumService.getAlbums().subscribe(observer);

        observer.awaitTerminalEvent();
        observer.assertNoErrors();
        observer.assertSubscribed();
        List<JsonAlbum> albums = observer.values().get(0);
        assertThat(albums.size()).isEqualTo(5000);

        JsonAlbum firstAlbum = albums.get(0);
        assertThat(firstAlbum.getAlbumId()).isEqualTo(1);
        assertThat(firstAlbum.getId()).isEqualTo(1);
        assertThat(firstAlbum.getTitle()).isEqualTo("accusamus beatae ad facilis cum similique qui sunt");
        assertThat(firstAlbum.getUrl()).isEqualTo("http://placehold.it/600/92c952");
        assertThat(firstAlbum.getThumbnailUrl()).isEqualTo("http://placehold.it/150/30ac17");

    }
}