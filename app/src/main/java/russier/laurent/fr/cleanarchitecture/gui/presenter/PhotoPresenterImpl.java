package russier.laurent.fr.cleanarchitecture.gui.presenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import russier.laurent.fr.cleanarchitecture.domain.Photo;
import russier.laurent.fr.cleanarchitecture.domain.PhotoUseCase;
import russier.laurent.fr.cleanarchitecture.gui.PhotoView;

public class PhotoPresenterImpl implements PhotoPresenter {
    private final PhotoUseCase useCase;
    private CompositeDisposable compositeDisposable;
    private PhotoView view;
    private List<Photo> photos = null;

    @Inject
    public PhotoPresenterImpl(PhotoView view, PhotoUseCase useCase) {
        this.view = view;
        this.useCase = useCase;
        compositeDisposable = new CompositeDisposable();
    }

    PhotoPresenterImpl(PhotoUseCase photoUseCase) {
        this.useCase = photoUseCase;
    }

    @Override
    public void getPhotos(boolean reloadPhotos) {
        if (reloadPhotos) {
            photos = null;
        }

        view.showProgress();
        if (photos == null) {
            compositeDisposable = new CompositeDisposable();
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
                            PhotoPresenterImpl.this.photos = photos;
                            if (photos.isEmpty()) {
                                view.displayNoResult();
                            } else {
                                view.displayPhotos(photos);
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
        } else {
            view.displayPhotos(photos);
            view.hideProgress();
        }
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        view = null;
    }

    @Override
    public void onViewAttached(PhotoView photoView) {
        view = photoView;
    }

    @Override
    public void onViewDetached() {
        view = null;
    }
}
