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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Satvik
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button login;
    @FXML
    private Button signup;
    Socket so;
    InputStream is;
    OutputStream os;
    InputStreamReader isr;
    OutputStreamWriter osr;
    BufferedReader br;
    BufferedWriter bw;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button exit;
    @FXML
    private AnchorPane rootPane;
    
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
        
    }    

    @FXML
    private void loginButtonAction(ActionEvent event) {
        System.out.println("login action");
        String uname = username.getText();
        
        String pass = password.getText();
        String str;
        try{
            str = uname+"\n";
            System.out.println(str);
            bw.write(str);
            str = pass+"\n";
            System.out.println(str);
            bw.write(str);
            str = "null"+"\n";
            System.out.println(str);
            bw.write(str);
            bw.flush();
            
            String st = br.readLine();
            System.out.println(st);
            if(st.compareTo("Logged in Successfully") == 0)
            {
                System.out.println("displaying to home page");
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    
                    HomePageController homeController = fxmlLoader.getController();
                    homeController.setSocket(so, br , bw);
                    
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.setTitle("Panache");
                    //rootPane.setVisible(false);
                    stage.show();
                    ((Node)(event.getSource())).getScene().getWindow().hide(); //to hide current window i.e login window//
                } catch(IOException e) {
                    System.out.println("not displaying home page");
                }
            }
            else{
                System.out.println("laude lag gaye");
            }
            
            
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void signupButtonAction(ActionEvent event) {
        try {
            AnchorPane registerPane = FXMLLoader.load(getClass().getResource("Register.fxml"));
            rootPane.getChildren().setAll(registerPane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void exitButtonAction(ActionEvent event) {
        System.exit(0);
    }
    
}
