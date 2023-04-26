import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
   
    JLabel Instrucciones;
    JCheckBox []Tablero = new JCheckBox[9];

    ImageIcon Circ;
    ImageIcon Cros;
    ImageIcon Rect;

    Boolean Player = true;

    GUI(){
        setTitle("Juego del Gato");
        setSize(300,300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            Rect = new ImageIcon(ImageIO.read( new File("check-box-empty.png")));
            Circ = new ImageIcon(ImageIO.read( new File("circle.png")));
            Cros = new ImageIcon(ImageIO.read( new File("close.png")));
        } catch (IOException e) {
            System.out.println("No pude abrir la imagen :(");
        }

        // Label
        Instrucciones = new JLabel();//"Escoge una casilla para iniciar"
        Instrucciones.setText("Escoge una casilla para iniciar: P1");
        Instrucciones.setBounds(10, 10, 250, 40);
        
        //Check box 
        int i,j;
        for(i=0; i<9; i++)
            Tablero[i] = new JCheckBox(Rect);
        for(i=0;i<3; i++){
            for(j=0;j<3; j++){
                Tablero[i*3+j].setBounds(80+40*i,80+40*j,30,30);
                Tablero[i*3+j].addActionListener(new Action(i*3+j));
            }
        }

        add(Instrucciones);
        for(i=0; i<9; i++)
            add(Tablero[i]);
    }

    class Action implements ActionListener{
        int ID;
        Action(int id){
            ID = id;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            if(Player){
                Tablero[ID].setSelectedIcon(Cros);
                Instrucciones.setText(Instrucciones.getText().replace("P1", "P2"));
            }else{
                Tablero[ID].setSelectedIcon(Circ);
                Instrucciones.setText(Instrucciones.getText().replace("P2", "P1"));

            }
            Tablero[ID].setEnabled(false);
            Player = !Player;
        }
    }

}
