package russier.laurent.fr.cleanarchitecture.gui.presenter;

import russier.laurent.fr.cleanarchitecture.gui.PhotoView;

public interface PhotoPresenter extends Presenter<PhotoView> {
    void getPhotos(boolean reloadPhotos);
}
