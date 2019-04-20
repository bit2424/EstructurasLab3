package myException;

public class marketNotFoundException extends Exception{
    public marketNotFoundException() {
        super("El  mercado solicitado no existe");
    }
}
