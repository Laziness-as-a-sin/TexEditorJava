import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import org.json.JSONException;
import org.json.JSONObject;

public class Document {
    ArrayList<Paragraph> p = new ArrayList();
    
    public Document() throws JSONException{
        Paragraph zero = new Paragraph(2, 12);
        p.add(zero);
    }
    
    public void add() throws JSONException{
        int y = p.get(p.size()-1).y;
        Paragraph zero = new Paragraph(2, y+12);
        p.add(zero);
    }
    
    public void delete(){
        
    }
    
    public void paint(Graphics g){
        int x = p.get(p.size()-1).x;
        int y = p.get(p.size()-1).y;
        g.setColor(Color.black);
        g.drawLine(x, y, x, y+10);
    }
}
