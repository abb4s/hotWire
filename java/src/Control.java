import java.awt.Point;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Control {
	Point a,b;//position of both device points on that's columns 
	String path[];
	public Control(){
		path=new String[0];
	}
	public String[] explodeMainPath(String mainPath){
		//take main path from input(user or file) and explode that to unit paths in array some thing like this
		Pattern ptr=Pattern.compile("(h\\x40\\d{1,3}|vr\\x40\\d{1,3}|vl\\x40\\d{1,3})");
		Matcher matcher=ptr.matcher(mainPath);
		int count = 0;
		while (matcher.find())
		    count++;
		path=new String[count];
		matcher=matcher.reset();
		for(int i=0;matcher.find();i++){
			path[i]=matcher.group(0);
		}
		
		return path;
	}
	public void performPath(){

		for (String str : path) {
			if(str.matches("h\\x40\\d{1,3}")){
				Communicator.send(str);
			}
			//it's just a sample and because of the input that will take from some things like blender or sketchup that have special format for paths,
			// i think the send command for every unit path perhaps should be in special part.
			/*else if(str.matches("vr\\x40\\d{1,3}")){
				Communicator.send(str);
			}
			else if(str.matches("vl\\x40\\d{1,3}")){
				Communicator.send(str);
			}
			else{
				
			}*/
		}
	}
}
