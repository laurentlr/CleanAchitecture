package russier.laurent.fr.cleanarchitecture;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;

public class AlbumRepositoryImplTest {

    @Rule public MockitoRule rule = MockitoJUnit.rule();
    @InjectMocks private AlbumRepositoryImpl repository;
    @Mock private AlbumService albumService;

    @Test
    public void getAlbums() throws Exception {
        repository.getAlbums();

        verify(albumService).getAlbums();
    }
}