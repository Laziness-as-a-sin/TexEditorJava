import java.awt.Color;
import java.awt.Font;
import org.json.JSONObject;
import org.json.JSONException;
import java.awt.Graphics;
import java.util.ArrayList;

public class Paragraph {
    
    JSONObject p = new JSONObject();
    ArrayList<JSONObject> t = new ArrayList();
       
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
}
