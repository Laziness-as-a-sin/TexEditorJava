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
import java.io.Serializable;

public class Paragraph implements Serializable{
     private static final long serialVersionUID = 1L;
    //ArrayList lineHeight = new ArrayList(); // Переделать, доделать когда будет перенос строк
    int lineHeight;
    transient private ArrayList<JSONObject>aText = new ArrayList(); // Массив Text/Подпараграф'в
    Font font = new Font("Arial", Font.PLAIN, 15);
    private int tecText = 0;
    public int numSymbol = 0; // Общее число символов в параграфе
    private int indexText = 0; //  Индекс текущего изменяемого подпараграфа
    private int posCurInText; // Позиция курсора в текущем изменяемом подпараграфе
    ArrayList<String> saveText = new ArrayList(); // Сохраняемые подпараграфы
    
    public Paragraph() throws JSONException{
        JSONObject f = new JSONObject();
        f.put("type", 0);// 0-text, 1-picture
        f.put("text", "");
        f.put("font", font);
        f.put("fontString", "Arial");
        f.put("fontSize", 15);
        f.put("colorR", 0);
        f.put("colorG", 0);
        f.put("colorB", 0);
        f.put("width", 0);
        f.put("length", 0);
        aText.add(f);
        lineHeight= (int) f.get("fontSize");
    }
    
    public void paint(Graphics g, int x, int y) throws JSONException{       
        int localX = x, localY = y;
        localY += lineHeight;
        for(int i = 0; i < aText.size(); i++){
            g.setColor(new Color((int)aText.get(i).get("colorR"),(int)aText.get(i).get("colorG"),(int)aText.get(i).get("colorB")));
            System.out.println("color "+(int)aText.get(i).get("colorR")+" "+(int)aText.get(i).get("colorG")+" "+(int)aText.get(i).get("colorB")+" "+i);
            g.setFont((Font) aText.get(i).get("font"));
            g.drawString((String) aText.get(i).get("text"), localX, localY);
            localX += (int) aText.get(i).get("width");
        }
    }
    
    public void addSymbol(char tecSymb, int posCur, JSONObject inJson) throws JSONException{ // Добавление символа в массив по индексу
        setIndexText(posCur);
        if(!checkChangeFont(inJson, aText.get(indexText))){
            addSimple(tecSymb);
        }
        else{
            JSONObject f = new JSONObject();
            f.put("type", 0);// 0-text, 1-picture
            f.put("text", "");
            f.put("fontString", (String) inJson.get("fontString"));
            f.put("fontSize", (int) inJson.get("fontSize"));
            f.put("colorR", (int) inJson.get("colorR"));
            f.put("colorG", (int) inJson.get("colorG"));
            f.put("colorB", (int) inJson.get("colorB"));
            f.put("width", 0);
            f.put("length", 0);
            f.put("font", new Font((String) f.get("fontString"), Font.PLAIN, (int) f.get("fontSize")));


            if(posCur == 0){
                aText.add(0, f);
                addSimple(tecSymb);
            }
            else
                if(indexText == aText.size()-1 && (int)aText.get(indexText).get("length") == posCurInText){
                    aText.add(f);
                    indexText++;
                    posCurInText = 0;
                    addSimple(tecSymb);                
                }
                else
                    if((int)aText.get(indexText).get("length") == posCurInText){
                        aText.add(indexText+1, f);
                        indexText++;
                        posCurInText = 0;
                        addSimple(tecSymb); 
                    }
                    else{
                        JSONObject xxx = aText.get(indexText);
                        String str = (String) aText.get(indexText).get("text");
                        int length = (int) aText.get(indexText).get("length");                       
                        String str1 = str.substring(0, posCurInText);
                        String str2 = str.substring(posCurInText, length);

                        JSONObject a = new JSONObject();
                        a.put("type", 0);// 0-text, 1-picture
                        a.put("text", str1);
                        a.put("fontString", (String) xxx.get("fontString"));
                        a.put("fontSize", (int) xxx.get("fontSize"));
                        a.put("colorR", (int) xxx.get("colorR"));
                        a.put("colorG", (int) xxx.get("colorG"));
                        a.put("colorB", (int) xxx.get("colorB"));
                        a.put("font", new Font((String) a.get("fontString"), Font.PLAIN, (int) a.get("fontSize")));

                        a.put("length", str1.length());
                        a.put("width", widthTec((String) a.get("text"), (Font) a.get("font")));
                        aText.remove(indexText);
                        aText.add(indexText, a);
                        
                        
                        aText.add(indexText+1, f);
                        int kooooo = indexText+2;
                        JSONObject b = xxx;
                        b.put("text", str2);
                        b.put("length", str2.length());
                        b.put("width", widthTec((String) b.get("text"), (Font) b.get("font")));
                        aText.add(kooooo, b);
                        
                        indexText++;
                        posCurInText = 0;
                        addSimple(tecSymb); 
                    }
        }
        numSymbol++; 
    }
    
