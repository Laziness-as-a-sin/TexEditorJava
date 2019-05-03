import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import org.json.JSONException;
import org.json.JSONObject;

public class Document {
    ArrayList<Paragraph> p = new ArrayList();
    ArrayList<JSONObject> cord = new ArrayList();
    int i = 0, posCur = 0;
    int cX = 1, cY;
    int mX, mY;
    boolean mouseClick = false;
    
    
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
    
    public void delete(){
        
    }
    
    public void rewritetec(char tec) throws JSONException{
        if(tec == 8){
            if(posCur > 0){                
                p.get(i).deleteElement(posCur);
                posCur--;
            }
            
            if((i != 0) && (p.get(i).length() == 0)){
                p.remove(i);
                i -= 1;
                posCur = p.get(i).length();
            }
        }
        else{
            p.get(i).add(tec, posCur);
            posCur++;
            //cX = p.get(i).posFromStart(posCur);
        }
    }
    
    public void paint(Graphics g) throws JSONException{
        int xt = 1, yt = 2;
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        cX = p.get(i).posFromStart(posCur, g);
        cY = 14+i*12;        
        for(int i = 0; i < p.size(); i++){
            yt += 12;
            p.get(i).paint(g, xt, yt);
        }
        if(mouseClick){
            mouseClick = false;
            while(cX - mX < 0 && posCur < p.get(i).length()){
                posCur++;
                System.out.println(cX - mX);
                cX = p.get(i).posFromStart(posCur, g);                
            }
        }
        
        g.drawLine(cX, cY, cX, cY-12);
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
        if(posCur < p.get(i).length()){
            posCur++;
        }
    }
    
    public void moveCurLeft() throws JSONException{
        if(posCur > 0){
            posCur--;
        }
    }
    
    private void movePosCur() throws JSONException{
        if(posCur > p.get(i).length()){
            posCur = p.get(i).length();
        }
    }
}
