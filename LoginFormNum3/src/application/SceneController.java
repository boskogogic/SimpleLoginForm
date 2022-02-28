package application;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import dao.UserDAO;

public class SceneController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	/* For my auto increment ID in database. */
	private static final AtomicInteger counter = new AtomicInteger();
	
	@FXML
	private Button signUp;
	
	@FXML
	private CheckBox passwordCheckBox;
	
	@FXML 
	private TextField userMail;
	
	@FXML
	private PasswordField userPassword;
	
	@FXML 
	private TextField passwordText;
	
	@FXML
	public void makePasswordVisible(ActionEvent event)
	{
		if(passwordCheckBox.isSelected()) {
			showPasswordText();
			return;
		}
		else {
			hidePasswordText();
		}
	}
	
	@FXML 
	public  void showPasswordText()
	{
		passwordText.setText(userPassword.getText());;
		passwordText.setVisible(true);
		userPassword.setVisible(false);
	}
	
	@FXML
	public void hidePasswordText() {
		userPassword.setText(passwordText.getText());
		userPassword.setVisible(true);
		passwordText.setVisible(false);
	}
	
	
	//ovo bi trebalo da bude klasa gdje cu upisivati podatke u bazu -> RADI PROVJERENO
	public void userSignUp(ActionEvent event) throws IOException{
		if( (userMail.getText().equals("") && userPassword.getText().equals("") ) || (userMail.getText().equals("") && passwordText.getText().equals(""))) 
		{
			System.out.println("DESI SE NESTO"); // provjereno - radi
			// ovde bi trebalo upisivati u bazu i otvarati novi prozor ili ne bi trebalo ?!
			UserDAO in = new UserDAO();
			in.writeInDataBase(counter.getAndIncrement(), userMail.getText(), userPassword.getText());
			switchToScene2(event);
		}
		else
		{
			System.out.println("Ne desi se nista!");
			UserDAO in = new UserDAO();
			in.writeInDataBase(counter.getAndIncrement(), userMail.getText(), userPassword.getText());
			switchToScene2(event);
		}
	}
	
	/* Method which I use to switch the Scenes. */
	public void switchToScene2(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
		Node node1;
		stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
