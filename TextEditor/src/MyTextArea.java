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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import org.json.JSONObject;
import org.json.JSONException;

public class MyTextArea extends JComponent implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener{

    JSONObject doc = new JSONObject();
    Document d1 = new Document();
    
    public MyTextArea() throws JSONException
    {
       // doc.put(key, value)
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        this.addKeyListener(this); //где должен быть фокус
        setFocusable(true);
    }
   
    @Override
    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);

        try {
            d1.paint(g);
        } catch (JSONException ex) {
            Logger.getLogger(MyTextArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent k) { 


    }

    @Override
    public void keyPressed(KeyEvent k) {
        int key = k.getKeyCode();        
        switch(key) {
            case KeyEvent.VK_UP:
                d1.highlightTop();
            break;
            case KeyEvent.VK_DOWN:
                d1.highlightBot();
            break;
            case KeyEvent.VK_LEFT:

            break;
            case KeyEvent.VK_RIGHT:

            break;
            case KeyEvent.VK_ENTER:           
                try {
                    d1.add();
                } catch (JSONException ex) {
                    Logger.getLogger(MyTextArea.class.getName()).log(Level.SEVERE, null, ex);
                }            
            break;
            default:
                try {
                    d1.rewritetec(k.getKeyChar());
                } catch (JSONException ex) {
                    Logger.getLogger(MyTextArea.class.getName()).log(Level.SEVERE, null, ex);
                } 
            break;
        }
        repaint();            
    }

    @Override
    public void keyReleased(KeyEvent k) {
    
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        d1.click();
        repaint();
        System.out.println(123);
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
