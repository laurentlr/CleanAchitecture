package russier.laurent.fr.cleanarchitecture;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class AlbumPresenterImpl implements AlbumPresenter {
    private final AlbumView view;
    private final AlbumUseCase useCase;

    AlbumPresenterImpl(AlbumView view, AlbumUseCase useCase) {
        this.view = view;
        this.useCase = useCase;
    }

    @Override
    public void getAlbums() {
        useCase.getAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Album>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //nothing
                    }

                    @Override
                    public void onNext(List<Album> albums) {
                        if (albums.isEmpty()) {
                            view.displayNoResult();
                        } else {
                            view.displayAlbums();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //nothing
                    }

                    @Override
                    public void onComplete() {
                        //nothing
                    }
                });
    }
}
