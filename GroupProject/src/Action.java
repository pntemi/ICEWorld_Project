
import java.awt.Point;

public class Action {
	protected String timestamp;
	protected long aid;
	protected int actionType;
	protected String word;
	protected Point point;
	
	public Action(String timestamp){
		this.timestamp = timestamp;
	}
	
	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}
	public void setAID(long aid){
		this.aid = aid;
	}
	public void setActionType(int type){
		this.actionType = type;
	}
	public void setWord(String word){
		this.word = word;
	}
	public void setPoint(Point point){
		this.point = point;
	}
	public String getTimestamp(){
		return timestamp;
	}
	public long getAID(){
		return aid;
	}
	public int getActionType(){
		return actionType;
	}
	public String getWord(){
		return word;
	}
}
