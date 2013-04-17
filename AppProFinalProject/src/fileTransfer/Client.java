package fileTransfer;
import java.net.*;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Client {
	//ifSelecteed();
	protected String ipAddress;
	protected int port;
	public Client(String ip, int port){
		ipAddress = ip;
		this.port = port;
	}
	protected void showDialog(){
		String title = "Accept file";
		String message = "Do you want to accept the file transfer?";
		int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
		if(reply == JOptionPane.YES_OPTION){
			receiveFile();
		}else{
			JOptionPane.showMessageDialog(new JPanel(),"Terminate File Transfer");
		}
	}
	protected void receiveFile(){
		 byte[] aByte = new byte[1];
	        int bytesRead;

	        Socket clientSocket = null;
	        InputStream is = null;

	        try {
	            clientSocket = new Socket( ipAddress , port );
	            is = clientSocket.getInputStream();
	       } catch (IOException ex) {
	         // Do exception handling
	       }

	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        if (is != null) {

	            FileOutputStream fos = null;
	            BufferedOutputStream bos = null;
	            try {
	                fos = new FileOutputStream(new File(setDirectory(),"test.txt"));
	                bos = new BufferedOutputStream(fos);
	                bytesRead = is.read(aByte, 0, aByte.length);

	                do {
	                        baos.write(aByte);
	                        bytesRead = is.read(aByte);
	                } while (bytesRead != -1);

	                bos.write(baos.toByteArray());
	                bos.flush();
	                bos.close();
	                clientSocket.close();
	                JOptionPane.showMessageDialog(new JPanel(),"Done receiving!");
	            } catch (IOException ex) {
	                // Do exception handling
	            }
	        }
	}
	protected String setDirectory(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnValue = fileChooser.showOpenDialog(null);
		String store="";
		if (returnValue == JFileChooser.APPROVE_OPTION) 
			store = fileChooser.getSelectedFile().getAbsolutePath()+"\\";
		//System.out.println(store);
		return store;
	}
	public static void main(String[] args){
		//Server server = new Server();
		//server.sendFile();
		Client client = new Client("124.121.223.219", 51234);
		client.showDialog();
		
	}
}
