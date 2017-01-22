package russier.laurent.fr.cleanarchitecture.gui.presenter;

import russier.laurent.fr.cleanarchitecture.gui.PhotoView;

public interface PhotoPresenter {
    void getPhotos(boolean reloadPhotos);

    void onDestroy();

    void onViewAttached(PhotoView photoView);

    void onViewDetached();
}
