package quizServer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Satvik
 */

import java.net.*;
import java.io.*;
import java.sql.*;

public class ClientHandeler extends Thread {
    
    Socket s;
    InputStream is;
    OutputStream os;
    InputStreamReader isr;
    OutputStreamWriter osr;
    BufferedReader br;
    BufferedWriter bw;
    LogIn log;
    boolean flag=false;
    String userName;
    
    
    public ClientHandeler(Socket s, InputStream is, OutputStream os)
    {
         this.is = is;
         this.os = os;
         this.s = s;
         System.out.println("thread started");
    }
     
     @Override
     public void run()
     {
        isr = new InputStreamReader(is);
        osr = new OutputStreamWriter(os);
        br = new BufferedReader(isr);
        bw = new BufferedWriter(osr);
         System.out.println("inside run method");
         
         try{
            String userName = br.readLine();
            System.out.println(userName);
            String password = br.readLine();
            System.out.println(password);
            String name = br.readLine();
            System.out.println(name);
            //String tmp = br.readLine();
            int techOrStu = 1; //Integer.parseInt(tmp);
             if(name.compareTo("null") == 0)
             {
                 log = new LogIn(userName,password);
                 log.connect();
                 String str;
                 System.out.println("Prepare to Login");
                 if(log.login())
                 {
                     System.out.println("Logged in Successfully");
                     str = "Logged in Successfully"+"\n";
                     bw.write(str);
                     bw.flush();
                     flag=true;
                     this.userName = userName;
                 }else{
                     System.out.println("Unable to Login");
                     str = "Unable to login"+"\n";
                     bw.write(str);
                     bw.flush();
                 }
                 
             }
             else{
                 log = new LogIn(userName,password,name,techOrStu);
                 log.connect();
                 System.out.println("Preparing to register");
                  String str;
                 if(log.register())
                 {  
                      System.out.println("Successfully Registered");
                      str = "Successfully Registered"+"\n";
                      bw.write(str);
                      bw.flush();
                   
                 }else{
                       System.out.println("Unable to Register");
                       str = "Unable to Register"+"\n";
                     bw.write(str);
                     bw.flush();
                 }
            }
            /*String signal = br.readLine();
            if(signal == Constant.UPLOAD){
                
            }
            else if(signal == Constant.DISPLAY){
                
            }
            else if(signal == Constant.STREAM){
                String videoID = br.readLine();
                
            }
            else {
                
            }
            */
            
            while(true && flag )
            {
                System.out.println("Inside loop.....");
                
                String option = br.readLine();
                System.out.println(option);
                if(option.equalsIgnoreCase("addquestion"))
                {
                    int count = log.getCount(true);
                    count++;
                    String contestID = userName+"."+Integer.toString(count);
                    String question = br.readLine();
                    String answer = br.readLine();
                    System.out.println(question);
                    System.out.println(answer);
                    log.insert(contestID, question, answer);
                }
                
                if(option.equalsIgnoreCase("takequiz"))
                {
                   String quiz_code = br.readLine();
                   System.out.println(quiz_code);
                   
                   ResultSet rs = log.getQuestion(quiz_code);
                   int size=0;
                   if(rs!=null)
                   {
                       rs.last();
                       size = rs.getRow();
                   }
                   System.out.println(size);
                   bw.write(Integer.toString(size)+"\n");
                   rs.beforeFirst();
                   while(rs.next())
                   {
                       String question = rs.getString(2);
                       bw.write(question+"\n");
                       System.out.println(question);
                   }
                   
                   bw.flush();
                    
                   
                }
                if(option.equalsIgnoreCase("end"))
                    break;
            }
        }
         catch(Exception ex)
         {
             System.out.println("Connection Lost: ");
         }
         
         
         
         
         
     
     }
    
}
