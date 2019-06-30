/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Satvik
 */
public class QuizPageController implements Initializable {

    @FXML
    private TextArea question;
    @FXML
    private RadioButton option1;
    @FXML
    private RadioButton option2;
    @FXML
    private RadioButton option4;
    @FXML
    private RadioButton option3;
    @FXML
    private Button prevButton;
    @FXML
    private Button submitButton;
    @FXML
    private Button nextButton;
    private AnchorPane rootPane;
    private Socket so;
    private InputStream is;
    private OutputStream os;
    private OutputStreamWriter osr;
    private InputStreamReader isr;
    private BufferedReader br;
    private BufferedWriter bw;
    int counter=0;
    private String ques[];
    @FXML
    private Label currentQuestionNo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setSocket(Socket so ,  BufferedReader br , BufferedWriter bw){
        this.so = so;
        this.br = br;
        this.bw = bw;
        
    }
    
    private void displayquestion()
    {
        StringTokenizer st1 = new StringTokenizer(ques[counter],"#");
        counter++;
        question.setText(st1.nextToken());
        option1.setText(st1.nextToken());
        option2.setText(st1.nextToken());
        option3.setText(st1.nextToken());
        option4.setText(st1.nextToken());
    }
    public void getQuestion()
    {
        try{
             String inp = br.readLine();
             int size = Integer.parseInt(inp);
             System.out.println(size);
             ques = new String[size];
             
             for(int i=0; i<size; i++)
             {
                 String question = br.readLine();
                 ques[i] = question;
                 System.out.println(question);
             }
             displayquestion();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

       
    }
    @FXML
    private void prevButtonAction(ActionEvent event) {
    }

    @FXML
    private void submitButtonAction(ActionEvent event) {
    }

    @FXML
    private void nextButtonAction(ActionEvent event) {
        displayquestion();
    }
    
}
