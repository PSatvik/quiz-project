/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizServer;
import java.io.*;
import java.net.*;
import java.sql.*;

/**
 *
 * @author Satvik
 */

public class QuestionHandeler extends Thread {

    Socket s;
    DataInputStream dis;

    public QuestionHandeler(Socket s, DataInputStream dis )
    {
        this.s = s;
        this.dis = dis;
    }
    
    @Override
    public void run()
    {
        try{
        
        String contestID = dis.readUTF();
        String question = dis.readUTF();
        String answer = dis.readUTF();
        
        LogIn toAdd = new LogIn();
        toAdd.connect();
        toAdd.insert(contestID,question,answer);
        
        }
        catch(Exception ex)
        {
            System.out.println(" Unable to add question in the database.....");
        }
        
    }
}
