package russier.laurent.fr.cleanarchitecture.gui.dagger;

import dagger.Module;
import dagger.Provides;
import russier.laurent.fr.cleanarchitecture.domain.PhotoRepository;
import russier.laurent.fr.cleanarchitecture.domain.PhotoUseCase;
import russier.laurent.fr.cleanarchitecture.domain.PhotoUseCaseImpl;

@Module
public class PhotoModule {

    @Provides
    @PresenterScope
    PhotoUseCase providePhotoUseCase(PhotoRepository repository) {
        return new PhotoUseCaseImpl(repository);
    }
}
