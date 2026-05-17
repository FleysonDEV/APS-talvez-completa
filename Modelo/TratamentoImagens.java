package Modelo;
// exeção personalizada para imagens que falharem, usando o construtor para resgatar o texto de erro
public class TratamentoImagens extends Exception {
    public TratamentoImagens(String mensagem){
        super(mensagem);
    }
}
