package russier.laurent.fr.cleanarchitecture.gui.presenter;

import android.content.Context;
import android.support.v4.content.Loader;

import russier.laurent.fr.cleanarchitecture.gui.PhotosActivity;

/**
 * Presenter surviving lifecycle
 * https://medium.com/@czyrux/presenter-surviving-orientation-changes-with-loaders-6da6d86ffbbf#.43gscfu7j
 *
 * @param <T>
 */
public class PresenterLoader<T extends PhotoPresenter> extends Loader<T> {

    private final PhotosActivity.PresenterFactory<T> factory;
    private T presenter;

    public PresenterLoader(Context context, PhotosActivity.PresenterFactory<T> factory) {
        super(context);
        this.factory = factory;
    }

    @Override
    protected void onStartLoading() {
        // If we already own an instance, simply deliver it.
        if (presenter != null) {
            deliverResult(presenter);
            return;
        }

        // Otherwise, force a load
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        // Create the Presenter using the PhotoPresenterFactory
        presenter = factory.create();

        // Deliver the result
        deliverResult(presenter);
    }

    @Override
    protected void onReset() {
        presenter.onDestroy();
        presenter = null;
    }
}
