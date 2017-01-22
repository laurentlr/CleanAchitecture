package russier.laurent.fr.cleanarchitecture.gui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ViewFlipper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import russier.laurent.fr.cleanarchitecture.R;
import russier.laurent.fr.cleanarchitecture.domain.Photo;
import russier.laurent.fr.cleanarchitecture.gui.presenter.PhotoPresenter;
import russier.laurent.fr.cleanarchitecture.gui.presenter.PhotoPresenterFactory;
import russier.laurent.fr.cleanarchitecture.gui.presenter.PresenterLoader;

public class PhotosActivity extends AppCompatActivity implements
        PhotoView,
        SwipeRefreshLayout.OnRefreshListener,
        LoaderManager.LoaderCallbacks<PhotoPresenter> {

    private static final int LOADER_ID = 100;

    private static final int RESULT_CHILD = 0;
    private static final int NO_RESULT_CHILD = 2;
    private static final int ERROR_CHILD = 1;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.swipeToRefresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.viewFlipper) ViewFlipper viewFlipper;

    private PhotoPresenter presenter;
    private PhotosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onViewAttached(this);
        presenter.getPhotos(false);
    }

    @Override
    protected void onStop() {
        presenter.onViewDetached();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void displayPhotos(List<Photo> photos) {
        viewFlipper.setDisplayedChild(RESULT_CHILD);
        adapter.update(photos);
    }

    @Override
    public void displayNoResult() {
        viewFlipper.setDisplayedChild(NO_RESULT_CHILD);
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void displayTechnicalError() {
        viewFlipper.setDisplayedChild(ERROR_CHILD);
    }

    @Override
    public void onRefresh() {
        presenter.getPhotos(true);
    }

    @Override
    public Loader<PhotoPresenter> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, new PhotoPresenterFactory(this));
    }

    @Override
    public void onLoadFinished(Loader<PhotoPresenter> loader, PhotoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onLoaderReset(Loader<PhotoPresenter> loader) {
        presenter = null;
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PhotosAdapter();
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public interface PresenterFactory<T extends PhotoPresenter> {
        T create();
    }

}
