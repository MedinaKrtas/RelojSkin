package sample;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    int horas;
    int min;
    int seg;

    @FXML
    public Label label1;
    @FXML
    public Label label2;
    @FXML
    public Label label3;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Calendar calendar = new GregorianCalendar();


        this.horas = calendar.get(Calendar.HOUR_OF_DAY);
        this.min = calendar.get(Calendar.MINUTE);
        this.seg = calendar.get(Calendar.SECOND);
        this.label1.setText(this.horas+"");
        this.label2.setText(this.min+"");
        this.label3.setText(this.seg+"");


        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                while(true){

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            label3.setText(seg+"");
                            label2.setText(min+"");
                            label1.setText(horas+"");
                        }
                    });

                    seg += 1;

                    if(seg > 59){
                        seg = 0;
                        min += 1;
                    }

                    if(min > 59){
                        min = 0;
                        horas += 1;
                    }

                    if(horas > 23){
                        horas = 0;
                    }

                    Thread.sleep(1000);
                }

            }
        };


        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();


    }
}
