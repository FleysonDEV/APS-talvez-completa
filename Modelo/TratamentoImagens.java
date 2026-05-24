package Modelo;

public class TratamentoImagens extends Exception {
   // CONSTRUTOR DA EXCEÇÃO
    public TratamentoImagens(String mensagem){
        super(mensagem);
    }
}
/**
 * REUSO DE COMPORTAMENTO DA SUPERCLASSE ('super'):
 * A palavra-chave 'super' invoca o construtor da classe mãe ('Exception').
 * Isso repassa a mensagem de erro para que os mecanismos nativos do Java (como o método '.getMessage()'
 */