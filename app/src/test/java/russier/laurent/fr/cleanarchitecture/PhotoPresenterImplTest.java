package russier.laurent.fr.cleanarchitecture;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import rule.RxRule;
import russier.laurent.fr.cleanarchitecture.domain.Photo;
import russier.laurent.fr.cleanarchitecture.domain.PhotoUseCase;
import russier.laurent.fr.cleanarchitecture.gui.PhotoView;
import russier.laurent.fr.cleanarchitecture.gui.presenter.PhotoPresenterImpl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PhotoPresenterImplTest {

    @Rule public RxRule rxRule = new RxRule();
    @Rule public MockitoRule rule = MockitoJUnit.rule();
    @InjectMocks private PhotoPresenterImpl presenter;
    @Mock private PhotoView view;
    @Mock private PhotoUseCase useCase;

    @Test
    public void getPhotos_WhenNoResult() throws Exception {
        given(useCase.getPhotos()).willReturn(Observable.just(Collections.<Photo>emptyList()));

        presenter.getPhotos(false);

        verify(view).showProgress();
        verify(view).displayNoResult();
        verify(view).hideProgress();
    }

    @Test
    public void getPhotos_WhenError() throws Exception {
        given(useCase.getPhotos()).willReturn(Observable.<List<Photo>>error(new Exception()));

        presenter.getPhotos(false);

        verify(view).showProgress();
        verify(view).displayTechnicalError();
        verify(view).hideProgress();
    }

    @Test
    public void getPhotos_WhenAlreadyLoaded() throws Exception {
        given(useCase.getPhotos()).willReturn(Observable.just(Collections.singletonList(new Photo())));

        //load first time
        presenter.getPhotos(false);
        //reload
        presenter.getPhotos(false);

        verify(useCase).getPhotos();
        verify(view, times(2)).showProgress();
        verify(view, times(2)).displayPhotos(anyList());
        verify(view, times(2)).hideProgress();
    }

    @Test
    public void getPhotos() throws Exception {
        given(useCase.getPhotos()).willReturn(Observable.just(Collections.singletonList(new Photo())));

        presenter.getPhotos(true);

        verify(view).showProgress();
        verify(view).displayPhotos(anyList());
        verify(view).hideProgress();
    }
}