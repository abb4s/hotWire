import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Shape {
    private Point points[];
    private Face faces[];
    private File file;
    public Shape(){
        
    }
    public Shape(String filePath){
        getFromFile(filePath);
    }
    public Point[] getPoints() {
        return points;
    }

    public Face[] getFaces() {
        return faces;
    }
    
    
    public void setPoints(Point[] points) {
        this.points = points;
    }

    public void setFaces(Face[] faces) {
        this.faces = faces;
    }
    
    public Point[] getFromFile(String path){
        String str;
        String temp[];
        try{
            /*
             * reading file
             */
            file=new File(path);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            str = new String(data, "UTF-8");
            /*
             * for parsing verticies
             */
            Pattern ptr=Pattern.compile("v (.*)");
            Matcher matcher=ptr.matcher(str);
            int count = 0;
            while (matcher.find())
		 count++;
            points=new Point[count];
            matcher=matcher.reset();
            
            for(int i=0;matcher.find();i++){
                temp=matcher.group(0).split(" ");
                points[i]=new Point(Double.parseDouble(temp[1])*100,Double.parseDouble(temp[3])*100,Double.parseDouble(temp[2])*100);
                points[i].index=i+1;
            }
            /*
             * for parsing faces
             */
            ptr=Pattern.compile("(f )(.*)");
            matcher=ptr.matcher(str);
            count = 0;
            while (matcher.find())
		 count++;
            faces=new Face[count];
            matcher=matcher.reset();
            for(int i=0;matcher.find();i++){
                temp=matcher.group(2).split(" ");
                Point tp[]=new Point[temp.length];
                for (int j=0;j<temp.length;j++) {
                    tp[j]=points[Integer.parseInt(temp[j])-1];
                }
                faces[i]=new Face(tp);
            }
            
            /*
             * print shap after parse
             */
            for(Point p : points){
                System.out.print("verticy : ");
                System.out.print(p.x + "\t"+ p.z + "\t"+ p.y );
                System.out.println();
            }
            for(Face f : faces){
                System.out.print("face : ");
                for(Point p : f.getPoints())
                  System.out.print(p.index + " ");
                System.out.println();
            }
            
            
        }
        catch(FileNotFoundException e){
           
        } 
        catch (IOException ex) {
            
        }

        return null;
    }
    public Point[] getPath(){
        int x;
        Integer x1=12;
        
        
    	return null;
    }

}
