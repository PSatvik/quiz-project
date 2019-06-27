package quiz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author sanat
 */
public class HomePageController implements Initializable {

    @FXML
    private HBox homeButton;
    @FXML
    private HBox trendingButton;
    @FXML
    private HBox historyButton;
    @FXML
    private HBox watchLaterButton;
    @FXML
    private HBox uploadButton;
    @FXML
    private HBox settingButton;
    @FXML
    private Label userName;
    GridPane gridPane = new GridPane();
    private int row;
    private File[] preview;
    final File dir = new File("C:\\Users\\Satvik\\Desktop\\preview");
    private AnchorPane rootPane;
    private Socket so;
    private InputStream is;
    private OutputStream os;
    private OutputStreamWriter osr;
    private InputStreamReader isr;
    private BufferedReader br;
    private BufferedWriter bw;
    @FXML
    private Label name;
    @FXML
    private TextField quiz_code;
    @FXML
    private Button startButton;
    @FXML
    private Button exitButton;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        try {
            homeButtonAction();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void setSocket(Socket so ,  BufferedReader br , BufferedWriter bw){
        this.so = so;
        this.br = br;
        this.bw = bw;
        
    }
    

    @FXML
    private void homeButtonAction() throws FileNotFoundException {
        
    }

    @FXML
    private void trendingButtonAction(MouseEvent event) {
       
    }

    @FXML
    private void historyButtonAction(MouseEvent event) {
    }

    @FXML
    private void watchLaterButton(MouseEvent event) {
    }

    @FXML
    private void uploadButtonAction(MouseEvent event) {
        try {
            System.out.println(so.toString());
            //bw.write("abcd");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddQuestion.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            
            AddQuestionController addQuestion = fxmlLoader.getController();
            System.out.println("Sending option.....");
            addQuestion.setSocket(so, br , bw);
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Upload Question");
            //rootPane.setVisible(false);
            stage.show();
            String str = "addquestion\n";
            bw.write(str);
            bw.flush();
            //((Node)(event.getSource())).getScene().getWindow().hide(); //to hide current window i.e login window//
        } catch(IOException e) {
            System.out.println("error in uploading question");
        }
    }

    @FXML
    private void settingButtonAction(MouseEvent event) {
        
    }

    @FXML
    private void startButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QuizPage.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            String str="takequiz\n";
            bw.write(str);
            
            String code = quiz_code.getText();
            bw.write(code+"\n");
            
            bw.flush();
            QuizPageController takeQuiz = fxmlLoader.getController();
            takeQuiz.setSocket(so, br , bw);
            takeQuiz.getQuestion();
          
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("quiz page");
            stage.show();
            //((Node)(event.getSource())).getScene().getWindow().hide(); //to hide current window i.e login window//
        } catch(IOException e) {
            System.out.println("error in starting question");
        }
    }

    @FXML
    private void exitButtonAction(ActionEvent event) {
    }

    
}
