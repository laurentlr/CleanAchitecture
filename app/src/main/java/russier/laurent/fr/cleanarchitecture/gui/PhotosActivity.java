package russier.laurent.fr.cleanarchitecture.gui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ViewFlipper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import russier.laurent.fr.cleanarchitecture.R;
import russier.laurent.fr.cleanarchitecture.domain.Photo;

public class PhotosActivity extends AppCompatActivity implements PhotoView {

    private static final int RESULT_CHILD = 0;
    private static final int NO_RESULT_CHILD = 2;
    private static final int ERROR_CHILD = 1;
    @BindView(R.id.recyclerView) protected RecyclerView recyclerView;
    @BindView(R.id.swipeToRefresh) protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.viewFlipper) protected ViewFlipper viewFlipper;
    @Inject PhotoPresenter photoPresenter;
    private PhotosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        DaggerPhotoComponent
                .builder()
                .netComponent(((MyApplication) getApplicationContext()).getNetComponent())
                .photoModule(new PhotoModule(this))
                .build()
                .inject(this);

        photoPresenter.getPhotos();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PhotosAdapter();
        recyclerView.setAdapter(adapter);
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
}
