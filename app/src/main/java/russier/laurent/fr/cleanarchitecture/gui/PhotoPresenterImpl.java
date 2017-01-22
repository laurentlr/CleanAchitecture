package russier.laurent.fr.cleanarchitecture.gui;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import russier.laurent.fr.cleanarchitecture.domain.Photo;
import russier.laurent.fr.cleanarchitecture.domain.PhotoUseCase;

public class PhotoPresenterImpl implements PhotoPresenter {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final PhotoUseCase useCase;
    private PhotoView view;

    PhotoPresenterImpl(PhotoView view, PhotoUseCase useCase) {
        this.view = view;
        this.useCase = useCase;
    }

    @Override
    public void getPhotos() {
        view.showProgress();
        useCase.getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<List<Photo>>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onNext(List<Photo> photos) {
                        if (photos.isEmpty()) {
                            view.displayNoResult();
                        } else {
                            view.displayPhotos();
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
