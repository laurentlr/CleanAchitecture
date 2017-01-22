package russier.laurent.fr.cleanarchitecture.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import russier.laurent.fr.cleanarchitecture.R;
import russier.laurent.fr.cleanarchitecture.domain.Photo;

public class MainActivity extends AppCompatActivity implements PhotoView {

    @Inject PhotoPresenter photoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerPhotoComponent
                .builder()
                .netComponent(((MyApplication) getApplicationContext()).getNetComponent())
                .photoModule(new PhotoModule(this))
                .build()
                .inject(this);
        photoPresenter.getPhotos();
    }

    @Override
    public void displayPhotos(List<Photo> photos) {

    }

    @Override
    public void displayNoResult() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void displayTechnicalError() {

    }
}
