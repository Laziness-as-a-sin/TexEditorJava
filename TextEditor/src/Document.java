import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import org.json.JSONException;
import org.json.JSONObject;

public class Document {
    ArrayList<Paragraph> p = new ArrayList();
    ArrayList<JSONObject> cord = new ArrayList();
    
    public Document() throws JSONException{
        Paragraph zero = new Paragraph(2, 12);
        p.add(zero);
    }
    
    public void add() throws JSONException{
        
        int y = p.get(p.size()-1).y;
        Paragraph tec = new Paragraph(2, y+12);
        p.add(tec);
    }
    
    public void delete(){
        
    }
    
    public void rewritetec(char tec) throws JSONException{
        if(tec == 8){
            p.get(p.size()-1).deletelast();
        }
        else{
            p.get(p.size()-1).add(tec);
        }
    }
    
    public void paint(Graphics g) throws JSONException{
        int x = p.get(p.size()-1).x;
        int y = p.get(p.size()-1).y;
        g.setColor(Color.black);
        g.drawLine(x, y, x, y-10);
        for(int i = 0; i < p.size(); i++){
            p.get(i).paint(g);
        }
    }
}
