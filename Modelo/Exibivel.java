package Modelo;
/**
        * Uma interface funciona como uma classe abstrata pura, estabelecendo um "contrato visual"
        * que obriga qualquer classe que a implemente a fornecer o comportamento exigido.
        * Responsávael pela inicialização visual das telas do sistema, como 'SeraVerdadePrincipal', 'TelaNoticia', 'TelaJustificativa' e 'ResultadoPagina'.
        */
public interface Exibivel {
    void configurarLayout();
}
