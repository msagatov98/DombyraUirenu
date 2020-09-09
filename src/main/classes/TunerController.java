package main.classes;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

class TunerController {

    private Circle topCircle, bottomCircle;
    private AudioFormat format;
    private TargetDataLine targetDataLine;

    private boolean isTopClicked = false;
    private boolean isBottomClicked = false;

    private Paint green = Paint.valueOf("#8AB54B");
    private Paint red = Paint.valueOf("red");

    private Button btnTunerTop, btnTunerBottom;

    TunerController() {

        format = new AudioFormat(44100, 16, 1, true, false);
        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, format);

        try {
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void tune(ActionEvent e) {



        if (e.getSource() == btnTunerTop) {

            Thread threadTop = new Thread(() -> {
                try {
                    tune(topCircle, true);
                }   catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            if (!isTopClicked) {
                methonInTune(btnTunerTop, null);
                threadTop.start();
                isTopClicked = true;
            } else {
                stopRecording();
                threadTop.interrupt();
                btnTunerTop.setText("Үстінгі");
                topCircle.setLayoutX(0.0);
                topCircle.setFill(green);
                isTopClicked = false;
            }
        } else if (e.getSource() == btnTunerBottom) {

            Thread threadBottom = new Thread(() -> {
                try {
                    tune(bottomCircle, false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            if (!isBottomClicked) {
                methonInTune(btnTunerBottom, null);
                threadBottom.start();
                isBottomClicked = true;
            } else {
                stopRecording();
                bottomCircle.setLayoutX(0);
                threadBottom.interrupt();
                btnTunerBottom.setText("Астынғы");
                isBottomClicked = false;
            }
        }}

    void setBtnTunerTop(Button btnTunerTop) {
        this.btnTunerTop = btnTunerTop;
    }

    void setBtnTunerBottom(Button btnTunerBottom) {
        this.btnTunerBottom = btnTunerBottom;
    }

    void setTopCircle(Circle topCircle) {
        this.topCircle = topCircle;
    }

    void setBottomCircle(Circle bottomCircle) {
        this.bottomCircle = bottomCircle;
    }

    private void tune(Circle circle, boolean isTop) throws Exception  {

        double minLayout = 90;
        double maxLayout = 170;

        double topVoice = 146;
        double bottomVoice = 192;

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        KeyValue keyValueX = new KeyValue(topCircle.scaleXProperty(), 1);
        KeyValue keyValueY = new KeyValue(topCircle.scaleYProperty(), 1);

        Duration duration = Duration.millis(100);

        targetDataLine.open(format, 44100);
        targetDataLine.start();

        byte[] buffer = new byte[2 * 1200];
        int[] a = new int[buffer.length / 2];

        int n;
        while ((n = targetDataLine.read(buffer, 0, buffer.length)) > 0) {

            for (int i = 0; i < n; i += 2) {
                // convert two bytes into single value
                int value = (short) ((buffer[i] & 0xFF) | ((buffer[i + 1] & 0xFF) << 8));
                a[i >> 1] = value;
            }

            double prevDiff = 0;
            double prevDx = 0;
            double maxDiff = 0;

            int sampleLen = 0;


            int len = a.length / 2;

            for (int i = 0; i < len; i++) {
                double diff = 0;
                for (int j = 0; j < len; j++) {
                    diff += Math.abs(a[j] - a[i + j]);
                }

                double dx = prevDiff - diff;

                // change of sign in dx
                if (dx < 0 && prevDx > 0) {
                    // only look for troughs that drop to less than 10% of peak
                    if (diff < (0.1 * maxDiff)) {
                        if (sampleLen == 0) {
                            sampleLen = i - 1;
                        }
                    }
                }

                prevDx = dx;
                prevDiff = diff;
                maxDiff = Math.max(diff, maxDiff);
            }


            if (sampleLen > 0) {
                double frequency = (format.getSampleRate() / sampleLen);

                System.out.println(Double.toString(frequency));

                EventHandler<ActionEvent> onFinished;

                if (isTop) {
                    onFinished = (t -> {
                        if (frequency <= minLayout) {
                            changeCirclePositionAndColor(circle, red, minLayout);
                        } else if (frequency >= maxLayout) {
                            changeCirclePositionAndColor(circle, red, maxLayout);
                        } else if (frequency-1 <= topVoice && frequency+1 >= topVoice) {
                            changeCirclePositionAndColor(circle, green, topVoice-55);
                        } else if (frequency > topVoice) {
                            changeCirclePositionAndColor(circle, red, frequency-45);
                        } else if (frequency < topVoice) {
                            changeCirclePositionAndColor(circle, red, frequency-80);
                        }
                    });
                } else {
                    onFinished = (t -> {
                        if (frequency <= minLayout) {
                            changeCirclePositionAndColor(circle, red, minLayout);
                        } else if ((frequency-101) >= maxLayout) {
                            changeCirclePositionAndColor(circle, red, maxLayout);
                        } else if (frequency-1 <= bottomVoice && frequency+1 >= bottomVoice) {
                            changeCirclePositionAndColor(circle, green, bottomVoice-101);
                        } else if (frequency < bottomVoice) {
                            changeCirclePositionAndColor(circle, red, frequency-111);
                        } else if (frequency > bottomVoice) {
                            changeCirclePositionAndColor(circle, red, frequency-91);
                        }
                    });
                }

                KeyFrame keyFrame = new KeyFrame(duration, onFinished , keyValueX, keyValueY);

                timeline.getKeyFrames().add(keyFrame);

                timeline.play();
            }
        }
    }

    private void methonInTune(Button btn, String str) {
        //btn.setBackground(new Background(new BackgroundFill(Paint.valueOf("#E98937"), null, null)));
        //btn.setStyle("-fx-background-radius: 3em;" + "-fx-background-color: #E98937");
        btn.setText(str);
    }

    private void stopRecording() {
        targetDataLine.stop();
        targetDataLine.close();
    }

    private void changeCirclePositionAndColor(Circle circle, Paint color, Double x) {
        circle.setLayoutX(x);
        circle.setFill(color);
    }
}
