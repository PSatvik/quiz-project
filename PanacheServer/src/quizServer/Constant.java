/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizServer;

/**
 *
 * @author Satvik
 */
public class Constant {
    public static final String SERVER_IP = "localhost";
    public final static String USERNAME = "root";
    public final static String DATABASE = "server";
    public final static String PASSWORD = "root";
    
    // port numbers
    public static final int RTP_PORT = 25001;
    public static final int UPLOAD_PORT = 25002 ;
    public static final int SERVER_PORT = 25001;
    
    // signals
    public static final String UPLOAD = "1";
    public static final String DISPLAY = "0";
    public static final String STREAM = "2";
}
