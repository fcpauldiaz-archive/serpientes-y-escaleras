/*
 * Universidad del Valle de Guatemala
 * 20/10/2013
 *Pablo Diaz
 *Adolfo Morales
 *Javier Merida
 *Clase para pedir datos iniciales y nivel de dificultad al usuario
 *Presentacion.java
 */
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Presentacion{

    //atributos que el usuario tiene que ingresar
    private String nivelDificultad;
    private Object numeroJugadores;
    private Object numeroCasillas;
    
    //constructor
    public Presentacion(){
        ImageIcon imagen = new ImageIcon(getClass().getResource("presentacion.png"));
        JLabel presentacion = new JLabel(imagen);
        presentacion.setSize(500, 500);
        presentacion.setVisible(true);
        Object seleccion = JOptionPane.showInputDialog(null,"Elija un dificultad","Selector de dificultad",
        JOptionPane.QUESTION_MESSAGE,imagen,  // null para icono defecto
        new Object[] { "Nivel facil", "Nivel avanzado" },"Nivel avanzado");
        while(seleccion == null){
            JOptionPane.showMessageDialog(null,"Por favor escoja una dificultad");
             seleccion = JOptionPane.showInputDialog(null,"Elija un dificultad","Selector de dificultad",
        JOptionPane.QUESTION_MESSAGE,imagen,  // null para icono defecto
        new Object[] { "Nivel facil", "Nivel avanzado" },"Nivel avanzado");
            
        }
        this.nivelDificultad= seleccion.toString();
    }
    //metodos set y get de los atributoss
    public String getNivelDificultad() {
        return nivelDificultad;
    }
    public Object getNumeroCasillas() {
        return numeroCasillas;
    }
    public Object getNumeroJugadores() {
        return numeroJugadores;
    }    
    /*
     * metodo para pedir el numero de casillas y jugadores al usuario
     * tipo void, no regresa nada ni recibe nada
     */
    public void pedirDatosUsuario(){
        //condicion si escoge el nivel facil
        if (nivelDificultad.equals("Nivel facil"))
        {
             (this.numeroCasillas) = JOptionPane.showInputDialog(null, "Seleccione el n\u00famero de Casillas",   ""
             + "Casillas",   JOptionPane.QUESTION_MESSAGE,null,  // null para icono defecto
            new Object[] {25,36, 49, 64 },64);//valor por default de 64

            while(numeroCasillas == null){//ciclo para forzar al usuario a que ingrese los datos
                JOptionPane.showMessageDialog(null,"Por favor escoja un numero de casillas");
                numeroCasillas = JOptionPane.showInputDialog(null, "Seleccione el n\u00famero de Casillas",  
                        "Casillas",   JOptionPane.QUESTION_MESSAGE,null,  // null para icono defecto
                new Object[] {25,36, 49, 64 },64);//valor por default de 64
            }
        }//cierra if nivel facil  
        
        //condicion si escoge el nivel dificil
        if (nivelDificultad.equals("Nivel avanzado"))
        {
             numeroCasillas = JOptionPane.showInputDialog(null, "Seleccione el n\u00famero de Casillas",   
                     "Casillas",   JOptionPane.QUESTION_MESSAGE,null,  // null para icono defecto
            new Object[] {81,100, 121},81);

            while(numeroCasillas == null){//ciclo para forzar al usuario a que ingrese los datos
                JOptionPane.showMessageDialog(null,"Por favor escoja un numero de casillas");
                numeroCasillas = JOptionPane.showInputDialog(null, "Seleccione el n\u00famero de Casillas",  
                        "Casillas",   JOptionPane.QUESTION_MESSAGE,null,  // null para icono defecto
                new Object[] {81,100, 121},81);
            }
        }//cierra if nivel avanzado
               
        //el usuario selecciona el número de jugadores
        numeroJugadores = JOptionPane.showInputDialog(null, "Seleccione el n\u00famero de jugadores",   
                "Jugadores",   JOptionPane.QUESTION_MESSAGE,null,  // null para icono defecto
        new Object[] { "contra computadora", 2, 3,4 },   "");
        while(numeroJugadores == null){//programacion defensiva, ciclo para forzar al usuario a que ingrese los datos
            JOptionPane.showMessageDialog(null,"Por favor escoja un numero de jugadores");
            numeroJugadores = JOptionPane.showInputDialog(null, "Seleccione el n\u00famero de jugadores",   
                    "Jugadores",   JOptionPane.QUESTION_MESSAGE,null,  // null para icono defecto
            new Object[] { "contra computadora", 2, 3,4 },   "");
        }
        //si es contra la computadora entonces solo tiene que haber un ingreso de datos
        if (numeroJugadores.equals("contra computadora")){
            numeroJugadores = 1;
        }
    }//cierra metodo de pedir datos
}//cierra clases