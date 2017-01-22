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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class AlbumPresenterImplTest {

    @Rule public RxRule rxRule = new RxRule();
    @Rule public MockitoRule rule = MockitoJUnit.rule();
    @InjectMocks private AlbumPresenterImpl presenter;
    @Mock private AlbumView view;
    @Mock private AlbumUseCase useCase;

    @Test
    public void getAlbums_WhenNoResult() throws Exception {
        given(useCase.getAlbums()).willReturn(Observable.just(Collections.<Album>emptyList()));

        presenter.getAlbums();

        verify(view).showProgress();
        verify(view).displayNoResult();
        verify(view).hideProgress();
    }

    @Test
    public void getAlbums_WhenError() throws Exception {
        given(useCase.getAlbums()).willReturn(Observable.<List<Album>>error(new Exception()));

        presenter.getAlbums();

        verify(view).showProgress();
        verify(view).displayTechnicalError();
        verify(view).hideProgress();
    }

    @Test
    public void getAlbums() throws Exception {
        given(useCase.getAlbums()).willReturn(Observable.just(Collections.singletonList(new Album())));

        presenter.getAlbums();

        verify(view).showProgress();
        verify(view).displayAlbums();
        verify(view).hideProgress();
    }
}