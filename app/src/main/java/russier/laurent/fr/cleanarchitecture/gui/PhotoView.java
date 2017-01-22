package russier.laurent.fr.cleanarchitecture.gui;

public interface PhotoView {
    void displayPhotos();

    void displayNoResult();

    void showProgress();

    void hideProgress();

    void displayTechnicalError();
}
