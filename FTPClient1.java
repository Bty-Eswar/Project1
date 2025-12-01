 

import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
public class FTPClient1{
    public static void main(String[] args) throws Exception{
        JFrame f=new JFrame("Client");
        JTextField tf=new JTextField(15);
        JButton b=new JButton("Send File");
        JLabel lbl=new JLabel("Output...");
        f.add(tf);f.add(b);f.add(lbl);
        f.setLayout(new FlowLayout());
        f.setSize(400,150);
        f.setVisible(true);
        Socket s=new Socket("localhost",5000);
        PrintWriter out=new PrintWriter(s.getOutputStream(),true);
        BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
        b.addActionListener(e->out.println(tf.getText()));
        while(true)
        {
            String encrypted=in.readLine();
            if(encrypted==null)break;
            if(encrypted.startsWith("ERROR")){
                lbl.setText(encrypted);
                continue;
            }
            String decrypted="";
            for(char c:encrypted.toCharArray()){
                decrypted+=(char)(c-1);
            }
            lbl.setText("Decrypted:"+decrypted);
            FileWriter fw=new FileWriter("decrypted_output.txt");
            fw.write(decrypted);
            fw.close();
        }
    }
}
 
 


 