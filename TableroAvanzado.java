/*
 * Universidad del Valle de Guatemala
 * 21/10/2013
 * Pablo Diaz 13203
 * Adolfo Morales 13269
 * Javier Mérida 13014
 * Clase para hacer el tablero gráfico del nivel avanzado
 * Herede de Tablero.java
 * TableroAvanzado.java
 */



import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JOptionPane;



public class TableroAvanzado extends Tablero{
    
   //el constructor sera el mismo
    public TableroAvanzado(int numeroCasillas, Jugador[] jugador, Casilla[] casilla, int numerojugadores) {
       super(numeroCasillas, jugador, casilla, numerojugadores);
    }
    
    /*
     * metodo que sobreescribe el padre
     * lo que se modifica en este metodo es que se agrega la condicion en caso
     * que la ficha caiga en una casilla que sea cambia tablero.
     */
    
    @Override
    public void parteLogicaJuego(){
        
        //---------------LOGICA DEL JUEGO   
        //condicion para arreglar lo que avanza la ficha en caso que se pase del numero total de casillas
        if((jugador[jugadorActual].getCasillaActual()+saltor)>=numeroCasillas)
        {
            saltor = numeroCasillas - jugador[jugadorActual].getCasillaActual();   //se modifica el salto para que en caso se salga del tablero
                                                                                            //se modifique el salto
        }


        //se muestran los datos al jugador 
        datos1.setText (datos1.getText() + "\nJugador   #"+jugadorActual+"  : " + jugador[jugadorActual].getNombre() + "\n");     
        datos1.setText (datos1.getText() + "  Estaba en: "+ jugador[jugadorActual].getCasillaActual() + "\n");
        datos1.setText (datos1.getText() + "  Dados    : "+ dador1 + "," + dador2 + " = " + (dador1+dador2)+ "\n");
        datos1.setText (datos1.getText() + "  Avanza a : " +(jugador[jugadorActual].getCasillaActual()+saltor)+ "\n");     
        //se mueve la casilla actual del jugador actual a la casilla actual + el resultado de los dados
        
       jugador[jugadorActual].setCasillaActual(jugador[jugadorActual].getCasillaActual()+saltor);             
       //se hace este ciclo para que se mueva la ficha hasta que la ficha caiga en una casilla simple
       //ciclo para mover al jugador hasta que caiga en una casilla simple
       while (true) {
             //hasta cuando cae en una simple se sale del ciclo
              if (casilla[jugador[jugadorActual].getCasillaActual()].getTipoCasilla().equals("Simple") )
               {
                  datos1.setText (datos1.getText() + "  Cae en simple\n");               
                   break;
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
                          datos1.setText(datos1.getText()+ " se mover\u00e1 a" + numeroCasillas + "\n") ;//se le muestra al jugador
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
                          datos1.setText(datos1.getText()+ " se mover\u00e1 a" + 1 + "\n") ;
                      }
                  else{//si no se procede a reducir la casilla en la que esta el jugador
                      jugador[jugadorActual].setCasillaActual(jugador[jugadorActual].getCasillaActual()+ casilla[jugador[jugadorActual].getCasillaActual()].getMovimientoN());
                      datos1.setText(datos1.getText()+ " se mover\u00e1 a " + jugador[jugadorActual].getCasillaActual() + "\n") ;
                      }
                      repaint();//se actualiza la posicion grafica de la ficha
                      continue; //sirve para saltarse una iteracion y volver al inicio del ciclo    
                  }//cierra tipo serpiente
               if (casilla[jugador[jugadorActual].getCasillaActual()].getTipoCasilla().equals("Cambia Tablero")){
                  
                  datos1.setText(datos1.getText() + "cambia tablero\n "); //se le muestra al jugador que el tablero cambio
                  
                  //primero se agrega que todas las casillas son de tipo simple
                  for (int cont = 1; cont<=(int)numeroCasillas;cont++){
                      casilla[cont].setTipoCasilla("Simple");
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
                        casilla[variable_aleatoria].setTipoCasilla("Pierde Turno");
                   }//cierra ciclo for



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
                        casilla[variable_aleatoria].setTipoCasilla("Tira de Nuevo");//ahora si ya se pone la casilla en su lugar
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
                        casilla[variable_aleatoria].setTipoCasilla("Cambia Tablero");//se hacen las casillas de cambia tablero

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
                        casilla[variable_aleatoria2].setTipoCasilla("Escalera");
                        casilla[variable_aleatoria2].setMovimientoN(variable_avanza);


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
                            if (casilla[variable_avanza+variable_aleatoria3].getTipoCasilla().equals("Escalera")){//programacion defensiva para que una serpieente no termine donde empieza una escalera
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
                        casilla[variable_aleatoria3].setTipoCasilla("Serpiente");
                        casilla[variable_aleatoria3].setMovimientoN(variable_avanza);

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
                             if (casilla[variable_avanza+variable_aleatoria4].getTipoCasilla().equals("Serpiente")){//no puede empezar donde hay una serpiente
                                 variable_aleatoria4 = aleatorio.nextInt((int)(numeroCasillas))+1;
                                continue;//se salta una iteracion
                            }
                            //si la posicio del avanza es la primera se cambia la posicion
                            if (variable_aleatoria4==1){
                                variable_aleatoria4 = aleatorio.nextInt((int)(numeroCasillas))+1;
                                continue;
                            } 
                            break; 

                        }while(true);//cierra ciclo avanza N 
                            casilla[variable_aleatoria4].setTipoCasilla("Avanza");
                            casilla[variable_aleatoria4].setMovimientoN(variable_avanza);

                    }//cierra for
                    super.casillaActual=1;
                    super.posX = 0;
                    super.posY = tamanoPanelY-tamanoCasillaY;
                    super.anadirCasillas(filas, columnas);
                    
                    
            } //cierra if cambia tablero          
            
