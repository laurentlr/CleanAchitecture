package russier.laurent.fr.cleanarchitecture;

interface AlbumView {
    void displayAlbums();

    void displayNoResult();

    void showProgress();

    void hideProgress();

    void displayTechnicalError();
}
