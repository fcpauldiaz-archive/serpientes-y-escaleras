/*
 * Universidad del Valle de Guatemala
 * Pablo Diaz 13203
 * Adolfo Morales 13269
 * Javier Mérida 13014
 * Clase para poner los atributos posibles de una casilla
 * Casilla.java
 */


public class Casilla {
  
    //atributos para la casilla
    private String tipoCasilla;
    private int movimientoN; //es el valor del moviemiento especial
    private int posGraficaX;//en el tablero cada casilla tiene una posicion grafica
    private int posGraficaY;
 
    
  //se empiezan las casillas con valor 0
  public Casilla() {
        this.movimientoN = 0;
        this.posGraficaX = 0;
        this.posGraficaY = 0;
    }
      
 //métodos set para setear un valor void
  //metodo get para obtener un valor
  public int getPosGraficaX() {
        return posGraficaX;
   }

  public void setPosGraficaX(int posGraficaX) {
    this.posGraficaX = posGraficaX;
  }

  public int getPosGraficaY() {
    return posGraficaY;
  }

  public void setPosGraficaY(int posGraficaY) {
    this.posGraficaY = posGraficaY;
 }
    
   
  public String getTipoCasilla() {
      return tipoCasilla;
  }

  public void setTipoCasilla(String tipoCasilla) {
      this.tipoCasilla = tipoCasilla;
  }

  public int getMovimientoN() {
      return movimientoN;
  }

  public void setMovimientoN(int movimientoN) {
     this.movimientoN = movimientoN;
  }
 
}//cierra clase