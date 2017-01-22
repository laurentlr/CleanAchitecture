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
import russier.laurent.fr.cleanarchitecture.gui.PhotoPresenterImpl;
import russier.laurent.fr.cleanarchitecture.gui.PhotoView;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyList;
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

        presenter.getPhotos();

        verify(view).showProgress();
        verify(view).displayNoResult();
        verify(view).hideProgress();
    }

    @Test
    public void getPhotos_WhenError() throws Exception {
        given(useCase.getPhotos()).willReturn(Observable.<List<Photo>>error(new Exception()));

        presenter.getPhotos();

        verify(view).showProgress();
        verify(view).displayTechnicalError();
        verify(view).hideProgress();
    }

    @Test
    public void getPhotos() throws Exception {
        given(useCase.getPhotos()).willReturn(Observable.just(Collections.singletonList(new Photo())));

        presenter.getPhotos();

        verify(view).showProgress();
        verify(view).displayPhotos(anyList());
        verify(view).hideProgress();
    }
}