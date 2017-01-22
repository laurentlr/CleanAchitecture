package russier.laurent.fr.cleanarchitecture.gui;

import dagger.Module;
import dagger.Provides;
import russier.laurent.fr.cleanarchitecture.domain.PhotoRepository;
import russier.laurent.fr.cleanarchitecture.domain.PhotoUseCase;
import russier.laurent.fr.cleanarchitecture.domain.PhotoUseCaseImpl;

@Module
class PhotoModule {

    private final PhotoView view;

    PhotoModule(PhotoView view) {
        this.view = view;
    }

    @Provides
    @PresenterScope
    PhotoUseCase providePhotoUseCase(PhotoRepository repository) {
        return new PhotoUseCaseImpl(repository);
    }

    @Provides
    @PresenterScope
    PhotoPresenter providePhotoPresenter(PhotoUseCase useCase) {
        return new PhotoPresenterImpl(view, useCase);
    }
}
