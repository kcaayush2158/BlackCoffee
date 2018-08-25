package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXSlider;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class MainController implements Initializable {

@FXML
private MediaView mediaView;
@FXML
private MediaPlayer mediaplayer;

private String filepath;

@FXML
private JFXSlider slider;
@FXML
private JFXSlider seeslider;
public void openFile(ActionEvent event ) {
	FileChooser filechooser = new FileChooser();
	FileChooser.ExtensionFilter extensionfilter = new FileChooser.ExtensionFilter("Select a File (*.mp4)", "*.mp4");
	filechooser.getExtensionFilters().add(extensionfilter);
	File file = filechooser.showOpenDialog(null);
	filepath = file.toURI().toString();
	if(filepath != null) {
	
		Media media = new Media(filepath);
		mediaplayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaplayer);
		DoubleProperty width =mediaView.fitWidthProperty();
		DoubleProperty height =mediaView.fitHeightProperty();
		width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
		slider.setValue(mediaplayer.getVolume()*100);
		slider.valueProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable observable) {
				mediaplayer.setVolume(slider.getValue()/100);

				
			}
			
		});
		mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {

			@Override
			public void changed(ObservableValue<? extends Duration> observable, Duration oldvalue, Duration newValue) {
			seeslider.setValue(newValue.toSeconds());
				
			}
			
		});
		seeslider.setOnMouseClicked(new EventHandler<MouseEvent>()   {
			@Override
			public void handle(MouseEvent event) {
				mediaplayer.seek(Duration.seconds(seeslider.getValue()));
			}
		});
		mediaplayer.play();
	}

	
}



@FXML
	 public void pauseVideo(ActionEvent event) {
		 mediaplayer.pause();
	 }
@FXML
	 public void stopVideo(ActionEvent event) {
		 mediaplayer.stop();
	 }
@FXML
	 public void fastVideo(ActionEvent event) {
		 mediaplayer.setRate(1.5);
	 }
@FXML
	 public void fasterVideo(ActionEvent event) {
	 mediaplayer.setRate(2);
	 }
@FXML
public void slowVideo(ActionEvent event) {
	 mediaplayer.setRate(.75);
}
@FXML
public void slowerVideo(ActionEvent event) {
	 mediaplayer.setRate(.5);
}
@FXML
public void playVideo(ActionEvent event) {
	 mediaplayer.play();
	 mediaplayer.setRate(1);
}

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
}
}
