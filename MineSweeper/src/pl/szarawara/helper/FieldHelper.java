package pl.szarawara.helper;

import java.util.List;

public class FieldHelper {
	
	private List<Field> neightbours;
	
	public List<Field> getNeightbours() {
		return neightbours;
	}
	public void setNeightbours(List<Field> neightbours) {
		this.neightbours = neightbours;
	}
	
	public int getNeightboursMinesCount(){
		int count=0;
		for (Field field : neightbours){
			if(field.isMine())
				count++;
		}
		return count;
	}
	
}
