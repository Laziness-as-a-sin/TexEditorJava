import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;

public class Document {
    ArrayList<Paragraph> p = new ArrayList();
    ArrayList<JSONObject> cord = new ArrayList();
    int i = 0, posCur = 0;
    int cX = 1, cY;
    int mX, mY;
    boolean mouseClick = false;
    boolean cSwitch = false;
    Font font1 = new Font("Arial", Font.PLAIN, 15);
    int fontNum = 0;
    
    private static final long serialVersionUID = 1L;
    
    public Document() throws JSONException{
        Paragraph zero = new Paragraph();
        p.add(zero);
    }
    
    public void add() throws JSONException{
        i++;
        Paragraph newParagraph = new Paragraph();
        p.add(i, newParagraph);
        posCur = 0;
        cX = 1;
    }
    
    public void switchFont(){
        if(cSwitch == false)
            cSwitch = true;
        else
            cSwitch = false;
        if(fontNum == 0){
            fontNum = 1;
            font1 = new Font("TimesRoman", Font.PLAIN, 12);
            //System.out.println("switchFont 12"+cSwitch);
        }
        else{
            fontNum = 0;
            font1 = new Font("Arial", Font.PLAIN, 15);
            //System.out.println("switchFont 15"+cSwitch);
        }
        
    }
    
    public void addSymb(char tecSymb) throws JSONException{
        if(cSwitch == true){
            cSwitch = false;
            String str = ""+tecSymb;
            JSONObject f = new JSONObject();
            f.put("type", 0); //0-text, 1-picture
            f.put("text", str);
            f.put("font", font1);
            if(fontNum == 0){
                f.put("fontSize", 15);
                f.put("color", Color.black);
            }
            else{
                f.put("fontSize", 12);
                f.put("color", Color.red);
            }           
            
            f.put("width", 0);
            f.put("length", 1);
            
            p.get(i).addText(f, posCur);
            System.out.println("Switch Font");
            moveCurRight();
        }
        else{           
            p.get(i).addSymbol(tecSymb, posCur);
            moveCurRight();
        }
    }
    
    public void rewritetec(char tec) throws JSONException{
//        if(tec == 8){
//            if(posCur > 0){                
//                p.get(i).deleteElement(posCur);
//                posCur--;
//            }
//            
//            if((i != 0) && (p.get(i).length() == 0)){
//                p.remove(i);
//                i -= 1;
//                posCur = p.get(i).length();
//            }
//        }
//        else{
//            p.get(i).add(tec, posCur);
//            posCur++;
//            //cX = p.get(i).posFromStart(posCur);
//        }
    }
    
    public void paint(Graphics g) throws JSONException{
        int xt = 1, yt = 2;
        
        for(int ik = 0; ik < p.size(); ik++){            
            p.get(ik).paint(g, xt, yt);
            yt += p.get(ik).paragraphHeigt();
            if(i == ik){
                g.drawLine(p.get(ik).posFromStart(posCur)+1, yt, p.get(ik).posFromStart(posCur)+1, yt-12);
            }
        }
    }
    
    public void mClick(int x, int y){
        mouseClick = true;
        mX = x;
        mY = y;
        int nowI = mY/12;
        if(nowI > p.size()-1){
            i = p.size()-1;
        }
        else{
            i = nowI;
        }
        posCur = 0;
    }
    
    public void highlightTop() throws JSONException{//maybe input un one function
        if(i > 0){
            i--;
            movePosCur();
        }
    }
    
    public void highlightBot() throws JSONException{//maybe input un one function
        if(i < (p.size()-1)){
            i++;
            movePosCur();
        }
    }
    
    public void moveCurRight() throws JSONException{
        if(posCur < p.get(i).numSymbol){
            posCur++;
            System.out.println("Курсор двинулся");
            System.out.println(posCur);
            System.out.println("---------------");
        }
    }
    
    public void moveCurLeft() throws JSONException{
        if(posCur >0){
            posCur--;
        }
    }
    
    private void movePosCur() throws JSONException{
        if(posCur > p.get(i).numSymbol){
            posCur = p.get(i).numSymbol;
        }
    }
}
