
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Device {
	private String code[];
	private Communicator communicator;
	private Point path[];
        private double rightColPos;
        private double leftColPos;
        private double horPos;
        private Shape shape;
        public static final double MAXHEIGH=4;
        public static final double MAXWIDTH=6;
        
      
        private void moveRightColToPos(double x){
           if(0<x && x<MAXHEIGH){
               command("vr@"+(x-rightColPos));
               rightColPos=x;
           }
        }
        private void moveRightCol(double x){
           if(0<x+rightColPos && x+rightColPos<MAXHEIGH){
               command("vr@"+x);
               rightColPos=x+rightColPos;
           }            
        }
        private void moveLeftColToPos(double x){
           if(0<x && x<MAXHEIGH){
               command("vr@"+(x-rightColPos));
               rightColPos=x;
           }            
        }
        private void moveLeftCol(double x){
           if(0<x+rightColPos && x+rightColPos<MAXHEIGH){
               command("vr@"+x);
               rightColPos=x+rightColPos;
           }                
        }
        private void moveHorToPos(double x){
           if(0<x && x<MAXWIDTH){
               command("vr@"+(x-rightColPos));
               rightColPos=x;
           }            
        }
        private void moveHor(double x){
            
        }
        public void startDraw(String filename){
            shape.getFromFile(filename);
            path=shape.getPath();
            drawShape(shape);
        }
        private void drawShape(Shape shape){
            int i=0;
            for(i=0 ; i<path.length-1 ; i++){
                drawLine(path[i], path[i+1]);
            }
            drawLine(path[i+1],path[0]);
        }
        private void drawLine(Point a,Point b){
            double stairLength,dx,dy, stairCount,m;
            
            if((a.x-b.x)==0){
            //when the line is vertical
                moveRightColToPos(b.y);
                moveLeftColToPos(b.y);
            }
            else{
                m=(a.y-b.y)/(a.x-b.x);
                if(m==0){
                //when the line is hortical
                    moveHorToPos(b.x);
                }else{
                    stairLength=0.01;
                    dx=Math.sqrt(Math.pow(stairLength, 2)/Math.pow(m, 2)-1);
                    dy=dx*m;
                    stairCount=Math.ceil(Math.abs(a.x-b.x)/dx);
                    for(int i=0;i<stairCount; i++){
                        moveRightCol(dy);
                        moveLeftCol(dy);
                        moveHor(dx); 
                    }                    
                }
        
            }            
        }
	private void command(String str){
		communicator.reconnect();
		communicator.send(str);
	}
	private void resetWire(){
		//command("h@6");
		command("vr@4");
		command("vl@4");
	}
        public void test(){
            
        }
	public Device(){
                shape=new Shape();
		code=new String[0];
		communicator=new Communicator();
	}
        
        
        
        
        
        
        
        
	private String[] explodeMainPath(String mainPath){
		//take main path from input(user or file) and explode that to unit paths in array some thing like this
		Pattern ptr=Pattern.compile("(h\\x40\\d{1,3}|vr\\x40\\d{1,3}|vl\\x40\\d{1,3})");
		Matcher matcher=ptr.matcher(mainPath);
		int count = 0;
		while (matcher.find())
		    count++;
		code=new String[count];
		matcher=matcher.reset();
		for(int i=0;matcher.find();i++){
			code[i]=matcher.group(0);
		}
		return code;
	}
	private void performPath(){
		
		for (String str : code) {
			if(str.matches("h\\x40\\d{1,3}")){
				//Communicator.send(str);
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
