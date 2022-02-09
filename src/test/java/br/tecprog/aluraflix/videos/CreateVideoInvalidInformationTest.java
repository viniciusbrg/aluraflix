package br.tecprog.aluraflix.videos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CreateVideoInvalidInformationTest {
    @MockBean
    VideoRepository videoRepository;

    @Autowired
    VideosController videosController;

    final Video expectedVideo = new Video(1L, "Title", "description", "http://test.com");

    void makeAssertions(final Video video) {
        Assertions.assertThrows(RuntimeException.class, () -> videosController.create(video));
        Mockito.verify(videoRepository, Mockito.never()).save(video);
    }

    @Test
    public void createWithBlankTitleTest() {
        final Video video = new Video(null, "    ", "description", "http://test.com");
        makeAssertions(video);
    }

    @Test
    public void createWithEmptyTitleTest() {
        final Video video = new Video(null, "", "description", "http://test.com");
        makeAssertions(video);
    }


    @Test
    public void createWithEmptyDescriptionTest() {
        final Video video = new Video(null, "Title", "    ", "http://test.com");
        makeAssertions(video);
    }


    @Test
    public void createWithBlankDescriptionTest() {
        final Video video = new Video(null, "Title", "", "http://test.com");
        makeAssertions(video);
    }

    @Test
    public void createWithBlankUrlTest() {
        final Video video = new Video(null, "Title", "description", "    ");
        makeAssertions(video);
    }

    @Test
    public void createWithEmptyUrlTest() {
        final Video video = new Video(null, "Title", "description", "");
        makeAssertions(video);
    }

    @Test
    public void createWithNotUrlTest() {
        final Video video = new Video(null, "Title", "description", "really not a url");
        makeAssertions(video);
    }

    @Test
    public void createVideoWithMultipleErrors() {
        final Video video = new Video(null, "", "        ", "http://fake.com");
        makeAssertions(video);
    }
}
