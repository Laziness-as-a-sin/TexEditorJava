import org.json.JSONObject;
import org.json.JSONException;
import java.awt.Graphics;

public class Paragraph {
    
    int x, y;
    JSONObject p = new JSONObject();
    
    public Paragraph(int x1, int y1) throws JSONException{
        x = x1;
        y = y1;
    }
    
    public void paint(Graphics g){
        
    }
    
}
