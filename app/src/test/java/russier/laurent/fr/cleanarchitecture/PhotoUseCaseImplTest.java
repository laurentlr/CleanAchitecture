package russier.laurent.fr.cleanarchitecture;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import russier.laurent.fr.cleanarchitecture.domain.PhotoRepository;
import russier.laurent.fr.cleanarchitecture.domain.PhotoUseCaseImpl;

import static org.mockito.Mockito.verify;

public class PhotoUseCaseImplTest {

    @Rule public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks private PhotoUseCaseImpl photoUseCase;
    @Mock private PhotoRepository repository;

    @Test
    public void getPhotos() throws Exception {
        photoUseCase.getPhotos();

        verify(repository).getPhotos();

    }
}