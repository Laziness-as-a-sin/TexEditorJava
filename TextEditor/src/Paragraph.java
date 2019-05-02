import java.awt.Color;
import java.awt.Font;
import org.json.JSONObject;
import org.json.JSONException;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Paragraph {
    
    JSONObject p = new JSONObject();
    ArrayList<JSONObject> t = new ArrayList();
    int k = 0;
    
    public Paragraph() throws JSONException{
        p.put("Text", "");
    }
    
    public void paint(Graphics g, int x, int y) throws JSONException{       
        g.setColor(Color.black);         
        String nowWord = (String) p.get("Text");
        g.setFont(new Font("TimesRoman", Font.PLAIN, 12)); 
        g.drawString(nowWord, x, y);
    }
    
    public void add(char tec) throws JSONException{
        String nowWord = (String) p.get("Text"); 
        nowWord += tec;
        p.put("Text", nowWord);
    }
    
    public void deletelast() throws JSONException{
        String str = (String) p.get("Text"); 
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        
        p.put("Text", str);
    } 
    
    private int posFromStart() throws JSONException{
        String str = (String) p.get("Text");
        str = str.substring(0, k);
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        Font font = new Font("TimesRoman", Font.PLAIN, 12);//need change
        int textwidth = (int)(font.getStringBounds(str, frc).getWidth());
        return(textwidth);        
    }
    
    public int moveCursorR() throws JSONException{
        String str = (String) p.get("Text");
        if(k < (str.length()-1)){
            k++;
            return(posFromStart());
        }
        else
            return(posFromStart());
    }
    
//    private String(String str, int i, String instr){
//        
//    }
}
