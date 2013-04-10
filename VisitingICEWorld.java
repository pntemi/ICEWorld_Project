
package iceworld.sample;

//------------------------------------------------------------------------------
// This example shows:
//    - how to login to the ICE World as Inhabitants/Aliens.
//    - do actions after the Icetizen is logged in.
//    - Logout of the ICE World.
//
// NOTE THAT:
//    - This is just an example. It will not be compiled.
//    - Things with enclosing <<...>> is NOT Java expressions.
//------------------------------------------------------------------------------

// import whatever needed
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//------------------------------------------------------------------------------
// Import helper classes from org.json to easily parse JSON format
// These classes were also downloaded from http://code.google.com/p/json-simple/
// and made available in CourseVille in the .jar format.
// For Eclipse users, add the .jar file as external jar into
// the src folder of your project.
// (project properties > Java Build Path > libraries > Add External JARs) 
//------------------------------------------------------------------------------
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

//import the given classes/interfaces from the ICE World
import iceworld.given.*;

public class VisitingICEWorld{
	public static void main(String[] args) {

		// ----------------------------------------------------------
		// Firstly, you need to create your own class that implements
		// the iceworld.given.MyIcetizen interface. The class should
		// represents your "Icetizen" implementation in your project. 
		// ----------------------------------------------------------

		// ----------------------------------------------------------
		// Instantiate an object of your "Icetizen" class.
		// Set its attributes.

		TestMyIcetizen tester = new TestMyIcetizen();
		// *** Stictly use the ICE Port ID assigned to your group
		tester.setIcePortID(<<The ICE port ID (int) assigned to your group>>);
		tester.setUsername(<<The (String) username of this Icetizen>>);
		// Pick any port number that this instance will use for
		// the peer-to-peer communication of the file transfer
		// action.
		// It is a good idea to find a way ensuring that different
		// instances of your ICE Port running on the same machine
		// do not use duplicated port numbers.
		tester.setListeningPort(<<(String) A port number>>);
		IcetizenLook look = new IcetizenLook();
		look.gidB = "B001";
		look.gidS = "S001";
		look.gidH = "H001";
		look.gidW = "W001";

		// ----------------------------------------------------------
		
		
		// ----------------------------------------------------------
		// Instantiate an object of the ICEWorldImmigration class.
		// Pass the Icetizen object, corresponding to the one to
		// enter the ICE World, as the input to the ICEWorldImmigration
		// constructor.

		ICEWorldImmigration immigration = new ICEWorldImmigration(tester);
		// ----------------------------------------------------------
		
		// ----------------------------------------------------------
		// Do the login as an Alien
		if (immigration.loginAlien()){
			
		}
		// ----------------------------------------------------------

		// ----------------------------------------------------------
		// If you want to do the login as an Inhabitant
		//if (immigration.login(<<(String) the password of the Icetizen>>))
		//{
		//	System.out.println("Login OK");
		//}
		// ----------------------------------------------------------
		
		// ----------------------------------------------------------
		// Say "Hello"
		if (immigration.talk("Hello")){
			System.out.println("Talk OK");
		}
		// ----------------------------------------------------------

		// ----------------------------------------------------------
		// Yell "Hello"
		if (immigration.yell("Hello")){
			System.out.println("Yell OK");
		}
		// ----------------------------------------------------------

		// ----------------------------------------------------------
		// Walk to the (9,4) location of the ICE World ground
		if (immigration.walk(9, 4)){
			System.out.println("Walk OK");
		}
		// ----------------------------------------------------------

		// ----------------------------------------------------------
		// Customize the look of the active Icetizen
		IcetizenLook look2 = new IcetizenLook();
		look2.gidB = "B001";
		look2.gidS = "S019";
		look2.gidH = "H008";
		look2.gidW = "W046";
		tester.setIcetizenLook(look);
		if (ICEWorld.customization(look2)){
			System.out.println("Customization OK");
		}
		// ----------------------------------------------------------

		// ----------------------------------------------------------
		// Log out of the ICE World
		if (ICEWorld.logout()){
			System.out.println("Logout OK");
		}
		// ----------------------------------------------------------

	}

}