import iceworld.given.ICEWorldImmigration;


public class TestWalk {
	public static void main(String[] args){
		Icetizen ploy = new Icetizen();
		ploy.setIcePortID(256);
		ICEWorldImmigration immigration = new ICEWorldImmigration(ploy);
		ploy.setUsername("Nutcha.Te");
		immigration.login("3137896");
		immigration.talk("heyyy");
		/*for(int i=100;i>1;i-=5){
			immigration.walk(i, i);
			System.out.println("walking..");
		}
		/*Icetizen nongAlien = new Icetizen();
		nongAlien.setIcePortID(555);
		ICEWorldImmigration immigration = new ICEWorldImmigration(nongAlien);
		if(immigration.loginAlien()){
			immigration.walk(20, 30);
			System.out.println("done");
		}*/
	}
}
