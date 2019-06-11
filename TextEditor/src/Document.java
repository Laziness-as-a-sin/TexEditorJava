import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;

public class Document implements Serializable{
    ArrayList<Paragraph> p = new ArrayList();
    ArrayList<JSONObject> cord = new ArrayList();
    int i = 0, posCur = 0, posCurY = 0;
    int cX = 1, cY;
    int mX, mY;
    boolean mouseClick = false;
    boolean cSwitch = false;
    Font font1 = new Font("Arial", Font.PLAIN, 15);
    String fontSring = "Arial";
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
            fontSring = "TimesRoman";
        }
        else{
            fontNum = 0;
            font1 = new Font("Arial", Font.PLAIN, 15);
            fontSring = "Arial";
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
            f.put("fontString", fontSring);
            if(fontNum == 0){
                f.put("fontSize", 15);
                f.put("colorR", 0);
                f.put("colorG", 0);
                f.put("colorB", 0);
            }
            else{
                f.put("fontSize", 12);
                f.put("colorR", 255);
                f.put("colorG", 0);
                f.put("colorB", 0);
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
    
    public void paint(Graphics b, int w, int h) throws JSONException{
        b.setColor(Color.white);
        b.fillRect(0, 0, w, h);
        int xt = 1, yt = 2;
        
        for(int ik = 0; ik < p.size(); ik++){            
            p.get(ik).paint(b, xt, yt);
            yt += p.get(ik).paragraphHeigt();
            if(i == ik){ // Курсор
                b.drawLine(p.get(i).posFromStart(posCur), yt, p.get(i).posFromStart(posCur), yt-12);
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
        }
    }
    
    public void highlightBot() throws JSONException{//maybe input un one function
        if(i < (p.size()-1)){
            i++;
        }
    }
    
    public void moveCurRight() throws JSONException{
        if(posCur < p.get(i).numSymbol){
            posCur++;
            System.out.println("Курсор двинулся right");
            System.out.println(posCur);
            System.out.println("---------------");
        }
    }
    
    public void moveCurLeft() throws JSONException{
        if(posCur >0){
            posCur--;
        }
    }
    
    public void save() throws FileNotFoundException, IOException{
        for (int i = 0; i < p.size(); i++){
            String name = "D://save" + i + ".ser";
            //создаем 2 потока для сериализации объекта и сохранения его в файл
            FileOutputStream outputStream = new FileOutputStream(name);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            // сохраняем игру в файл
            objectOutputStream.writeObject(p.get(i).PrepareForSave());

            //закрываем поток и освобождаем ресурсы
            objectOutputStream.close();
        }
        FileWriter fileWriter = new FileWriter("D://saveStat.ser");
        fileWriter.write(p.size());
        fileWriter.close();
        System.out.println("SAVE");
    }
    
    public void load() throws FileNotFoundException, IOException, ClassNotFoundException, JSONException{
        i = 0;
        posCur = 0;
        FileReader fr = new FileReader("D://saveStat.ser"); 
        int k; 
        k=fr.read();

        p = new ArrayList();
        System.out.println(k);
        for (int i = 0; i < k; i++){
            FileInputStream fileInputStream = new FileInputStream("D://save0.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            p.add((Paragraph) objectInputStream.readObject());
            p.get(i).AfterLoad(p.get(i));
            System.out.println("PRELOAD");
        }
        System.out.println("LOAD");
    }
    
}
