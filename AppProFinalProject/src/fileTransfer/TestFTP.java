package fileTransfer;

public class TestFTP {
	public static void main(String[] args){
		//Server server = new Server();
		//server.sendFile();
		Client client = new Client("127.0.0.1", 3248);
		client.showDialog();
		
	}
}
