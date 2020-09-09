package main.classes;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;
import java.util.Date;

class LearningPaneController {

    private Button[] btnPlayKui, btnPlaySong;
    private Button[] btnOpenSong;
    private Button[] btnOpenKui;

    private VBox[] kuis, songs;

    private MediaPlayer playedSong;
    private Button playedButton;

    private MediaPlayer[] mediaPlayerSong = new MediaPlayer[10];
    private MediaPlayer[] mediaPlayerKui = new MediaPlayer[10];

    private Image iconPause;
    private Image iconPlay;

    private Duration duration;

    private Slider slider;
    private Button btnOpenPaneSongs, btnOpenPaneKuis;
    private GridPane paneLearning, paneTuner, paneEmulator;
    private GridPane gridPaneSongs, gridPaneKuis;
    private AnchorPane anchorPaneSongs, anchorPaneKuis;
    private Pane paneMusic;

    private HBox panePlayer;
    private Button btnPlay;

    private Label label1, label2;

    void setLabel1(Label label1) {
        this.label1 = label1;
    }

    void setLabel2(Label label2) {
        this.label2 = label2;
    }

    void setBtnPlay(Button btnPlay) {
        this.btnPlay = btnPlay;
    }

    void setPanePlayer(HBox panePlayer) {
        this.panePlayer = panePlayer;
    }

    void setPaneMusic(Pane paneMusic) {
        this.paneMusic = paneMusic;
    }

    void setAnchorPaneKuis(AnchorPane anchorPaneKuis) {
        this.anchorPaneKuis = anchorPaneKuis;
    }

    void setAnchorPaneSongs(AnchorPane anchorPaneSongs) {
        this.anchorPaneSongs = anchorPaneSongs;
    }

    void setGridPaneKuis(GridPane gridPaneKuis) {
        this.gridPaneKuis = gridPaneKuis;
    }

    void setGridPaneSongs(GridPane gridPaneSongs) {
        this.gridPaneSongs = gridPaneSongs;
    }

    void setPaneEmulator(GridPane paneEmulator) {
        this.paneEmulator = paneEmulator;
    }

    void setPaneTuner(GridPane paneTuner) {
        this.paneTuner = paneTuner;
    }

    void setPaneLearning(GridPane paneLearning) {
        this.paneLearning = paneLearning;
    }

    void setSlider(Slider slider) {
        this.slider = slider;
    }

    void setBtnOpenPaneSongs(Button btnOpenPaneSongs) {
        this.btnOpenPaneSongs = btnOpenPaneSongs;
    }

    void setBtnOpenPaneKuis(Button btnOpenPaneKuis) {
        this.btnOpenPaneKuis = btnOpenPaneKuis;
    }

    LearningPaneController() {

        iconPause = new Image(new File("src\\main\\resources\\res\\images\\icons\\iconPause.png").toURI().toString());
        iconPlay = new Image(new File("src\\main\\resources\\res\\images\\icons\\iconPlay.png").toURI().toString());

        for (int i = 0; i < 10; i++) {
            Media[] mediaSong = new Media[10];
            mediaSong[i] = new Media(new File("src\\main\\resources\\res\\songs\\song" + i + ".mp3").toURI().toString());
            Media[] mediaKui = new Media[10];
            mediaKui[i] = new Media(new File("src\\main\\resources\\res\\kuis\\kui" + i + ".mp3").toURI().toString());
            mediaPlayerSong[i] = new MediaPlayer(mediaSong[i]);
            mediaPlayerKui[i] = new MediaPlayer(mediaKui[i]);
        }
    }

    void playMusic() {
        for (int i = 0; i < 10; i++) {
            if (kuis[i].isVisible()) {
                methodInBtnPlay(mediaPlayerKui[i]);
                break;
            }

            if (songs[i].isVisible()) {
                methodInBtnPlay(mediaPlayerSong[i]);
                break;
            }
        }
    }

    void openPaneMusic(ActionEvent e) {

        if (e.getSource() == btnOpenPaneKuis) {
            paneLearning.setVisible(false);
            anchorPaneSongs.setVisible(false);
            gridPaneSongs.setVisible(false);

            gridPaneKuis.setVisible(true);
            paneMusic.setVisible(true);
        } else if (e.getSource() == btnOpenPaneSongs) {
            paneLearning.setVisible(false);
            gridPaneKuis.setVisible(false);
            anchorPaneKuis.setVisible(false);

            gridPaneSongs.setVisible(true);
            paneMusic.setVisible(true);
        }
    }


