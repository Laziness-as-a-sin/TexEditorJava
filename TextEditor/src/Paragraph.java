import java.awt.Color;
import org.json.JSONObject;
import org.json.JSONException;
import java.awt.Graphics;
import java.util.ArrayList;

public class Paragraph {
    
    int x, y;
    JSONObject p = new JSONObject();
    ArrayList<JSONObject> t = new ArrayList();
    
    
    public Paragraph(int x1, int y1) throws JSONException{
        x = x1;
        y = y1;
        p.put("Text", "");
    }
    
    public void paint(Graphics g/**, int x1, int y1**/) throws JSONException{
        //g.setColor(Color.white);
        //g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        //g.drawString("hello world", 100, 100);         
        String nowWord = (String) p.get("Text");
        g.drawString(nowWord , x, y);
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
