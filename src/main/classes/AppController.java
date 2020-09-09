package main.classes;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.fxml.FXML;

public class AppController {

    @FXML
    public Button btnOpenPaneLearnig, btnOpenPaneTuner, btnOpenPaneEmulator;
    
    @FXML
    public GridPane paneLearning, paneTuner, paneEmulator;

    @FXML
    public Button btnOpenPaneSongs, btnOpenPaneKuis;

    @FXML
    public Pane paneMusic;

    @FXML
    public GridPane gridPaneSongs, gridPaneKuis;

    @FXML
    public Button btnOpenSong0, btnOpenSong1, btnOpenSong2, btnOpenSong3;
    @FXML
    public Button btnOpenSong4, btnOpenSong5, btnOpenSong6, btnOpenSong7;
    @FXML
    public Button btnOpenSong8, btnOpenSong9;

    @FXML
    public Button btnPlayLezginka, btnPlayQazaqQyz;
    @FXML
    public Button btnPlayAxelF, btnPlayLeila, btnPlayHymn;
    @FXML
    public Button btnPlayJingleBells, btnPlayKozimninQarasy;
    @FXML
    public Button btnPlayQustar, btnPlayQydyryp, btnPlayWhistle;

    @FXML
    public AnchorPane anchorPaneSongs, anchorPaneKuis;

    @FXML
    public VBox paneSong0, paneSong1, paneSong2, paneSong3;
    @FXML
    public VBox paneSong4, paneSong5, paneSong6, paneSong7;
    @FXML
    public VBox paneSong8, paneSong9;

    @FXML
    public Button btnOpenKui0, btnOpenKui1, btnOpenKui2, btnOpenKui3;
    @FXML
    public Button btnOpenKui4, btnOpenKui5, btnOpenKui6, btnOpenKui7;
    @FXML
    public Button btnOpenKui8, btnOpenKui9;

    @FXML
    public Button btnPlaySaryarqa, btnPlayKorogly, btnPlayKenes;
    @FXML
    public Button btnPlayKonilAshar, btnPlayAdai, btnPlayBalbyrauyn;
    @FXML
    public Button btnPlayZhumyrQylysh, btnPlayAta, btnPlayErke, btnPlayAlqisa;

    @FXML
    public VBox paneKui0, paneKui1, paneKui2, paneKui3;
    @FXML
    public VBox paneKui4, paneKui5, paneKui6, paneKui7;
    @FXML
    public VBox paneKui8, paneKui9;

    @FXML
    public Button btnTunerTop, btnTunerBottom;
    @FXML
    public Circle topCircle, bottomCircle;

    @FXML
    public Button btnTop19, btnTop18, btnTop17, btnTop16, btnTop15, btnTop14;
    @FXML
    public Button btnTop13, btnTop12, btnTop11, btnTop10, btnTop9, btnTop8;
    @FXML
    public Button btnTop7, btnTop6, btnTop5, btnTop4, btnTop3, btnTop2, btnTop1, btnTop0;

    @FXML
    public Button btnBottom19, btnBottom18, btnBottom17, btnBottom16, btnBottom15, btnBottom14;
    @FXML
    public Button btnBottom13, btnBottom12, btnBottom11, btnBottom10, btnBottom9, btnBottom8;
    @FXML
    public Button btnBottom7, btnBottom6, btnBottom5, btnBottom4, btnBottom3, btnBottom2, btnBottom1, btnBottom0;

    @FXML
    public Label label1, label2;
    @FXML
    public Slider slider;
    @FXML
    public Button btnPlay;
    @FXML
    public HBox panePlayer;

    private TunerController tunerController = new TunerController();
    private EmulatorController emulatorController = new EmulatorController();
    private LearningPaneController learningPaneController = new LearningPaneController();

    private Paint p1 = Paint.valueOf("#303030");
    private Paint p2 = Paint.valueOf("#242424");


