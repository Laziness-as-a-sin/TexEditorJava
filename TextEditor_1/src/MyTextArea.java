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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.*;

public class MyTextArea extends JComponent implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener{

    JSONObject doc = new JSONObject();
    Document d1 = new Document();
    BufferedImage b;
    
    public MyTextArea() throws JSONException, IOException
    {
        this.b = ImageIO.read(new File("src/pic/lov.jpg"));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        this.addKeyListener(this); //где должен быть фокус
        setFocusable(true);
    }
    
    @Override
    public void paint(Graphics g){
        g.drawImage(b, 0, 0, this);
    }
    
    public void buffPaint() throws JSONException{
        d1.paint(b.getGraphics(), this.getWidth(), this.getHeight());
    }
    
    @Override
    public void keyTyped(KeyEvent k) { 


    }

    @Override
    public void keyPressed(KeyEvent k) {
        try {
            int key = k.getKeyCode();
            switch(key) {
                case KeyEvent.VK_UP:
                    d1.highlightTop();
                break;
                case KeyEvent.VK_DOWN:
                    d1.highlightBot();
                break;
                case KeyEvent.VK_LEFT:
                    d1.moveCurLeft();
                break;               
                case KeyEvent.VK_RIGHT:
                    d1.moveCurRight();
                break;
                case KeyEvent.VK_ENTER:
                    d1.add();
                break;
                case KeyEvent.VK_F1:
                    d1.switchFont();
                break;
                case KeyEvent.VK_F5:
                    save();
                break;
                case KeyEvent.VK_F6:
                    load();
                break;
                case KeyEvent.VK_F7:
                    TestSer tes = new TestSer();
                    System.out.println("SAVE");
                    String name = "D://save.ser";
                    //создаем 2 потока для сериализации объекта и сохранения его в файл
                    FileOutputStream outputStream = new FileOutputStream(name);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                    // сохраняем игру в файл
                    objectOutputStream.writeObject(tes);

                        //закрываем поток и освобождаем ресурсы
                    objectOutputStream.close();
                break;
                default:
                    d1.addSymb(k.getKeyChar());
                break;
            }
//            repaint()
        buffPaint();
        repaint();
        } catch (JSONException ex) {
            Logger.getLogger(MyTextArea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyTextArea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyTextArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void save() throws IOException{
        d1.save();
    }
    
    private void load() throws IOException, FileNotFoundException, ClassNotFoundException, JSONException{
        d1.load();
    }
    
    @Override
    public void keyReleased(KeyEvent k) {
    
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        d1.mClick(e.getX(), e.getY());
        //repaint();
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
