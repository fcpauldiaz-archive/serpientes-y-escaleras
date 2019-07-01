/*
 * Universidad del Valle de Guatemala
 * Pablo Diaz 13203
 * Adolfo Morales 13269
 * Javier Mérida 13014
 * Clase para hacer el tablero gráfico
 * 3/09/2013
 * Tablero.java
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.Random;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.*;


public class Tablero extends JPanel implements ActionListener{
    
    //atributos del tablero
    protected int tamanoPanelX = 1000 ;//tamano predefinido X del tablero
    protected int tamanoPanelY = 600; //tamano predefinido Y del tablero
    protected int tamanoCasillaX, tamanoCasillaY;//los tamanos de las casillas varian dependiendo del numero de casillas
    protected int posX, posY; //posiciones de cada casilla
    protected int casillaActual = 1;//la casilla actual de cada jugador
    protected int filas, columnas;//el numero de filas y columnas depende 

    //atributos de las clases 
    protected Casilla[] casilla;//se utiliza el arreglo de las casillas creadas en el main
    protected Jugador[] jugador;//se utiliza el arreglo de los jugadores creados en el main
    protected int numerojugadores;//se utiliza el numero de jugadores ingresado por el usuario
    protected int numeroCasillas;//se utiliza el numero de casillas ingresado por el usuario
    protected int dador1, dador2, saltor;//cantidad que sale en cada dado, saltor = dador1 + dador2
    protected int jugadorActual = 1;//jugador actual que esta jugando
    
    //componentes del tablero
    protected JLabel label1; //componente para mostrar graficamente el numero de casillas
    protected JTextArea datos1;// componente para mostrar los datos aclarativos de la ejecucion del juego
    protected JButton tirarDados, salir;//boton para tirar dados y boton para salir del juego
    protected Border  raisedbevel;//componente para poner un borde a los demas componentes
    protected JLabel mostrarD1, mostrarD2;//figura para mostrar el dado1 y el dado2
    protected JTextArea mostrarTurnoActual;//componente para mostar indicar el jugador que va a tirar los dados.
    
    
    //constructor vacío
    public Tablero(){}
    //constructor personalizado
    //para iniciar los valores del tablero
    public Tablero(int numeroCasillas, Jugador[] jugador, Casilla[] casilla, int numerojugadores){
        super();
        this.filas = (int) Math.sqrt (numeroCasillas);
        this.columnas = filas;
        this.tamanoCasillaX = this.tamanoPanelX/filas;
        this.tamanoCasillaY = this.tamanoPanelY/columnas;
        this.posX = 0;
        this.posY = tamanoPanelY-tamanoCasillaY;
        this.numerojugadores = numerojugadores;
        this.numeroCasillas = numeroCasillas;
        this.jugador = jugador;
        setLayout(null);
        setSize(tamanoPanelX,tamanoPanelY);
        this.casilla = casilla;
       
        anadirCasillas(filas,columnas);//se llama al metodo para dibujar las casillas
        anadirDatosAuxiliares();//se llama la metodo para agregar los cuadros aclarativos del juego
        
    }
    /*
     * metodo para dibujar el tablero
     * recibe el numero de filas y columnas segun haya escogido el usuario
     * tipo void 
     */
    public void anadirCasillas(int Filas, int Columnas){
        //ciclo para crear graficamente las casillas, depende de filas y columnas
        for ( int y = 1; y<=Filas;y++){
            for (int x = 1; x<=Columnas;x++){
                label1 = new JLabel(""+ casillaActual);
                label1.setBounds(posX, posY, tamanoCasillaX,tamanoCasillaY);//posicion grafica donde se pondra el jlabel
                Border border = BorderFactory.createLineBorder(Color.gray);
                label1.setOpaque(true);
                
                              
                
                String texto = casilla[casillaActual].getTipoCasilla();
               
                if (!(texto.equals("Simple"))){//si no es tipo simple se le muestra el tipo de casilla y el movimiento
                  
                   texto = " "+texto + " " + casilla[casillaActual].getMovimientoN() + " ";
                   
                    label1.setText(label1.getText() + texto);
                }
                
                if(texto.equals("Escalera")){
                    ImageIcon imagen = new ImageIcon(getClass().getResource("escalera.gif"));
                    JLabel imageLabel = new JLabel(imagen);
                    imageLabel.setBounds(0, 525, 97, 50);
                    label1.add(imageLabel);
                    
                }
                
                //condicion para ponerle color a las casillas impares
                if(Filas%2==0){
                    if (x%2 != 0){
                            label1.setBackground(new Color(0,172,109));
                    }
                }
                else{
                    if (x%2!=0 && y%2 !=0){
                        label1.setBackground(new Color(0,172,109));
                    }
                }//terminan condiciones para colorear las casillas
                                              
                label1.setBorder(border);
                add(label1);
                
                casilla[casillaActual].setPosGraficaX(posX);//en cada casilla se le da su atributo de posicion grafica X y Y
                casilla[casillaActual].setPosGraficaY(posY);
               
               
                //condicion para dibujuar las casillas    
                if (y%2 != 0){//impar
                    posX = posX + tamanoCasillaX;
                    
                }
                else//par
                    posX = posX - tamanoCasillaX;


            //contador de casillas    
            casillaActual++;
            }//cierra for x
            
            //cuando llegue al maximo de casillas por fila, se sube de fila
            posY = posY-tamanoCasillaY;
            //condiciones para ir moviendo las casillas en las columnas
            if (y%2 != 0){
                    posX = posX - tamanoCasillaX;
                }
            else {
                    posX = posX + tamanoCasillaX;
            }
          
        }//cierra for y
        
   }//cierra metodo añadir casillas
    
    /*metodo interno para añadir datos auxiliares del programa
     * no recibe nada ni regresa nada
     */
    public void anadirDatosAuxiliares(){
         //creación de bordes 
       
        //borde a colocar a los cuadros auxiliares para mostrar la información
        raisedbevel = BorderFactory.createRaisedSoftBevelBorder();
        Border border = LineBorder.createGrayLineBorder(); 
        Border redline = BorderFactory.createLineBorder(Color.yellow,1);
        Border compound = BorderFactory.createCompoundBorder(raisedbevel,redline );
          //se crean los componentes y se les pone el borde
        datos1 = new JTextArea(20,14);
        mostrarTurnoActual = new JTextArea(6,2);
        mostrarTurnoActual.setBorder(border);
        mostrarTurnoActual.setBounds(800,650,215,40);
        mostrarTurnoActual.setEditable(false);
        mostrarTurnoActual.setBackground(new Color(70, 151,70));//0,255,179
        mostrarTurnoActual.setForeground(new Color(0,0,0));
        mostrarTurnoActual.setFont(new Font("Arial", Font.BOLD, 14));
        
        
        datos1.setBorder(border);
        
        //se le pone la función de scroll a los text area
        JScrollPane areaScrollPane = new JScrollPane(datos1);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
       
        
        //colores RGB
        datos1.setBackground(new Color(70, 151,70));//0,255,179
        datos1.setForeground(new Color(0,0,0));
        datos1.setFont(new Font("Arial", Font.BOLD, 14));
        datos1.setEditable(false);//El usuario no puede editar el texto mostrado
        
        tirarDados = new JButton();//boton que llevará la lógica del juego
        tirarDados.setIcon(new ImageIcon(getClass().getResource("animaDados3.gif")));
        tirarDados.setBounds(1100, 0, 100, 100);//se pone en un lugar especifico del tablero
        tirarDados.setText("TIRAR");
        tirarDados.setActionCommand("tirar");
        salir = new JButton("Salir");
        salir.setActionCommand("salir");
        tirarDados.setBorder(compound);
        
        tirarDados.setBackground(new Color(97,107,255));
        tirarDados.setForeground(new Color(255,255,255));
        tirarDados.setBounds(1100, 0, 200, 200);//se pone en un lugar especifico del tablero
        areaScrollPane.setBounds(1100,300,200,350);//se pone en un lugar especifico del tablero
        salir.setBackground(new Color(97,107,255));
        salir.setForeground(Color.white);
        
        salir.setBounds(1100,650,200,40);//se pone en un lugar especifico del tablero
        //se anaden al tablero
        add(tirarDados);
        add(areaScrollPane);
        
        //funcion del botón
        tirarDados.addActionListener(this);//botones funcionales
        salir.addActionListener(this);
      
        add(salir);
        mostrarD1 = new JLabel();
        mostrarD2 = new JLabel();
        
        mostrarD1.setBounds(1100, 210,240, 80);//se pone en un lugar especifico del tablero
        mostrarD2.setBounds(1200,210,240,80);//se pone en un lugar especifico del tablero
        //se anaden al tablero
        add(mostrarD1);
        add(mostrarD2);
        add(mostrarTurnoActual);
       
    }
    
    /*
     * metodo para agregar los dados graficos cada vez que tira el usuario
     * tipo void, no regresa nada
     */
    public void agregarDados(){
        Random aleatorio = new Random();
        //imagenes de los dos dados
        ImageIcon dado1 = new ImageIcon(getClass().getResource("dado1.png"));
        ImageIcon dado2 = new ImageIcon(getClass().getResource("dado2.png"));
        ImageIcon dado3 = new ImageIcon(getClass().getResource("dado3.png"));
        ImageIcon dado4 = new ImageIcon(getClass().getResource("dado4.png"));
        ImageIcon dado5 = new ImageIcon(getClass().getResource("dado5.png"));
        ImageIcon dado6 = new ImageIcon(getClass().getResource("dado6.png"));
            
        dador1 = aleatorio.nextInt(6)+1;
        dador2 = aleatorio.nextInt(6)+1;
        saltor = dador1 + dador2; //se gugarda en una variable el resultado del salto
        //condiciones switch para poner el resultado de los dados graficamente
        switch(dador1){
             case 1: mostrarD1.setIcon(dado1);break;
             case 2: mostrarD1.setIcon(dado2);break;
             case 3: mostrarD1.setIcon(dado3);break;
             case 4: mostrarD1.setIcon(dado4);break;
             case 5: mostrarD1.setIcon(dado5);break;
             case 6: mostrarD1.setIcon(dado6);break;
         }

         switch(dador2){
             case 1: mostrarD2.setIcon(dado1);break;
             case 2: mostrarD2.setIcon(dado2);break;
             case 3: mostrarD2.setIcon(dado3);break;
             case 4: mostrarD2.setIcon(dado4);break;
             case 5: mostrarD2.setIcon(dado5);break;
             case 6: mostrarD2.setIcon(dado6);break;
         }
    }
    
    /*
     * metodo para las condiciones de la logica del juego
     * tipo void, no recibe nada
     */
    public void parteLogicaJuego(){
        //---------------LOGICA DEL JUEGO    
        //condicion para arreglar lo que avanza la ficha en caso que se pase del numero total de casillas
        if((jugador[jugadorActual].getCasillaActual()+saltor)>=numeroCasillas)
        {
            saltor = numeroCasillas - jugador[jugadorActual].getCasillaActual();   //se modifica el salto para que en caso se salga del tablero
                                                                                    //se modifique el salto
        }
        //se muestran los datos al jugador en el JText Area
        datos1.setText (datos1.getText() + "\nJugador   #"+jugadorActual+"  : " + jugador[jugadorActual].getNombre() + "\n");     
        datos1.setText (datos1.getText() + "  Estaba en: "+ jugador[jugadorActual].getCasillaActual() + "\n");
        datos1.setText (datos1.getText() + "  Dados    : "+ dador1 + "," + dador2 + " = " + (dador1+dador2)+ "\n");
        datos1.setText (datos1.getText() + "  Avanza a : " +(jugador[jugadorActual].getCasillaActual()+saltor)+ "\n");     
        //se mueve la casilla actual del jugador actual a la casilla actual + el resultado de los dados
        jugador[jugadorActual].setCasillaActual(jugador[jugadorActual].getCasillaActual()+saltor);             
        //se hace este ciclo para que se mueva la ficha hasta que la ficha caiga en una casilla simple
        while (true) {
            
            //hasta cuando cae en una simple se sale del ciclo
            if (casilla[jugador[jugadorActual].getCasillaActual()].getTipoCasilla().equals("Simple") )
            {
                   datos1.setText (datos1.getText() + "  Cae en simple\n");               
                   break;//se sale del ciclo cuando cae en una casilla simple
            }

            //si la posicion en la que esta el jugador es pierde turno, se le suma uno al atributo de turno perido y se le muestra al jugador
            //también se sale del ciclo
            if ( casilla[jugador[jugadorActual].getCasillaActual()].getTipoCasilla().equals( "Pierde Turno") ){   //se le pone un turno perdido al jugador en la lista de turnos perdidos en la misma posicion
                  datos1.setText(datos1.getText() + "  Cae en Pierde Turno " + "\n");
                  jugador[jugadorActual].setTurnoPerdido(jugador[jugadorActual].getTurnoPerdido()+1);
                  break;
            }     


            if ( 
                casilla[jugador[jugadorActual].getCasillaActual()].getTipoCasilla().equals("Escalera")
                ||
                casilla[jugador[jugadorActual].getCasillaActual()].getTipoCasilla().equals("Avanza")
                )//si la casilla en la que cae es escalera o avanza
                {// then if
                     datos1.setText(datos1.getText() + "  Cae en " + casilla[jugador[jugadorActual].getCasillaActual()].getTipoCasilla() + "\n");           
                     //si el valor del movimiento N de la casilla + la casilla actual es mayor que el numero de casillas   
                     if (casilla[jugador[jugadorActual].getCasillaActual()].getMovimientoN()+jugador[jugadorActual].getCasillaActual()>=numeroCasillas){
                            jugador[jugadorActual].setCasillaActual(numeroCasillas);//se pone la ficha en la ultima casilla
                            datos1.setText(datos1.getText()+ " se mover\u00e1 a " + numeroCasillas + "\n") ;//se le muestra al jugador
                        }
                     else{//si no es mayor el resultado, se procede a cambiar la casilla actual hasta que caiga en una casilla simple
                         
                         jugador[jugadorActual].setCasillaActual( casilla[jugador[jugadorActual].getCasillaActual()].getMovimientoN()
                         +jugador[jugadorActual].getCasillaActual());
                         datos1.setText(datos1.getText()+ " se mover\u00e1 a" + jugador[jugadorActual].getCasillaActual() + "\n") ;
                        }

                    repaint(); //se actualiza graficamente la posicion de la ficha
                    continue; //sirve para saltarse una iteracion y volver al inicio del ciclo 
                }  // if

                //concdicion para mover la serpiente
           if(casilla[jugador[jugadorActual].getCasillaActual()].getTipoCasilla().equals("Serpiente")){
                 datos1.setText(datos1.getText() + "  Cae en Serpiente " + "\n");          
                 if(jugador[jugadorActual].getCasillaActual()+casilla[jugador[jugadorActual].getCasillaActual()].getMovimientoN()<=1){
                          jugador[jugadorActual].setCasillaActual(1);//si lo que se mueve la serpiente es menor que uno, se pone la casilla actual del jugador actual como la casilla 1
                          datos1.setText(datos1.getText()+ " se mover\u00e1 a " + 1 + "\n") ;
                  }
                  else{//si no se procede a reducir la casilla en la que esta el jugador
                     jugador[jugadorActual].setCasillaActual(jugador[jugadorActual].getCasillaActual()+ casilla[jugador[jugadorActual].getCasillaActual()].getMovimientoN());
                     datos1.setText(datos1.getText()+ " se mover\u00e1 a " + jugador[jugadorActual].getCasillaActual() + "\n") ;
                     }
                     repaint();//se actualiza la posicion grafica de la ficha
                     continue; //sirve para saltarse una iteracion y volver al inicio del ciclo    
            }//cierra tipo serpiente


            break;
            }  // while  
            repaint();//cuando salga del ciclo tambien se tinen que actualizar la posicion grafica de la ficha
            //si el jugador llego al número máximo de casillas o más, es el jugador ganador
            //si el jugador llega a la casilla final, gana el juego   
            if ( jugador[jugadorActual].getCasillaActual() == (int) numeroCasillas){
                datos1.setText(datos1.getText() +"\n=====\nGANADOR " + " Jugador # "+ jugadorActual + "\n" +jugador[jugadorActual].getNombre());
                JOptionPane.showMessageDialog(null, "\n=====\nGANADOR " + " Jugador # "+ jugadorActual + "\n" +jugador[jugadorActual].getNombre());
                tirarDados.setEnabled(false);//cuando gana el juego ya no se puede seguir tirando
                
            }
    }
    /*
     * metodo para llevar el control de turnos de los jugadores
     * tipo void, no recibe nada
     */
    public void controlDeTurnos(){
           
            //ciclo para manejar los turnos de los jugadores
            while (true)   
            {
              if (jugadorActual < (int) numerojugadores)//si el jugador actual es menor que el numero de jugadores se le pasa el turno al segundo jugador
                  jugadorActual += 1;                     
              else
                  jugadorActual = 1 ;//cuando llega al máximo de jugadores, se vuelve a dar el turno al jugador 1
               //condicion para quitarle un turno perdido al jugador y se salta de jugador
             
             
              
              if (jugador[jugadorActual].getTurnoPerdido()> 0)
                 {
                     jugador[jugadorActual].setTurnoPerdido(jugador[jugadorActual].getTurnoPerdido()-1);//se le quitan un turno perdido
                     datos1.setText (datos1.getText() + "\nJugador   #"+jugadorActual+"  : " + jugador[jugadorActual].getNombre() + "\n");     
                     datos1.setText (datos1.getText() + "  Pasa su turno\n");
                 }
                      //condicion importante para que cuando el jugador sea la computadora, no juegue el usuario
              else if (jugador[jugadorActual].getNombre().equals("Computadora")){
                      agregarDados();
                      parteLogicaJuego();//se llama al metodo principal del juego
                     
                  }  
                      
             else{//si no tiene turno peridido se sale del ciclo

                   //una vez ya sumado el jugador actual se le muestra al usuario el tiro del proximo

                  mostrarTurnoActual.setText(" Le toca tirar a : " + jugador[jugadorActual].getNombre() );
                  
                
                     
                     

                   break;//si no el jugador no tiene un turno perdido se termina su turno
                 }
            } // while true  
    }
    
    /*
     * 
     * metodo el cual le da la accion al boton de tirar dados
     * tipo void, recibe actionevent
     */
   @Override
    public void actionPerformed(ActionEvent evento){
       
       
        String accion = evento.getActionCommand();
        //añadir función del botón tirar dados.
        if (accion.equals("tirar")){
            music();
            agregarDados();
            parteLogicaJuego();
            controlDeTurnos();
       }//cierra if tirar 
       //boton para salr
    if(accion.equals("salir")){
       int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea Salir del juego?");

       if (JOptionPane.OK_OPTION == confirmado)
           System.exit(0);

       }//cierra if salir      
   }//termina metodo de los botones
   
   /*metodo paint para dibujar geometric figures
    * tipo void, recibe graphics g
    */
    @Override
   public void paint(Graphics g){
       super.paint(g);
       Graphics2D g2d = (Graphics2D) g;
       //si son dos jugadores se dibujan dos fichas
       int correcionPosicion=0;
       int correccionNumero=15;
       int correcionPosY=0;
       
       /*
        * ciclo para dibujar las fichas
        * controlado por el numero de jugadores que hay
        */
       for (int p=1;p<=numerojugadores;p++){
           if (p>3){
               correcionPosicion -=30;
               correcionPosY=30;
               
           }
           Object color1 = this.jugador[p].getColorFicha();//se obtiene en color de ficha escogido
           g2d.setColor((Color)color1);//se pinta la ficha
           g2d.fillOval(casilla[jugador[p].getCasillaActual()].getPosGraficaX()+correcionPosicion,
                casilla[jugador[p].getCasillaActual()].getPosGraficaY()+ correcionPosY, 30, 30); //se coloca la ficha en su lugar
           g2d.setColor(Color.white);//color del numero de jugador
           
           g2d.drawString(String.valueOf(p),casilla[jugador[p].getCasillaActual()].getPosGraficaX()+correccionNumero ,
                casilla[jugador[p].getCasillaActual()].getPosGraficaY()+15 + correcionPosY);//se coloca el numero de jugador
           correcionPosicion+=30;//correcion para mover las fichas 30 pixeles en cada iteracion
           correccionNumero +=30;//correcion para mover los numeros de ficha 30 pixeles en cada iteracion
                 
            
         }
        //ciclo para dibujar las serpientes y escaleras
        for ( int y = 1; y<=numeroCasillas;y++){
           //se guarda el tipo de casilla
            String texto = casilla[y].getTipoCasilla();

            if ((texto.equals("Escalera"))){
                 //si es esclaera la imgaen es esclaera
                 ImageIcon imagen = new ImageIcon(getClass().getResource("escalera.gif"));
                 //sirve para hacer tranformaciones 
                 AffineTransform at = new AffineTransform();
                 //forma de colocar la escalera donde terminara la ficha
                 at.translate(casilla[casilla[y].getMovimientoN()+y].getPosGraficaX(),casilla[casilla[y].getMovimientoN()+y].getPosGraficaY());
                 //para voltear la escalera a la casilla que empieza se hacen comparaciones de posiciones graficas para poner el angulo necesario
                 if (casilla[casilla[y].getMovimientoN()+y].getPosGraficaX()<casilla[y].getPosGraficaX()){
                     if (casilla[casilla[y].getMovimientoN()+y].getPosGraficaX()-casilla[y].getPosGraficaX()>375){
                         at.rotate(-Math.toRadians(15));
                     }
                     else
                     at.rotate(-Math.toRadians(45));
                 }
                 //para voltear la escalera a la casilla que empieza se hacen comparaciones de posiciones graficas para poner el angulo necesario
                 if(casilla[casilla[y].getMovimientoN()+y].getPosGraficaX()>casilla[y].getPosGraficaX()){
                    if (casilla[casilla[y].getMovimientoN()+y].getPosGraficaX()-casilla[y].getPosGraficaX()>375){
                        if(casilla[casilla[y].getMovimientoN()+y].getPosGraficaX()-casilla[y].getPosGraficaX()>500){
                            at.rotate(Math.toRadians(90));
                        }
                        else
                            at.rotate(Math.toRadians(75));
                     }
                     else
                     at.rotate(Math.toRadians(45));
                 }
                 //para voltear la escalera a la casilla que empieza se hacen comparaciones de posiciones graficas para poner el angulo necesario
                 if(casilla[casilla[y].getMovimientoN()+y].getPosGraficaX()==casilla[y].getPosGraficaX()){
                      at.rotate(Math.toRadians(10));
                 }

                //sirve para agrandar la escalera
                if (casilla[casilla[y].getMovimientoN()+y].getPosGraficaY()-casilla[y].getPosGraficaY()<2*tamanoCasillaY){
                 at.scale(1,1.2);//n veces los pixeles

                }
                //sirve para agrandar la escalera en el eje y
                if(casilla[casilla[y].getMovimientoN()+y].getPosGraficaY()-casilla[y].getPosGraficaY()>tamanoCasillaY*2)
                   at.scale(1,1.5); //n veces los pixeles
                //sirve para agrandar la escalera en el eje x
                 if(casilla[casilla[y].getMovimientoN()+y].getPosGraficaX()-casilla[y].getPosGraficaX()>tamanoCasillaX*2)
                     at.scale(1,1.7);//n veces los pixeles
                 //finalmente se dibuja la escalera
                 g2d.drawImage(imagen.getImage(), at, null);
               }
            //si dentro el mismo ciclo la casilla es serpiente
            else if (texto.equals("Serpiente")){
                ImageIcon imagen = new ImageIcon(getClass().getResource("serpiente.gif"));
                AffineTransform at2 = new AffineTransform();
                 //antes de hacer las transformaciones de los angulos y pixeles se pone en su lugar correspondiente, es decir,se cambia la posicion de la imagen
                at2.translate((casilla[y].getPosGraficaX()-tamanoCasillaX),(casilla[y].getPosGraficaY()+2*tamanoCasillaY));
                at2.rotate(Math.toRadians(-45));
               if (casilla[casilla[y].getMovimientoN()+y].getPosGraficaX()<casilla[y].getPosGraficaX()){
                     if (casilla[casilla[y].getMovimientoN()+y].getPosGraficaX()-casilla[y].getPosGraficaX()>375){
                         at2.rotate(Math.toRadians(15));
                     }
                     else
                     at2.rotate(Math.toRadians(30));
                 }

                 if(casilla[casilla[y].getMovimientoN()+y].getPosGraficaX()>casilla[y].getPosGraficaX()){
                    if (casilla[casilla[y].getMovimientoN()+y].getPosGraficaX()-casilla[y].getPosGraficaX()>375){
                        if(casilla[casilla[y].getMovimientoN()+y].getPosGraficaX()-casilla[y].getPosGraficaX()>500){
                            at2.rotate(Math.toRadians(-80));
                        }
                        else
                            at2.rotate(Math.toRadians(-60));
                     }
                     else
                     at2.rotate(Math.toRadians(-20));
                 }
                if (casilla[casilla[y].getMovimientoN()+y].getPosGraficaY()-casilla[y].getPosGraficaY()>tamanoCasillaY){
                 at2.scale(1,1.1);//n veces los pixeles

                }
                //sirve para agrandar la escalera
                if(casilla[casilla[y].getMovimientoN()+y].getPosGraficaY()-casilla[y].getPosGraficaY()>tamanoCasillaY*2)
                   at2.scale(1,1.3); //n veces los pixeles
                //sirve para agrandar la escalera
                 if(casilla[casilla[y].getMovimientoN()+y].getPosGraficaX()-casilla[y].getPosGraficaX()>tamanoCasillaX*2)
                     at2.scale(1,1.3);//n veces los pixeles


                //finalmente se dibuja la imagen
                g2d.drawImage(imagen.getImage(), at2, null);
            }//cierra if serpiente
           }//cierra for x
    } //cierra metodo paint
   
    
  /*
   * metodo para agregar musica cuando se tiran los dados
   * tipo void, no regresa ni recibe nada
   */
   public void music()
   {
    try
    {
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(getClass().getResource("dados.wav")));
        clip.start();
    }//cierra try
    catch (Exception exc)
    {
        exc.printStackTrace(System.out);
    }//cierra catch
  }//cierra metodo music
    
    
}//cierra clase
    
