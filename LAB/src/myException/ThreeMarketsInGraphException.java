package myException;

public class ThreeMarketsInGraphException extends Exception {

    public ThreeMarketsInGraphException(){
        super("Solo se permiten tres mercados en la gráfica.");
    }

}
