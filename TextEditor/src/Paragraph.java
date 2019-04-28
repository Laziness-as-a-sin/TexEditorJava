import org.json.JSONObject;
import org.json.JSONException;
import java.awt.Graphics;
import java.util.ArrayList;

public class Paragraph {
    
    int x, y;
    //JSONObject p = new JSONObject();
    ArrayList<JSONObject> t = new ArrayList();
    public Paragraph(int x1, int y1) throws JSONException{
        x = x1;
        y = y1;
    }
    
    public void paint(Graphics g){
        
    }
    
    public void add(char tec) throws JSONException{
        JSONObject p = new JSONObject();
        p.put("Text", tec);
        t.add(p);
    }
}
