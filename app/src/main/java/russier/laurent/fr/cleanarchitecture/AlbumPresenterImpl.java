package russier.laurent.fr.cleanarchitecture;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class AlbumPresenterImpl implements AlbumPresenter {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final AlbumUseCase useCase;
    private AlbumView view;

    AlbumPresenterImpl(AlbumView view, AlbumUseCase useCase) {
        this.view = view;
        this.useCase = useCase;
    }

    @Override
    public void getAlbums() {
        view.showProgress();
        useCase.getAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<List<Album>>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onNext(List<Album> albums) {
                        if (albums.isEmpty()) {
                            view.displayNoResult();
                        } else {
                            view.displayAlbums();
                        }
                        view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.displayTechnicalError();
                        view.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        //nothing
                    }
                });
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        view = null;
    }
}
