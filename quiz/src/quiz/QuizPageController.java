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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

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
    int counter=0 , no_of_ques;
    private String ques[] , ans[];
    @FXML
    private Label currentQuestionNo;
    private int maxMark , obtainedMark;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        obtainedMark = 0;
        counter = 0;
    }    
    
    public void setSocket(Socket so ,  BufferedReader br , BufferedWriter bw){
        this.so = so;
        this.br = br;
        this.bw = bw;
        
    }
    
    private void displayquestion()
    {
        StringTokenizer st1 = new StringTokenizer(ques[counter],"#");

        question.setText(st1.nextToken());
        option1.setText(st1.nextToken());
        option2.setText(st1.nextToken());
        option3.setText(st1.nextToken());
        option4.setText(st1.nextToken());
        currentQuestionNo.setText(Integer.toString(counter+1)+"/"+Integer.toString(no_of_ques));
    }
    public void getQuestion()
    {
        try{
            String inp = br.readLine();
            int size = Integer.parseInt(inp);
            no_of_ques=size;
            System.out.println(size);
            ques = new String[size];
            ans = new String[size];
             
            for(int i=0; i<size; i++)
            {
                String question = br.readLine();
                ques[i] = question;
                ans[i] = br.readLine();
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
        
        String tmp = "";
        if(option1.isSelected()){
            tmp = option1.getText();
        }
        else if(option2.isSelected()){
            tmp = option2.getText();
        }
        else if(option3.isSelected()){
            tmp = option3.getText();
        }
        else if(option4.isSelected()){
            tmp = option4.getText();
        }
        
        if(tmp.equals(ans[counter])){
            obtainedMark++;
        }
        nextButtonAction(event);
        
    }

    @FXML
    private void nextButtonAction(ActionEvent event) {
        counter++;
        if(counter >= no_of_ques ){
            displayResult(event);
        }
        else
            displayquestion();
    }
    
    
    public void displayResult(ActionEvent event){
        //System.out.println(obtainedMark);
        
        String final_ans = " You have secured "+Integer.toString(obtainedMark)+" out of "+Integer.toString(no_of_ques)+" ";
        Label label = new Label(final_ans);
        Label result = new Label("Your result:");
        AnchorPane pane = new AnchorPane();
        pane.setPrefSize(400, 400);
   
        pane.setStyle("-fx-background-color: #333645;");
        Popup popup = new Popup();
        label.setStyle("-fx-background-color: white;");
        label.setFont(new Font(25.0));
        result.setFont(new Font(40.0));
        label.setLayoutX(50);
        label.setLayoutY(200);
        result.setLayoutX(100);
        result.setLayoutY(100);
        result.setTextFill(Color.RED);
        
        pane.getChildren().add(label);
        pane.getChildren().add(result);
        popup.getContent().add(pane);
        
        popup.setAutoHide(true);
        popup.show((Stage)((Node) event.getSource()).getScene().getWindow());
        
    }
    
}
