import java.awt.Color;
import java.awt.Font;
import org.json.JSONObject;
import org.json.JSONException;
import java.awt.Graphics;
import static java.awt.RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT;
import static java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Paragraph {
    
    JSONObject p = new JSONObject();
    ArrayList lineHeight = new ArrayList();
    ArrayList<JSONObject>aText = new ArrayList();
    Font font = new Font("Arial", Font.PLAIN, 15);
    
    public Paragraph() throws JSONException{
        JSONObject f = new JSONObject();
        f.put("type", 0);//0-text, 1-picture
        f.put("text", "");
        f.put("fontName", font);
        f.put("fontSize", 12);
        f.put("color", Color.black);
        f.put("width", 0);
        f.put("height", 0);
        f.put("length", 0);
        aText.add(f);
        p.put("Text", "");
        p.put("color", Color.black);
        
        lineHeight.add(f.get("fontSize"));
    }
    
    public void paint(Graphics g, int x, int y) throws JSONException{       
//        g.setColor(Color.black);
//        g.setColor((Color) p.get("color"));
//        String nowWord = (String) p.get("Text");
//        g.setFont(font); 
//        g.drawString(nowWord, x, y);
        int localX = x, localY = y;
        int indexHeight = 0;
        localY += (int) lineHeight.get(indexHeight);
        for(int i = 0; i < aText.size(); i++){
            g.setColor((Color) aText.get(i).get("color"));
            g.setFont((Font) aText.get(i).get("fontName"));
            g.drawString((String) aText.get(i).get("text"), localX, localY);
            localX += (int) aText.get(i).get("width");
        }
    }
    
    public void add1(char tecSymb, JSONObject tecObj) throws JSONException{
        aText.add(tecObj);
        if((int) tecObj.get("height") > (int) lineHeight.get(lineHeight.size()-1)){
            lineHeight.add(lineHeight.size()-1, tecObj.get("height"));
        } 
        add(tecSymb);
    }
    
    public void add(char tecSymb) throws JSONException{
        String str = (String) aText.get(aText.size()-1).get("text");
        str += tecSymb;
        aText.get(aText.size()-1).put("text", str);
        aText.get(aText.size()-1).put("width", widthTec((String) aText.get(0).get("text"),font));  
    }
    
    public void deleteElement(int posCur) throws JSONException{
        String str = (String) p.get("Text");        
        String str1 = str.substring(0, posCur-1);
        String str2 = str.substring(posCur, str.length()); 
        str = str1 + str2;
        p.put("Text", str);
    } 
    
    public int posFromStart(int k/*, Graphics */) throws JSONException{
        String str = (String) p.get("Text");
        str = str.substring(0, k);
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(null,VALUE_TEXT_ANTIALIAS_DEFAULT,VALUE_FRACTIONALMETRICS_DEFAULT);     
        int textwidth = (int)(font.getStringBounds(str, frc).getWidth());
        //int textwidth = g.getFontMetrics().stringWidth(str);
        return(textwidth);        
    }
    
    public int length() throws JSONException{
        String str = (String) p.get("Text");
        return(str.length());
    }
    
    private int widthTec(String str, Font font){
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(null,VALUE_TEXT_ANTIALIAS_DEFAULT,VALUE_FRACTIONALMETRICS_DEFAULT);     
        int textWidth = (int)(font.getStringBounds(str, frc).getWidth());
        return textWidth;
    } 
 
    
}
