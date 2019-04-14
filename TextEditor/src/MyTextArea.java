import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import org.json.JSONObject;
import org.json.JSONException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Evgeniy
 */

public class MyTextArea extends JComponent implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener{

    Vector words = new Vector();
    int l = 0;
    String nWord = "";
    int x, y;
    
    
    public MyTextArea() throws JSONException
    {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        this.addKeyListener(this); //где должен быть фокус
        setFocusable(true);
        words.addElement(new Record(0, 0, ""));
        JSONObject document = new JSONObject(); //требует объявить тип
        JSONObject paragraph = new JSONObject();
        JSONObject text = new JSONObject();
        text.put("Text", "Hello world!");
        paragraph.put("Text", text);
        document.put("Paragraph", paragraph);
    }
    @Override
    public void keyTyped(KeyEvent k) {
        String str = String.valueOf(k.getKeyChar());
        //System.out.println(str);
        nWord += str;
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent k) {
            
    }

    @Override
    public void keyReleased(KeyEvent k) {
    
    }
    
    private class Record{
        private int x = 0, y = 0;
        private String word = "";
        
        Record(int xn, int yn, String nWord){
            x = xn;
            y = yn;
            word = nWord;
        }
        
    }
       
    @Override
    public void paint(Graphics g){
        Record nowWord;
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        //g.drawString("hello world", 100, 100);
        for(int i = 0; i < words.size(); i++)
        {               
            nowWord = (Record)words.elementAt(i);
            g.drawString(nowWord.word , nowWord.x, nowWord.y);
        }
        g.drawString(nWord, x, y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        words.addElement(new Record(x, y, nWord));
        x = e.getX();
        y = e.getY();    
        nWord = "";
        repaint();        
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }

    @Override
    public void mouseDragged(MouseEvent e) {
               
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
              
    }


}
