/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Satvik
 */
public class AddQuestionController implements Initializable {

    @FXML
    private TextArea question;
    @FXML
    private TextField option1;
    @FXML
    private TextField option2;
    @FXML
    private TextField option4;
    @FXML
    private TextField option3;
    @FXML
    private TextField correct_option;
    @FXML
    private Button submit;
    @FXML
    private Button exit;
    @FXML
    private Label message;
    private Socket so;
    private InputStream is;
    private OutputStream os;
    private OutputStreamWriter osr;
    private InputStreamReader isr;
    private BufferedReader br;
    private BufferedWriter bw;
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setSocket(Socket so , BufferedReader br, BufferedWriter bw){
        this.so = so;
        this.br = br;
        this.bw = bw;
    }

    @FXML
    private void submitButtonAction(ActionEvent event) {
        try {
            String que = question.getText();
            String op1 = option1.getText();
            String op2 = option2.getText();
            String op3 =option3.getText();
            String op4 = option4.getText();
            String ans = correct_option.getText();
            
            String totalquestion = que+"#"+op1+"#"+op2+"#"+op3+"#"+op4;
           
            bw.write(totalquestion+"\n");
            bw.write(ans+"\n");
            bw.flush();
            message.setText("Question added successfully");
            
            
        } catch (IOException ex) {
            Logger.getLogger(AddQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void exitButtonAction(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide(); //to hide current window i.e login window//

    }
    
}
