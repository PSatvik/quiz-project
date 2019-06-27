package quiz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author sanat
 */
public class RegisterController implements Initializable {
    
    Socket so;
    InputStream is;
    OutputStream os;
    InputStreamReader isr;
    OutputStreamWriter osr;
    BufferedReader br;
    BufferedWriter bw;

    @FXML
    private AnchorPane registerPane;
    @FXML
    private TextField userName;
    @FXML
    private TextField mailId;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField repassword;
    @FXML
    private Button backButton;
    @FXML
    private Button submitButton;
    @FXML
    private Button exitbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         String host = "localhost";
        int port = 25001;
        InetAddress address;
        try {
            address = InetAddress.getByName(host);
            so = new Socket(address,port);
            
            is = so.getInputStream();
            os = so.getOutputStream();
            isr = new InputStreamReader(is);
            osr = new OutputStreamWriter(os);
            br = new BufferedReader(isr);
            bw = new BufferedWriter(osr);
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    

    @FXML
    private void backButtonAction(ActionEvent event) {
        
        try {
            AnchorPane rootPane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            registerPane.getChildren().setAll(rootPane);
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void submitButtonAction(ActionEvent event) {
        
       String Name ;
       String pass ;
       String userID;
       
       Name = userName.getText();
       pass = password.getText();
       userID = mailId.getText();
       
           
       String str;
        try{
            str = userID+"\n";
            System.out.println(str);
            bw.write(str);
            str = pass+"\n";
            System.out.println(str);
            bw.write(str);
            str = Name+"\n";
            System.out.println(str);
            bw.write(str);
            bw.flush();
            
            String st = br.readLine();
            System.out.println(st);
            
           
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
           
       }
       
    

@FXML
private void exitButtonAction(ActionEvent event) {
        System.exit(0);
}
    
}