                break;
            }  // while
             //condicion nueva en la que si el jugador saca 1 y 1 en los dados pierde turno
            if (dador1==1&&dador2==1){
                jugador[jugadorActual].setTurnoPerdido(jugador[jugadorActual].getTurnoPerdido()+1);
                datos1.setText(datos1.getText() + "pierde turno: saco 1 y 1"+ "\n");
            }
            
            repaint();//cuando salga del ciclo tambien se tinen que actualizar la posicion grafica de la ficha
                 //si el jugador llego al número máximo de casillas o más, es el jugador ganador
                 //si el jugador llega a la casilla final, gana el juego   
            if ( jugador[jugadorActual].getCasillaActual() == (int) numeroCasillas){
                datos1.setText(datos1.getText() +"\n="
                        + "====\nGANADOR " + " Jugador # "+ jugadorActual + "\n" +jugador[jugadorActual].getNombre());
                JOptionPane.showMessageDialog(null, "\n=====\nGANADOR " + " Jugador # "+ jugadorActual + "\n" +jugador[jugadorActual].getNombre());
                tirarDados.setEnabled(false);//cuando gana el juego ya no se puede seguir tirando
            }
    
    }//cierra metodo logica
    
    
    /*
     * metodo para cambiar las condiciones del control de turnos
     * es necesario para completar la fase 2, ya que en caso de que el jugador
     * saque dado 1 y 1, se le volvera a dar otro turno y tambien en caso que 
     * caiga en la casilla tira de nuevo.
     */
    
    
    public void controlDeTurnosAvanzado(){
        //condicion que indica no aplica el cambio de turno en caso de que que el jugador saque 6 y 6 o le salga tira de nuevo
        if ((dador1==6&&dador2==6)||(casilla[jugador[jugadorActual].getCasillaActual()].getTipoCasilla().equals("Tira de Nuevo"))){
               datos1.setText(datos1.getText() + " Tira de Nuevo\n  " );
        }
        else{
            controlDeTurnos();
        }
            
            
        
    }//cierra metodo control turnos avanzado
    
    /*
     * se sobre escribe el metodo del boton porque cambian algunas condiciones 
     * en el tablero avanzado
     * tipo void, recibe actionevent
     */
    @Override
    public void actionPerformed(ActionEvent evento){
        String accion = evento.getActionCommand();
        if (accion.equals("tirar")){
            music(); //metodo que agregar la musica
            agregarDados();//metodo que cambia los dados
            parteLogicaJuego();//metodo que lleva la logica del juego
            controlDeTurnosAvanzado();//metodo que lleva los turnos de los jugadores
            

        } //cierra if tirar
        if(accion.equals("salir")){
           int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea Salir del juego?");

           if (JOptionPane.OK_OPTION == confirmado)
               System.exit(0);

        }//cierra if salir    
        
     }//cierra metodo action performed
   
}//cierra clase