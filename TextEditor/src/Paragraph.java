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
    Font font = new Font("Arial", Font.PLAIN, 15); 
    public Paragraph() throws JSONException{
        p.put("Text", "");
    }
    
    public void paint(Graphics g, int x, int y) throws JSONException{       
        g.setColor(Color.black);         
        String nowWord = (String) p.get("Text");
        //g.setFont(font); 
        g.drawString(nowWord, x, y);
    }
    
    public void add(char tec, int posCur) throws JSONException{
        String str = (String) p.get("Text");
        String str1 = str.substring(0, posCur);
        String str2 = str.substring(posCur, str.length());
        str = str1 + tec + str2;
        p.put("Text", str);
    }
    
    public void deleteElement(int posCur) throws JSONException{
        String str = (String) p.get("Text");        
        String str1 = str.substring(0, posCur-1);
        String str2 = str.substring(posCur, str.length()); 
        str = str1 + str2;
        p.put("Text", str);
    } 
    
    public int posFromStart(int k, Graphics g) throws JSONException{
        String str = (String) p.get("Text");
        str = str.substring(0, k);
//        AffineTransform affinetransform = new AffineTransform();
//        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
//        int textwidth = (int)(font.getStringBounds(str, frc).getWidth());
        int textwidth = g.getFontMetrics().stringWidth(str);
        return(textwidth);        
    }
    
    public int length() throws JSONException{
        String str = (String) p.get("Text");
        return(str.length());
    }
    
}
