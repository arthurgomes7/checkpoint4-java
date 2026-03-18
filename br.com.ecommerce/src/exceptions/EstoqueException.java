package exceptions;

public class EstoqueException extends Exception {
    public EstoqueException(String mensagem) {
        System.out.println(mensagem);
    }
}
