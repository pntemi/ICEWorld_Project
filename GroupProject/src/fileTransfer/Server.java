package fileTransfer;

import java.net.*;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Server {
	// GUI part JFileChooser
	// Server part
	protected File fileToSend;

	public Server() {
		this(null);
	}

	public Server(File fileName) {
		fileToSend = fileName;
	}

	protected void chooseFile() {
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			fileToSend = fileChooser.getSelectedFile();
			System.out.println(fileToSend.getName());
		}
	}

	protected void sendFile() {
		while (true) {
			BufferedOutputStream outToClient = null;
			Socket connectionSocket = null;
			System.out.println("oy");
			ServerSocket welcomeSocket = null;
			System.out.println("seng");
			try {
				welcomeSocket = new ServerSocket(18081);
				System.out.println("1");
				chooseFile();
				connectionSocket = welcomeSocket.accept();
				System.out.println("2");
				outToClient = new BufferedOutputStream(
						connectionSocket.getOutputStream());
				System.out.println("3");
				
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(new JPanel(),
						"Cannot Transfer File");
			}
			if (outToClient != null) {
			
				byte[] mybytearray = new byte[(int) fileToSend.length()];

				FileInputStream fis = null;

				try {
					fis = new FileInputStream(fileToSend);
				} catch (FileNotFoundException ex) {
					JOptionPane.showMessageDialog(new JPanel(),
							"The selected file is not found.");
				}
				BufferedInputStream bis = new BufferedInputStream(fis);

				try {
					bis.read(mybytearray, 0, mybytearray.length);
					outToClient.write(mybytearray, 0, mybytearray.length);
					outToClient.flush();
					outToClient.close();
					connectionSocket.close();

					// File sent, exit the method
					return;
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(new JPanel(),
							"File Transfer Failed");
				}
			}
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.sendFile();
		JOptionPane.showMessageDialog(new JPanel(),"Done sending!");
	}
}
