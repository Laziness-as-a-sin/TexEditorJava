import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import org.json.JSONException;
import org.json.JSONObject;

public class Document {
    ArrayList<Paragraph> p = new ArrayList();
    ArrayList<JSONObject> cord = new ArrayList();
    int i = 0;
    
    public Document() throws JSONException{
        Paragraph zero = new Paragraph();
        p.add(zero);
    }
    
    public void add() throws JSONException{
        i++;
        Paragraph newParagraph = new Paragraph();
        p.add(i, newParagraph);
    }
    
    public void delete(){
        
    }
    
    public void rewritetec(char tec) throws JSONException{
        if(tec == 8){
            p.get(i).deletelast();
        }
        else{
            p.get(i).add(tec);
        }
    }
    
    public void paint(Graphics g) throws JSONException{
        int xt = 1, yt = 2;
        g.setColor(Color.black);
        //g.drawLine(x, y, x, y-10);
        for(int i = 0; i < p.size(); i++){
            yt += 12;
            p.get(i).paint(g, xt, yt);
        }
        
//        String nk = "HOHOH";
//        String nk1 = "HOHO";
//        g.setFont(new Font("TimesRoman", Font.PLAIN, 12)); 
//        g.drawString(nk , 100, 100);
//        int width = g.getFontMetrics().stringWidth(nk1);
//        g.drawLine(100+width, 100, 100+width, 100-10);
    }
    
    public void click(){
        
    }
    
    public void highlightTop(){//maybe input un one function
        if(i > 0){
            i--;
        }
    }
    
    public void highlightBot(){//maybe input un one function
        if(i < (p.size()-1)){
            i++;
        }
    }
}
