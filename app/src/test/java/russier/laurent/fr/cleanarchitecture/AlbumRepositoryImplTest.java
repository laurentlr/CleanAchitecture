package russier.laurent.fr.cleanarchitecture;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class AlbumRepositoryImplTest {

    @Rule public MockitoRule rule = MockitoJUnit.rule();
    @InjectMocks private AlbumRepositoryImpl repository;
    @Mock private AlbumService albumService;
    @Mock private AlbumMapper mapper;

    @Test
    public void getAlbums() throws Exception {
        given(albumService.getAlbums()).willReturn(Observable.just(Collections.singletonList(new JsonAlbum())));

        repository.getAlbums();

        verify(albumService).getAlbums();
    }
}