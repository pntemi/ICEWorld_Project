import iceworld.given.ICEWorldImmigration;


public class TestWalk {
	public static void main(String[] args){
		Icetizen ploy = new Icetizen();
		ploy.setListeningPort(256);
		ICEWorldImmigration immigration = new ICEWorldImmigration(ploy);
		ploy.setUsername("Nutcha.Te");
		immigration.login("3137896");
		for(int i=1;i<100;i+=5){
			immigration.walk(i, i);
			System.out.println("walking..");
		}
		
	}
}
