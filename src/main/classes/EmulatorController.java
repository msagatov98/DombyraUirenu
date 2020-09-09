package main.classes;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

class EmulatorController {

    private MediaPlayer bottomPlayed;
    private MediaPlayer topPlayed;

    private MediaPlayer[] mediaPlayerBottom = new MediaPlayer[20];
    private MediaPlayer[] mediaPlayerTop = new MediaPlayer[20];

    private Button[] btnBottom, btnTop;
    private KeyCode[] keyTop, keyBottom;


    EmulatorController() {
        Media[] mediaBottom = new Media[20];
        Media[] mediaTop = new Media[20];

        btnTop = new Button[20];
        btnBottom = new Button[20];

        for (int i = 0; i < 20; i++) {
            try {
                mediaBottom[i] = new Media(new File("src\\main\\resources\\res\\voices\\bottom" + i + ".mp3").toURI().toString());
                mediaTop[i] = new Media(new File("src\\main\\resources\\res\\voices\\top" + i + ".mp3").toURI().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            mediaPlayerBottom[i] = new MediaPlayer(mediaBottom[i]);
            mediaPlayerTop[i] = new MediaPlayer(mediaTop[i]);
        }

        keyTop = new KeyCode[] {
                KeyCode.DIGIT1, KeyCode.DIGIT2, KeyCode.DIGIT3, KeyCode.DIGIT4 ,KeyCode.DIGIT5,
                KeyCode.Q, KeyCode.W, KeyCode.E, KeyCode.R, KeyCode.T,
                KeyCode.A, KeyCode.S, KeyCode.D, KeyCode.F, KeyCode.G,
                KeyCode.Z, KeyCode.X, KeyCode.C, KeyCode.V, KeyCode.B
        };

        keyBottom = new KeyCode[] {
                KeyCode.DIGIT6, KeyCode.DIGIT7, KeyCode.DIGIT8, KeyCode.DIGIT9, KeyCode.DIGIT0,
                KeyCode.Y, KeyCode.U, KeyCode.I, KeyCode.O, KeyCode.P,
                KeyCode.H, KeyCode.J, KeyCode.K, KeyCode.L, KeyCode.SEMICOLON,
                KeyCode.N, KeyCode.M, KeyCode.COMMA, KeyCode.PERIOD, KeyCode.SLASH
        };
    }

    void initializeTopButton(Button[] btnTop) {
        try {
            System.arraycopy(btnTop, 0, this.btnTop, 0, btnTop.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void initializeBottomButton(Button[] btnBottom) {
        try {
            System.arraycopy(btnBottom, 0, this.btnBottom, 0, btnBottom.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void keyPlay(KeyEvent event) {
        for (int i = 0; i < 20; i++) {
            if (event.getCode() == keyBottom[i])
                methodInHandle1(mediaPlayerBottom[i]);

            if (event.getCode() == keyTop[i])
                methodInHandle2(mediaPlayerTop[i]);
        }
    }

    void playVoice(ActionEvent e) {
        for (int i = 0 ; i < 20; i++) {
            if (e.getSource() == btnTop[i])
                methodInHandle2(mediaPlayerTop[i]);

            if (e.getSource() == btnBottom[i])
                methodInHandle1(mediaPlayerBottom[i]);
        }
    }

    private void methodInHandle1(MediaPlayer mediaPlayer) {
        if (bottomPlayed != null) {
            bottomPlayed.stop();
        }
        mediaPlayer.play();
        bottomPlayed = mediaPlayer;
    }

    private void methodInHandle2(MediaPlayer mediaPlayer) {
        if (topPlayed != null) {
            topPlayed.stop();
        }
        mediaPlayer.play();
        topPlayed = mediaPlayer;
    }
}