    void openNote(ActionEvent e) {
        stopAllSong();

        Date date;

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == btnOpenSong[i]) {
                anchorPaneSongs.setVisible(true);
                gridPaneSongs.setVisible(true);
                panePlayer.setVisible(true);
                songs[i].setVisible(true);
                break;
            }

            if (e.getSource() == btnOpenKui[i]) {
                anchorPaneKuis.setVisible(true);
                gridPaneKuis.setVisible(true);
                panePlayer.setVisible(true);
                kuis[i].setVisible(true);
                break;
            }
        }
    }

    void setBtnPlaySong(Button[] btnPlaySong) {
        this.btnPlaySong = btnPlaySong;
    }

    void setBtnPlayKui(Button[] btnPlayKui) {
        this.btnPlayKui = btnPlayKui;
    }

    void setbtnOpenSong(Button[] btnOpenSong) {
        this.btnOpenSong = btnOpenSong;
    }

    void setbtnOpenKui(Button[] btnOpenKui) {
        this.btnOpenKui = btnOpenKui;
    }

    void setKuis(VBox[] kuis) {
        this.kuis = kuis;
    }

    void setSongs(VBox[] songs) {
        this.songs = songs;
    }

    void playSong(ActionEvent e) {
        stopAllSong();
        for (int i = 0; i < 10; i++) {

            if (e.getSource() == btnPlaySong[i]) {
                if (mediaPlayerSong[i].getStatus() == MediaPlayer.Status.PLAYING) {
                    mediaPlayerSong[i].stop();
                    btnPlaySong[i].setGraphic(new ImageView(iconPlay));
                    playedButton = btnPlaySong[i];
                } else {
                    mediaPlayerSong[i].play();
                    playedSong = mediaPlayerSong[i];
                    btnPlaySong[i].setGraphic(new ImageView(iconPause));
                    playedButton = btnPlaySong[i];
                }
                break;
            }
        }
    }

    void goBack() {
        slider.setValue(0);
        stopAllSong();
        for (int i = 0; i < 10; i++) {
            if (kuis[i].isVisible()) {
                methodInGoBack(kuis[i], gridPaneKuis);
                break;
            }

            if (songs[i].isVisible()) {
                methodInGoBack(songs[i], gridPaneSongs);
                break;
            }
        }
    }

    void playKui(ActionEvent e) {

        stopAllSong();

        for (int i = 0; i < 10; i++) {

            if (e.getSource() == btnPlayKui[i]) {
                if (mediaPlayerKui[i].getStatus() == MediaPlayer.Status.PLAYING) {
                    mediaPlayerKui[i].stop();
                    btnPlayKui[i].setGraphic(new ImageView(iconPlay));
                    playedButton = btnPlayKui[i];
                } else {
                    mediaPlayerKui[i].play();
                    playedSong = mediaPlayerKui[i];
                    btnPlayKui[i].setGraphic(new ImageView(iconPause));
                    playedButton = btnPlayKui[i];
                }
                break;
            }
        }
    }

    void stopAllSong() {
        if (playedSong != null)
            playedSong.stop();

        if (playedButton != null)
            playedButton.setGraphic(new ImageView(iconPlay));
    }

    private void slide(MediaPlayer mediaPlayer) {

        duration = mediaPlayer.getMedia().getDuration();

        slider.valueProperty().addListener((invalidated) -> {
            if (slider.isValueChanging()) {
                if (duration != null) {
                    mediaPlayer.seek(duration.multiply(slider.getValue() / 100.0));
                }
                updateValues(mediaPlayer);
            }
        });
        mediaPlayer.currentTimeProperty().addListener((changed) -> updateValues(mediaPlayer));
    }

    private void updateValues(MediaPlayer mediaPlayer) {
        if (slider != null && duration != null) {
            Platform.runLater(() -> {
                Duration currentTime = mediaPlayer.getCurrentTime();
                slider.setDisable(duration.isUnknown());
                if (!slider.isDisabled() && duration.greaterThan(Duration.ZERO) && !slider.isValueChanging()) {
                    slider.setValue(currentTime.divide(duration.toMillis()).toMillis() * 100.0);
                }
            });
        }
    }

    private void methodInBtnPlay(MediaPlayer mediaPlayer) {
        slide(mediaPlayer);
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            btnPlay.setText("|>");
        } else {
            mediaPlayer.play();
            btnPlay.setText("| |");
        }
        playedSong = mediaPlayer;
    }

    private void methodInGoBack(VBox paneNota, Pane paneMusic) {
        btnPlay.setText("|>");
        paneNota.setVisible(false);
        panePlayer.setVisible(false);
        anchorPaneKuis.setVisible(false);
        anchorPaneSongs.setVisible(false);
        paneMusic.setVisible(true);
    }

}