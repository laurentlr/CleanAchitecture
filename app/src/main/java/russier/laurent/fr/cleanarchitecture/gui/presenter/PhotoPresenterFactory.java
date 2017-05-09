package russier.laurent.fr.cleanarchitecture.gui.presenter;

import android.content.Context;

import javax.inject.Inject;

import russier.laurent.fr.cleanarchitecture.domain.PhotoUseCase;
import russier.laurent.fr.cleanarchitecture.gui.MyApplication;
import russier.laurent.fr.cleanarchitecture.gui.dagger.DaggerPhotoComponent;
import russier.laurent.fr.cleanarchitecture.gui.dagger.PhotoModule;

public class PhotoPresenterFactory implements PresenterFactory<PhotoPresenter> {

    private final Context context;
    @Inject PhotoUseCase photoUseCase;

    public PhotoPresenterFactory(Context context) {
        this.context = context;
    }

    @Override
    public PhotoPresenter create() {
        DaggerPhotoComponent
                .builder()
                .netComponent(((MyApplication) context.getApplicationContext()).getNetComponent())
                .photoModule(new PhotoModule())
                .build()
                .inject(this);
        return new PhotoPresenterImpl(photoUseCase);
    }
}