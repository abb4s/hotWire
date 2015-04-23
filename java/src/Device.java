
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Device {
	private String code[];
	private Communicator communicator;
	private Point path[];
        private double rightColPos=2;
        private double leftColPos=2;
        private double horPos=0;
        private Shape shape;
        public static final double MAXHEIGH=4;
        public static final double MAXWIDTH=5.3;
        public static final double MOVESPEED=0.5;
        
      
        private void moveRightColToPos(double x){
           if(0<=x && x<=MAXHEIGH){
               command("vr@"+(x-rightColPos));
               rightColPos=x;
           }
        }
        private void moveRightCol(double x){
           if(0<=x+rightColPos && x+rightColPos<=MAXHEIGH){
               command("vr@"+x);
               rightColPos=x+rightColPos;
           }            
        }
        private void moveLeftColToPos(double x){
           if(0<=x && x<=MAXHEIGH){
               command("vl@"+(x-leftColPos));
               leftColPos=x;
           }            
        }
        private void moveLeftCol(double x){
           if(0<=x+leftColPos && x+leftColPos<=MAXHEIGH){
               command("vl@"+x);
               leftColPos=x+leftColPos;
           }                
        }
        private void moveVerticals(double x){
        	moveRightCol(x);
        	moveLeftCol(x);
        	if(0<=x+rightColPos && x+rightColPos<=MAXHEIGH){
        		waitForMove(x);
        	} 
        }
        private void moveVerticalsToPos(double x){
        	double temp=rightColPos;
        	moveRightColToPos(x);
        	moveLeftColToPos(x);
        	if(0<=x && x<=MAXHEIGH){
        		waitForMove(temp-x);
        	}       	
        }
        private void moveHorToPos(double x){
           if(-MAXWIDTH<=x && x<=MAXWIDTH){
               command("h@"+(x-horPos));
               waitForMove(horPos-x);
               horPos=x;
           }            
        }
        private void moveHor(double x){
            if(-MAXWIDTH<=x+horPos && x+horPos<=MAXWIDTH){
                command("h@"+x);
                horPos=x+horPos;
                waitForMove(x);
            }     
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
            double stairLength,dx,dy,m,c;
            int stairCount;
            moveWireTo(a);
            if((a.x-b.x)==0){
            //when the line is vertical
                moveVerticalsToPos(b.y);
            }
            else if(a.y-b.y == 0){
            	moveHorToPos(b.x);
            }
            else{
                m=(a.y-b.y)/(a.x-b.x);
                System.out.println("m = "+ m);
                stairLength=0.1;
                dx=Math.sqrt(Math.pow(stairLength, 2)/(1+Math.pow(m, 2)));
                if(b.x-a.x <0)
                	dx*=-1;
                System.out.println("dx = "+ dx);
                dy=Math.sqrt(Math.pow(stairLength, 2)-Math.pow(dx, 2));
                if(b.y - a.y <0)
                	dy*=-1;
                System.out.println("dy = "+ dy);               
                stairCount=(int) Math.abs(Math.ceil(Math.abs(a.x-b.x)/dx));
                System.out.println("stair count = "+ stairCount);
                for(int i=0;i<stairCount; i++){
                	moveVerticals(dy);
                	moveHor(dx);
                	System.out.println("i = " + i);
                }
            }            
        }
	private void command(String str){
		communicator.reconnect();
		communicator.send(str);
	}
	private void resetDevice(){
		moveVerticalsToPos(MAXHEIGH);
		moveHorToPos(MAXWIDTH);
	}
	private void moveWireTo(Point a){
		moveVerticalsToPos(a.y);
		moveHorToPos(a.x);
	}
    public void test(){
      drawLine(new Point(5.3,4.0,0.0),new Point(-5.3,0.0,0.0));
    }
    private void waitForMove(double l){
    	//v=x/t => t=x/v
    	l=Math.abs(l);
    	long t=(long) (1000 * (l/MOVESPEED));
    	try {
			System.out.println("waiting for : " + t + "secconds + 1.5s" );
			Thread.sleep(t + 1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public Device(){
        shape=new Shape();
		code=new String[0];
		communicator=new Communicator();
		resetDevice();
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