    private void addSimple(char tecSymb) throws JSONException{
        String str = (String) aText.get(indexText).get("text");
        String str1 = str.substring(0, posCurInText);
        int length = (int) aText.get(indexText).get("length");
        String str2 = str.substring(posCurInText, length);
        str = str1 + tecSymb + str2;    
        aText.get(indexText).put("text", str);
        aText.get(indexText).put("length", str.length());
        aText.get(indexText).put("width", widthTec((String) aText.get(indexText).get("text"), (Font) aText.get(indexText).get("font")));        
    }
    
    public void deleteElement(int posCur) throws JSONException{
        setIndexText(posCur);
        if((int) aText.get(indexText).get("length") == 1){
            if(indexText != 0)
                aText.remove(indexText);
            else{
//                aText.get(indexText).put("text", "");
                aText.get(indexText).put("length", 0);
                aText.get(indexText).put("width", widthTec((String) aText.get(indexText).get("text"), (Font) aText.get(indexText).get("font")));
            }
        }
        else{
            String str = (String) aText.get(indexText).get("text");
            String str1 = str.substring(0, posCurInText-1);
            int length = (int) aText.get(indexText).get("length");
            String str2 = str.substring(posCurInText, length);
            str = str1 + str2;    
            aText.get(indexText).put("text", str);
            aText.get(indexText).put("length", str.length());
            aText.get(indexText).put("width", widthTec((String) aText.get(indexText).get("text"), (Font) aText.get(indexText).get("font")));
        }
        numSymbol--;
    } 
    
    public int posFromStart(int k) throws JSONException{
        int tecText1 = 0;
        int i = 0, length =0;
        while(i < k){
            int tecLength = (int) aText.get(tecText1).get("length");
            Font tecFont = (Font) aText.get(tecText1).get("font");
            String tecString = (String) aText.get(tecText1).get("text");
            int l = 0;
            while(l < tecLength && i < k){
                length += widthTec(tecString.substring(l, l+1), tecFont);
                l++;
                i++;
            }
            if(i < k){
                tecLength = (int) aText.get(tecText1+1).get("length");
                tecFont = (Font) aText.get(tecText1+1).get("font");
                tecString = (String) aText.get(tecText1+1).get("text");
                length += widthTec(tecString.substring(0, 1), tecFont);
            }
            i++;
            tecText1++;
        }
        return length;
    }
    
    private int widthTec(String str, Font font){ // Количество пикселей занимаемых стрингом с заданым шрифтом
        FontRenderContext frc = new FontRenderContext(null,VALUE_TEXT_ANTIALIAS_DEFAULT,VALUE_FRACTIONALMETRICS_DEFAULT);     
        int textWidth = (int)(font.getStringBounds(str, frc).getWidth());
        return textWidth;
    } 
 
    public int paragraphHeigt(){
//        int heigt = 0;
//        for(int i = 0; i < lineHeight.size(); i++)
//            heigt += (int) lineHeight.get(i);
        return lineHeight;
    }
    
    private void setIndexText(int posCur) throws JSONException{ // Индекс позиции курсора в массиве
        int tecText1 = 0;
        int i = 0, l = 0;
        while(i < posCur){
            int tecLength = (int) aText.get(tecText1).get("length");
            
            while(l < tecLength && i < posCur){
                i++;
                l++;
            }
            if(i < posCur){
                tecText1++;                
                l = 0;
            }
            //i++;
        }
        indexText = tecText1;
        posCurInText = l;
    }
    
    public Paragraph PrepareForSave(){
        saveText = new ArrayList();
        for(int i = 0; i < aText.size(); i++){
            saveText.add(aText.get(i).toString());
        }
        return this;
    }
    
    public void AfterLoad(Paragraph dl) throws JSONException{
        aText = new ArrayList();
        for(int i = 0; i < saveText.size(); i++){
            System.out.println(saveText.get(i));
            aText.add(new JSONObject(saveText.get(i)));
            aText.get(i).put("font", new Font((String) aText.get(i).get("fontString"), Font.PLAIN, (int) aText.get(i).get("fontSize")));
        }
    }
    
    private boolean checkChangeFont(JSONObject one, JSONObject second) throws JSONException{
        if((String)one.get("fontString") != (String)second.get("fontString"))
            return true;
        if((int)one.get("fontSize") != (int)second.get("fontSize"))
            return true;
        if((int)one.get("colorR") != (int)second.get("colorR"))
            return true;
        if((int)one.get("colorG") != (int)second.get("colorG"))
            return true;
        if((int)one.get("colorB") != (int)second.get("colorB"))
            return true;
        return false; // изменений нет
    } 
    
}
