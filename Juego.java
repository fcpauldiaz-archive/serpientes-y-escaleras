/*
 * Universidad del Valle de Guatemala
 * Pablo Díaz
 * Adolfo Morales
 * Javier Merida
 * 13/08/2013
 * Clase principal donde se hacen todas las estructuras logicas
 * Juego.java
 * 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.util.Random;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

public class Juego {
    
    public static void main(String[] args){
       
        //---------PRESENTACION PREVIO AL JUEGO 
        Presentacion presentacion = new Presentacion();
        System.out.println(presentacion.getNivelDificultad());
        
        
        
        //  ------------ INGRESO DATOS GENERALES
            //el usuario decide cuantas casillas quiere en su tablero
        presentacion.pedirDatosUsuario();
        Object numeroCasillas = presentacion.getNumeroCasillas();
        Object numeroJugadores = presentacion.getNumeroJugadores();
              
            
       
         //-------------------CREACIÓN DE ESTRUCTURAS Y TABLERO LÓGICO
        
        Jugador []jugador;//se referencia un arreglo de los jugadores
 
        if ((int)numeroJugadores ==1)//si es contra la computadora se inicializa con tres para  no usar la posicion 0
           jugador = new Jugador[3];//esta condicion sirve para crear tres posiciones en caso que sean dos jugadores
        else
          jugador = new Jugador[(int)numeroJugadores+1];//si no es 1 jugador, se inicializan los jugadores con jugadores +1

        //ciclo para pedir datos de nombres y color de ficha
        for(int cont = 1;cont<=(int)numeroJugadores;cont++){

            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del jugador");
            while(nombre == null)
                nombre =JOptionPane.showInputDialog(null, "Ingrese el nombre del jugador porfavor");
            
            Color colorFicha = JColorChooser.showDialog(null,"Select a Color", Color.RED);//seleccionador de color de ficha
            while (colorFicha == null)
                colorFicha = JColorChooser.showDialog(null,"Select a Color", Color.RED);
            
            jugador[cont] = new Jugador();//se instancia cada objeto
            jugador[cont].setNombre(nombre);//se le dan los atributos
            jugador[cont].setColorFicha(colorFicha);//se le setea el atribuuto
            
        }
        //si es un jugador, es porque la computadora es el otro jugador
         if ((int)numeroJugadores ==1){
             numeroJugadores=2;//para que el juego funcione se le tiene que decir que van haber dos jugadores y solo se ingresa 1 por el usuario
             jugador[2] = new Jugador();
             jugador[2].setNombre("Computadora");
             jugador[2].setColorFicha(Color.BLACK);          
        }//cierra condicion       
        
        //------------------------CREACION DE CASILLAS
        Casilla [] casilla1;//arreglo de clase casilla
        casilla1 = new Casilla[(int)numeroCasillas+1];//se inicializa con el número de casillas +1
      
        //primero se agrega que todas las casillas son de tipo simple
        for (int cont = 1; cont<=(int)numeroCasillas;cont++){
            casilla1[cont] = new Casilla();
            casilla1[cont].setTipoCasilla("Simple");
            
            
            
        }
        
        // se hacen 5 casillas aleatorias de PIERDE TURNO 
        //en el tablero se agregan las casillas
        Random aleatorio = new Random();
        for (int h = 1; h<=5;h++){
            int variable_aleatoria = aleatorio.nextInt((int)(numeroCasillas))+1;//variable de posicion
            while(true){
                if (variable_aleatoria == 1|| variable_aleatoria ==numeroCasillas){//no puede haber una casilla en la primera o última casilla
                    variable_aleatoria = aleatorio.nextInt((int)(numeroCasillas))+1;//se vuelve a cambiar de posicion
                    continue;//sirve para saltarse una iteracion y volver al inicio del ciclo 
                }
                break;//se sale del ciclo
            }
            casilla1[variable_aleatoria].setTipoCasilla("Pierde Turno");
       }//cierra ciclo for
        
        if (presentacion.getNivelDificultad().equals("Nivel avanzado")){
        //se crean las 5 casillas de TIRAR DE NUEVO
        
        for (int a=1;a<=5;a++){
            int variable_aleatoria = aleatorio.nextInt((int)numeroCasillas)+1;//aleatorio de la posicion
            while(true)//ciclo para validar la posicion de las casillas
            {   //no puede haber una casilla especial en la primera ni en la ultima
                if(variable_aleatoria==1||variable_aleatoria==numeroCasillas)
                {
                    variable_aleatoria = aleatorio.nextInt((int)(numeroCasillas))+1;// se vuelve a cambiar de posicion
                    continue;
                }
                break;//si no pasa la condicion, se termina el ciclo while
            }
            casilla1[variable_aleatoria].setTipoCasilla("Tira de Nuevo");//ahora si ya se pone la casilla en su lugar
        }
        //creacion de CASILLAS CAMBIA TABLERO
        for (int i =1;i<=5;i++)
        {
            int variable_aleatoria=aleatorio.nextInt((int)numeroCasillas)+1;
            while(true)//ciclo para validar la posicion de las casillas
            {   //no puede haber una casilla especial en la primera ni en la ultima
                if(variable_aleatoria==1||variable_aleatoria==numeroCasillas)
                {
                    variable_aleatoria = aleatorio.nextInt((int)(numeroCasillas))+1;// se vuelve a cambiar de posicion
                    continue;
                }
                break;//si no pasa la condicion, se termina el ciclo while
            }
            casilla1[variable_aleatoria].setTipoCasilla("Cambia Tablero");//se hacen las casillas de cambia tablero
                
        }
        }
        
        //se hacen cinco casillas aleatorias de escalera
        int variable_avanza;
        for (int k = 1;k<=5;k++){
            int variable_aleatoria2 = aleatorio.nextInt((int)(numeroCasillas))+1;//posicion de donde estara
            
            do {
                    variable_avanza = aleatorio.nextInt(20)+1;
                    //condicion para que lo que avanza la escalera no sea mayor que el numero de casillas
                    if (( variable_aleatoria2 + variable_avanza ) >= (int) numeroCasillas){
                        variable_aleatoria2 = aleatorio.nextInt((int)(numeroCasillas))+1;
                        continue;//sirve para saltarse una iteracion y volver al inicio del ciclo 
                    }
                    //condicion para que la poscion no sea 1
                    if (variable_aleatoria2 == 1){
                        variable_aleatoria2 = aleatorio.nextInt((int)(numeroCasillas))+1;
                        continue;//sirve para saltarse una iteracion y volver al inicio del ciclo 
                    }
                    //condicion para que la posicion final no sea en la casilla finals
                    if((variable_aleatoria2)== numeroCasillas){
                        variable_aleatoria2 = aleatorio.nextInt((int)numeroCasillas)+1;
                        continue;//sirve para saltarse una iteracion y volver al inicio del ciclo 
                    }
                    //condicion para que lo que avanza no este en la misa fila
                    if (variable_avanza <= Math.sqrt((int)numeroCasillas)){
                        continue;//sirve para saltarse una iteracion y volver al inicio del ciclo 
                    }
                    break;//se sale del ciclo
            } while ( true ) ;
            casilla1[variable_aleatoria2].setTipoCasilla("Escalera");
            casilla1[variable_aleatoria2].setMovimientoN(variable_avanza);
           
             
        }//cierra for de escalera
        
        //se hacen cinco casillas aleatorias de serpeinte
        for (int l = 1;l<=5;l++){
            
            int variable_aleatoria3 = aleatorio.nextInt((int)(numeroCasillas))+1;//posicion de donde estara
               
            
            do{
                
                variable_avanza = aleatorio.nextInt(20)+1;//variable del movimiento N
                variable_avanza= -variable_avanza;//el movimiento tiene que ser negativo
                if ((variable_avanza+variable_aleatoria3)<=1){//programación defensiva en caso que la suma sea menor que 1
                    variable_aleatoria3 = aleatorio.nextInt((int)(numeroCasillas))+1;//se crea otra variable de posicion
                    continue;//se salta una iteración
                }    
                if (casilla1[variable_avanza+variable_aleatoria3].getTipoCasilla().equals("Escalera")){//programacion defensiva para que una serpieente no termine donde empieza una escalera
                    variable_aleatoria3 = aleatorio.nextInt((int)(numeroCasillas))+1;//se crea otra variable de posicion
                    continue;//sirve para saltarse una iteracion y volver al inicio del ciclo 
                }
                //condicionpara que lo que avanza no sea mayor que el numero de filas
                if (-variable_avanza <= Math.sqrt((int)numeroCasillas))
                    continue;//sirve para saltarse una iteracion y volver al inicio del ciclo 
               
                if (variable_aleatoria3 == numeroCasillas){
                    variable_aleatoria3 = aleatorio.nextInt((int)numeroCasillas)+1;
                    continue;//sirve para saltarse una iteracion y volver al inicio del ciclo 
                }
                    
                
                break;//se sale del ciclo
            }
            while(true);
            //después de pasar por las correciones, se agregan los valores vállidos
            casilla1[variable_aleatoria3].setTipoCasilla("Serpiente");
            casilla1[variable_aleatoria3].setMovimientoN(variable_avanza);
           
        }//cierra for de serpiente
       
        //se hacen cinco casillas aleatorias de avanza N
        for (int m = 1;m<=5;m++){
            int variable_aleatoria4 = aleatorio.nextInt((int)(numeroCasillas))+1;//variable de posicion
                       
            do{
                variable_avanza = aleatorio.nextInt(5)+1;//variable del atributo avanza
                //mientras la posicion mas lo que avanza sea mayor que el numero de casillas se cambia de posicion
                if ((variable_avanza+variable_aleatoria4) >(int)(numeroCasillas)){
                    variable_aleatoria4 = aleatorio.nextInt((int)(numeroCasillas))+1;
                    continue;//se salta una iteracion
                }
                //codicion para que no pueda haber que no termine un avanza N donde esta una serpiente
                 if (casilla1[variable_avanza+variable_aleatoria4].getTipoCasilla().equals("Serpiente")){//no puede empezar donde hay una serpiente
                     variable_aleatoria4 = aleatorio.nextInt((int)(numeroCasillas))+1;
                    continue;//se salta una iteracion
                }
                //si la posicio del avanza es la primera se cambia la posicion
                if (variable_aleatoria4==1){
                    variable_aleatoria4 = aleatorio.nextInt((int)(numeroCasillas))+1;
                    continue;
                } 
                break; 
                
            }while(true);//cierra avanza N
                casilla1[variable_aleatoria4].setTipoCasilla("Avanza");
                casilla1[variable_aleatoria4].setMovimientoN(variable_avanza);
            
        }//cierra for
        
        
        //------CREACIÓN TABLERO GRAFICO
        {   
        
        JFrame frame = new JFrame();
        //dependiendo de la dificultad elegida por el usuario, dependera el tablero a instanciar
        if (presentacion.getNivelDificultad().equals("Nivel avanzado")){
            TableroAvanzado tablero1 = new TableroAvanzado((int)(numeroCasillas), jugador, casilla1, (int) numeroJugadores);
            frame.getContentPane().add(tablero1, BorderLayout.CENTER);
        }else {
            Tablero tablero1 = new Tablero((int)(numeroCasillas), jugador, casilla1, (int) numeroJugadores);
            frame.getContentPane().add(tablero1, BorderLayout.CENTER);
        }
        Titulo titulo1 = new Titulo();    
        frame.setTitle("Tablero");
        frame.getContentPane().add(titulo1, BorderLayout.NORTH);
        frame.setSize( Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height); 
        //Evitar que se pueda cambiar el tamanio de la ventana
	frame.setResizable(false);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	//Mostrar la ventana
	frame.setVisible(true);
        
        }//cierra frame       
    }//cierra main
}//cierra clase