    public void initialize() {

        learningPaneController.setLabel1(label1);
        learningPaneController.setLabel2(label2);
        learningPaneController.setBtnPlay(btnPlay);
        learningPaneController.setSlider(slider);
        learningPaneController.setAnchorPaneKuis(anchorPaneKuis);
        learningPaneController.setAnchorPaneSongs(anchorPaneSongs);
        learningPaneController.setBtnOpenPaneKuis(btnOpenPaneKuis);
        learningPaneController.setBtnOpenPaneSongs(btnOpenPaneSongs);
        learningPaneController.setGridPaneKuis(gridPaneKuis);
        learningPaneController.setGridPaneSongs(gridPaneSongs);
        learningPaneController.setPaneEmulator(paneEmulator);
        learningPaneController.setPaneLearning(paneLearning);
        learningPaneController.setPaneMusic(paneMusic);
        learningPaneController.setPanePlayer(panePlayer);
        learningPaneController.setPaneTuner(paneTuner);

        learningPaneController.setBtnPlaySong(new Button[]{
                btnPlayAxelF, btnPlayLeila, btnPlayHymn, btnPlayJingleBells,
                btnPlayKozimninQarasy, btnPlayLezginka, btnPlayQazaqQyz,
                btnPlayQustar, btnPlayQydyryp, btnPlayWhistle
        });

        learningPaneController.setBtnPlayKui(new Button[] {
                btnPlaySaryarqa, btnPlayKorogly, btnPlayKenes,
                btnPlayKonilAshar, btnPlayAdai, btnPlayBalbyrauyn,
                btnPlayZhumyrQylysh, btnPlayAta, btnPlayErke, btnPlayAlqisa
        });

        learningPaneController.setbtnOpenSong(new Button[] {
                btnOpenSong0, btnOpenSong1, btnOpenSong2, btnOpenSong3,
                btnOpenSong4, btnOpenSong5, btnOpenSong6, btnOpenSong7,
                btnOpenSong8, btnOpenSong9
        });

        learningPaneController.setbtnOpenKui(new Button[] {
                btnOpenKui0, btnOpenKui1, btnOpenKui2, btnOpenKui3,
                btnOpenKui4, btnOpenKui5, btnOpenKui6, btnOpenKui7,
                btnOpenKui8, btnOpenKui9
        });

        learningPaneController.setSongs(new VBox[]{
                paneSong0, paneSong1, paneSong2, paneSong3,
                paneSong4, paneSong5, paneSong6, paneSong7,
                paneSong8, paneSong9
        });

        learningPaneController.setKuis(new VBox[]{
                paneKui0, paneKui1, paneKui2, paneKui3,
                paneKui4, paneKui5, paneKui6, paneKui7,
                paneKui8, paneKui9
        });


        emulatorController.initializeTopButton(new Button[]{
                btnTop0, btnTop1, btnTop2, btnTop3,
                btnTop4, btnTop5, btnTop6, btnTop7,
                btnTop8, btnTop9, btnTop10, btnTop11,
                btnTop12, btnTop13, btnTop14, btnTop15,
                btnTop16, btnTop17, btnTop18, btnTop19
        });

        emulatorController.initializeBottomButton( new Button[]{
                btnBottom0, btnBottom1, btnBottom2, btnBottom3,
                btnBottom4, btnBottom5, btnBottom6, btnBottom7,
                btnBottom8, btnBottom9, btnBottom10, btnBottom11,
                btnBottom12, btnBottom13, btnBottom14, btnBottom15,
                btnBottom16, btnBottom17, btnBottom18, btnBottom19
        });

        tunerController.setTopCircle(topCircle);
        tunerController.setBottomCircle(bottomCircle);
        tunerController.setBtnTunerTop(btnTunerTop);
        tunerController.setBtnTunerBottom(btnTunerBottom);
    }

    @FXML
    public void openPane(ActionEvent e) {
        learningPaneController.stopAllSong();
        if (e.getSource().equals(btnOpenPaneTuner)) {
            setVisibleOnlyTo(paneTuner);
            btnTunerTop.setText("Үстінгі");
            btnTunerBottom.setText("Астынғы");

            changeButtonBackgroundColor(btnOpenPaneTuner, p1);
            changeButtonBackgroundColor(btnOpenPaneEmulator, p2);
            changeButtonBackgroundColor(btnOpenPaneLearnig, p2);

        } else if (e.getSource().equals(btnOpenPaneLearnig)) {
            setVisibleOnlyTo(paneLearning);
            changeButtonBackgroundColor(btnOpenPaneLearnig, p1);
            changeButtonBackgroundColor(btnOpenPaneEmulator, p2);
            changeButtonBackgroundColor(btnOpenPaneTuner, p2);

        } else if (e.getSource().equals(btnOpenPaneEmulator)) {
            setVisibleOnlyTo(paneEmulator);

            btnTunerTop.setText("Үстінгі");
            btnTunerBottom.setText("Астынғы");

            changeButtonBackgroundColor(btnOpenPaneEmulator, p1);
            changeButtonBackgroundColor(btnOpenPaneTuner, p2);
            changeButtonBackgroundColor(btnOpenPaneLearnig, p2);
        }
    }

    @FXML
    public void openPaneMusic(ActionEvent e) {
        learningPaneController.openPaneMusic(e);
    }

    @FXML
    public void openNote(ActionEvent e) {
        learningPaneController.openNote(e);
    }

    @FXML
    public void playSong(ActionEvent e) {
        learningPaneController.playSong(e);
    }

    @FXML
    public void playKui(ActionEvent e) {
        learningPaneController.playKui(e);
    }

    @FXML
    public void tune(ActionEvent e) {
        tunerController.tune(e);
    }

    @FXML
    public void keyPlay(KeyEvent event) {
        emulatorController.keyPlay(event);
    }

    @FXML
    public void playVoice(ActionEvent event) {
        emulatorController.playVoice(event);
    }

    @FXML
    public void goBack() {
        learningPaneController.goBack();
    }

    @FXML
    public void playMusic() {
        learningPaneController.playMusic();
    }

    private void changeButtonBackgroundColor(Button btn, Paint paint) {
        btn.setBackground(new Background(new BackgroundFill(paint, null, null)));
        btn.setStyle("-fx-background-color:" + paint.toString());
    }

    private void setVisibleOnlyTo(Pane pane) {
        paneLearning.setVisible(false);
        paneEmulator.setVisible(false);
        paneTuner.setVisible(false);

        panePlayer.setVisible(false);
        paneMusic.setVisible(false);
        gridPaneSongs.setVisible(false);
        gridPaneKuis.setVisible(false);
        anchorPaneSongs.setVisible(false);
        anchorPaneKuis.setVisible(false);

        pane.setVisible(true);
    }
}