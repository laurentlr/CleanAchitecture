package russier.laurent.fr.cleanarchitecture;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;

public class AlbumUseCaseImplTest {

    @Rule public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks private AlbumUseCaseImpl albumUseCase;
    @Mock private AlbumRepository repository;

    @Test
    public void getAlbums() throws Exception {
        albumUseCase.getAlbums();

        verify(repository).getAlbums();

    }
}