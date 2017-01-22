package russier.laurent.fr.cleanarchitecture.gui;

import java.util.List;

import russier.laurent.fr.cleanarchitecture.domain.Photo;

public interface PhotoView {
    void displayPhotos(List<Photo> photos);

    void displayNoResult();

    void showProgress();

    void hideProgress();

    void displayTechnicalError();
}
