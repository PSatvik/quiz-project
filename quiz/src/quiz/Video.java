/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

/**
 *
 * @author Satvik
 */
import java.io.*;
import java.net.InetAddress;

import java.net.Socket;
import java.nio.file.Files;

public class Video {
    
    Socket so;
    File f;
    String userName;
    String videoName;
    String tags;
    
    public Video(File f,String userName,String videoName, String tags)
    {
        this.f = f;
        this.userName = userName;
        this.videoName = videoName;
        this.tags = tags;
        
    }
    
    public void connect()
    {
        try{
            System.out.print("Connecting to server.......");
            String host = "localhost";
            int port = 25002;
            InetAddress address = InetAddress.getByName(host);
            so = new Socket(address,port);
            
        }
        catch(Exception e)
        {
            System.out.println("unable to connect");
        }
        System.out.print("Connected");
    }
    
    public void send()
    {
        try{
            if(f.exists())
                System.out.println("file present");
            
            System.out.println("hey");
            System.out.println(f.getAbsolutePath());
           
            FileReader fr=new FileReader(f.getAbsolutePath());  
            BufferedReader br = new BufferedReader(fr);
            
            System.out.println("1");
            //OutputStreamWriter osw = new OutputStreamWriter(os);
            //BufferedWriter bw = new BufferedWriter(osw);
            
            DataOutputStream out = new DataOutputStream(so.getOutputStream());
            byte[] array = Files.readAllBytes(f.toPath());
            System.out.println("2");
            
            out.writeUTF(userName);
            out.writeUTF(videoName);
            out.writeUTF(tags);
            
            out.writeInt(array.length);
            
            for(byte b : array)
            {
               out.write(array);
            }
            
        }
        catch(Exception e)
        {
            System.out.println("file not found");
            System.out.println(e.getMessage());
        }
    }
}
