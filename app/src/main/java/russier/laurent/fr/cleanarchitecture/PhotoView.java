package russier.laurent.fr.cleanarchitecture;

interface PhotoView {
    void displayPhotos();

    void displayNoResult();

    void showProgress();

    void hideProgress();

    void displayTechnicalError();
}
