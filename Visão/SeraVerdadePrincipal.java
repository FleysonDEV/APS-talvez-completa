package Visão;
import java.util.Collections;
import Modelo.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SeraVerdadePrincipal extends JFrame implements Exibivel {

    public SeraVerdadePrincipal() {
        configurarLayout();
    }

    @Override
    public void configurarLayout() {
        setTitle("SeraVerdade - Quiz de Notícias");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Garante que o processo seja completamente encerrado quando clicar no "X"
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("SeraVerdade", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        JButton btIniciar = new JButton("Iniciar Jogo");
        JButton btComoJogar = new JButton("Como Jogar");
        JButton btSair = new JButton("Sair");

        Dimension dBotao = new Dimension(150, 40);
        btIniciar.setPreferredSize(dBotao);
        btComoJogar.setPreferredSize(dBotao);
        btSair.setPreferredSize(dBotao);
        painelBotoes.add(btIniciar);
        painelBotoes.add(btComoJogar);
        painelBotoes.add(btSair);
        add(painelBotoes, BorderLayout.CENTER);
        //banco de dados do quiz, responsável por armazenar as justificativa, imagens(notícias), explicações e resposta da notícias(verdadeiro ou falso).
        btIniciar.addActionListener(e -> {
            ArrayList<QuestaoQuiz> bancoManchetes = new ArrayList<>();


            ArrayList<String> just1 = new ArrayList<>();
            just1.add("Linguagem Tendenciosa.");
            just1.add("Números Tendenciosos.");
            just1.add("Omissão de Contexto.");
            just1.add("Transparência sobre Riscos.");
            just1.add("Evidência de Dados Oficiais.");
            just1.add("Alerta de Segurança em Larga Escala.");

            bancoManchetes.add(new QuestaoQuiz(false, "noticia 1.jpg", just1, "Este post é enganoso porque distorce as conclusões de um estudo científico real para criar pânico. Embora a pesquisa com 99 milhões de pessoas exista, os autores reafirmam que as vacinas são seguras e que os benefícios superam vastamente os riscos. O post manipula dados ao exibir porcentagens altas sem mencionar que os eventos são extremamente raros, ocorrendo em poucos casos por milhão. \n" +
                    "Além disso, a ciência comprova que o risco de complicações cardíacas ou neurológicas é muito maior após a infecção pelo vírus do que após a vacinação. O estudo buscou apenas monitorar sinais de segurança já conhecidos, mas o post retira esses dados de contexto e omite a conclusão principal dos cientistas para induzir o público ao erro.\n"));


            ArrayList<String> just2 = new ArrayList<>();
            just2.add("Falsa Autoridade");
            just2.add("Linguagem Tendenciosa");
            just2.add("Ausência de Recomendações Oficiais");
            just2.add("Fonte Confiavel");
            just2.add("Evidência de Dados Oficiais ");
            just2.add("Transparência Científica ");

            bancoManchetes.add(new QuestaoQuiz(false, "noticia 2.jpg", just2, "Este post é enganoso porque usa um estudo preliminar e não revisado para afirmar falsamente que a vacina da gripe é ineficaz. A pesquisa analisou apenas um grupo restrito de profissionais de saúde saudáveis e os próprios autores esclareceram que os resultados não permitem concluir que o imunizante não funciona. O post ignora que a eficácia da vacina varia conforme a temporada e, principalmente, omite que ela é altamente eficaz na prevenção de hospitalizações e mortes, dados que sequer foram avaliados nesse estudo. Ao generalizar um resultado específico e incompleto, o conteúdo retira a informação de contexto para invalidar uma estratégia de saúde segura e comprovada."));


            ArrayList<String> just3 = new ArrayList<>();
            just3.add("Fontes Não Científicas");
            just3.add("Linguagem Tendenciosa");
            just3.add("Teoria da Conspiração");
            just3.add("Direito à Autonomia Individual");
            just3.add("Autores Conhecidos");
            just3.add("Transparência Científica");

            bancoManchetes.add(new QuestaoQuiz(false, "noticia 3.jpg", just3, "Este texto é falso porque nega fatos históricos e científicos amplamente comprovados. A afirmação de que não há evidências de que a vacinação erradicou a varíola e a poliomielite é mentirosa, visto que a varíola foi declarada erradicada pela OMS em 1980 exclusivamente devido à vacinação global. O texto utiliza uma linguagem alarmista e ideológica para deslegitimar décadas de dados epidemiológicos que mostram a queda drástica de casos imediatamente após o início das campanhas de imunização. Ao tratar sucessos médicos indiscutíveis como \"mitos\", o conteúdo manipula a opinião pública e ignora o consenso científico que garante a segurança e eficácia dessas vacinas."));


            ArrayList<String> just4 = new ArrayList<>();
            just4.add("Fontes Anônimas");
            just4.add("Linguagem Tendenciosa");
            just4.add("Teoria da Conspiração");
            just4.add("Ausência de Recomendações Oficiais");
            just4.add("Relato de Profissional da Linha de Frente");
            just4.add("Padrão de Sintomas Específico");

            bancoManchetes.add(new QuestaoQuiz(false, "noticia 4.jpg", just4, "Esta notícia é falsa porque se trata de uma mensagem fabricada para gerar pânico, utilizando dados inexistentes e teorias da conspiração. Não há qualquer registro oficial nas secretarias de saúde do Rio de Janeiro que confirme o aumento de mortalidade citado; ao contrário, os dados reais da Fiocruz de 2026 mostram que o foco de doenças respiratórias está em crianças, e não em idosos. O texto é uma \"fake news zumbi\", ou seja, uma reciclagem de boatos de anos anteriores que apenas altera os nomes das cidades para parecer atual. Além disso, a tentativa de ligar a vacina à Agenda 2030 da ONU é uma distorção sem base na realidade, já que esse plano foca em metas de desenvolvimento sustentável e não em controle populacional. Ao usar fontes anônimas e números inventados, a mensagem tenta invalidar uma campanha de saúde segura que, historicamente, reduz mortes e hospitalizações no período de inverno."));

            ArrayList<String> just5 = new ArrayList<>();
            just5.add("Confusão entre Obrigação e Adesão");
            just5.add("Omissão de Contexto");
            just5.add("Falsa Comparação");
            just5.add("Confiança na Imunidade Natural");
            just5.add("Foco em Saúde Holística");


            bancoManchetes.add(new QuestaoQuiz(false, "noticia 5.jpg", just5, "Este post é falso porque estabelece uma relação mentirosa de causa e efeito. Embora o Japão tenha mudado sua lei de vacinação em 1994 (tornando-as fortemente recomendadas em vez de obrigatórias por lei), as taxas de vacinação no país continuaram altíssimas. A baixa mortalidade infantil japonesa deve-se ao seu sistema de saúde universal, excelente pré-natal e saneamento, e não à falta de vacinas. Na verdade, a redução da obrigatoriedade causou surtos de doenças eliminadas, como o sarampo, provando o contrário do que o post afirma."));

            ArrayList<String> just6 = new ArrayList<>();
            just6.add("Diminuição da Carga Tóxica Acumulada");
            just6.add("Linguagem Tendenciosa");
            just6.add("Apelo ao Medo");
            just6.add("Relato de Profissional da Linha de Frente");
            just6.add("Evidência de Dados Oficiais ");
            just6.add("Transparência Científica");

            bancoManchetes.add(new QuestaoQuiz(false, "noticia 6.jpg", just6, "Esta mensagem é falsa porque confunde a proteína Spike do vírus selvagem com a proteína Spike controlada das vacinas. Enquanto o vírus usa a proteína para invadir órgãos e se multiplicar, a versão contida nas vacinas é modificada para ser estável e inofensiva, servindo apenas como um \"molde\" para o sistema imunológico aprender a se defender. Estudos científicos comprovam que essa proteína não circula livremente de forma tóxica pelo corpo; ela permanece localizada no local da aplicação e é rapidamente destruída pelo organismo após estimular a produção de anticorpos. Alegações de que ela causaria infertilidade ou danos cerebrais são mitos sem qualquer base na realidade, uma vez que os riscos de complicações graves são, na verdade, muito maiores para quem contrai a doença do que para quem se vacina."));

            ArrayList<String> just7 = new ArrayList<>();
            just7.add("Direito à Informação ");
            just7.add("Uso de Imagem Manipuladora");
            just7.add("Apelo ao Medo");
            just7.add("Linguagem Tendenciosa");
            just7.add("Omissão dos riscos reais");
            just7.add("Proteína tóxica");

            bancoManchetes.add(new QuestaoQuiz( false, "noticia 7.jpg", just7, "A alegação de que as vacinas de RNA mensageiro alteram o DNA é biologicamente impossível. O DNA humano fica protegido dentro do núcleo da célula, enquanto o RNA da vacina atua apenas no citoplasma, sem nunca entrar no núcleo ou ter contato com o nosso código genético.\n" +
                    "\n" +
                    "Estudos nas revistas Nature e New England Journal of Medicine confirmam que o RNA funciona apenas como um manual de instruções temporário: ele ensina o corpo a produzir uma proteína de defesa e é degradado e eliminado pelo organismo em poucas horas. Como o corpo humano não possui as enzimas necessárias para transformar RNA em DNA, não há risco de integração ao genoma. Portanto, a vacina é uma tecnologia transitória que não deixa rastros permanentes na identidade genética do indivíduo.\n"));

            ArrayList<String> just8 = new ArrayList<>();
            just8.add("Sites Não Confiáveis");
            just8.add("Linguagem Tendenciosa ");
            just8.add("Relatos de Casos Reais ");
            just8.add("Impacto na Fertilidade Futura ");
            just8.add("Omissão dos riscos reais");
            just8.add("Proteína tóxica");

            bancoManchetes.add(new QuestaoQuiz( false, "noticia 8.jpg", just8, "Esta publicação é um exemplo clássico de desinformação baseada em má interpretação de dados científicos. O post utiliza nomes de pesquisadores reais para dar uma falsa aura de autoridade a uma conclusão que o estudo original não sustenta. Na verdade, os grandes estudos globais com centenas de milhares de gestantes demonstram que as taxas de aborto espontâneo entre vacinadas são idênticas às taxas basais da população geral, enquanto o risco de complicações graves pela Covid-19 na gravidez é muito real.\n" +
                    "\n"));

            ArrayList<String> just9 = new ArrayList<>();
            just9.add("Apelo ao medo");
            just9.add("Descontextualização Profissional");
            just9.add("Falta de Dados a Longo Prazo");
            just9.add("Dano Cardíaco");
            just9.add("Denúncia de um Especialista");
            just9.add("Proteção Ineficaz");

            bancoManchetes.add(new QuestaoQuiz(false, "noticia 9.jpg", just9, "A afirmação é falsa porque distorce a proporção dos riscos para gerar pânico. Embora a miocardite seja um efeito colateral raríssimo das vacinas (cerca de 1 caso em 100 mil), o risco de desenvolver essa mesma inflamação cardíaca pela infecção do vírus é oito vezes maior e muito mais grave.\n" +
                    "\n" +
                    "Estudos publicados no JAMA confirmam que os casos pós-vacina são, em sua maioria, leves e de rápida recuperação. Em contraste, a miocardite causada pela Covid-19 em jovens costuma gerar quadros severos e hospitalizações. Portanto, a vacinação não é um \"experimento perigoso\", mas a forma mais segura de proteger o coração dos adolescentes contra os danos reais e superiores causados pela doença.\n"));

            ArrayList<String> just10 = new ArrayList<>();
            just10.add("Apelo ao medo");
            just10.add("Descontextualização Profissional");
            just10.add("Tecnologia de Desintoxicação");
            just10.add("Evidência Hospitalar Direta");
            just10.add("Denúncia de um Especialista");
            just10.add("Teoria da Conspiração ");

            bancoManchetes.add(new QuestaoQuiz( false, "noticia 10.jpg", just10, "O post usa o Hospital Edogawa como cenário para dar credibilidade a uma história inventada. As máquinas mostradas são para tratar cânceres convencionais com tecnologia de ponta, e não para reverter efeitos de vacinas. O termo \"turbo câncer\" é uma ferramenta de marketing do medo, sem qualquer código correspondente no CID (Classificação Internacional de Doenças).\n" ));

            ArrayList<String> just11 = new ArrayList<>();
            just11.add("Contexto Histórico");
            just11.add("Ausência de Sensacionalismo");
            just11.add("Tecnologia de Desintoxicação");
            just11.add("Teoria da Conspiração");
            just11.add("Invasividade");
            just11.add("Transparência de Metodologia ");

            bancoManchetes.add(new QuestaoQuiz(true, "noticia 11.jpg", just11, "A notícia do portal Metrópoles é verdadeira, baseada em um estudo da revista Pediatrics de fevereiro de 2026. A pesquisa confirma que a quantidade de alumínio nas vacinas acumulada até os 18 anos é mínima, segura e não causa autismo ou doenças neurológicas. O mineral atua como um adjuvante para fortalecer a imunidade, sendo que a exposição diária via alimentos e leite materno é superior à dose vacinal."));

            ArrayList<String> just12 = new ArrayList<>();
            just12.add("Fonte Governamental Oficial");
            just12.add("Transparência de Parcerias");
            just12.add("Viés Político/Institucional");
            just12.add("Insinuação de Incentivo Sexual");
            just12.add("Invasividade");
            just12.add("Manipulação Estatística");

            bancoManchetes.add(new QuestaoQuiz( true, "noticia 12.jpg", just12, "A notícia divulgada pela EBC é verdadeira. O pronunciamento do Ministro da Saúde, Alexandre Padilha, baseia-se em um estudo real da Fiocruz, apoiado pela Royal Society e pelo CNPq, que comprova a eficácia da vacina disponível no SUS. Os dados científicos confirmam que a imunização contra o HPV reduz drasticamente os casos de câncer de colo de útero e de lesões pré-cancerosas graves, alinhando-se ao consenso médico global sobre a segurança e a importância da vacina."));

            ArrayList<String> just13 = new ArrayList<>();
            just13.add("Abordagem Científical");
            just13.add("Transparência de Parcerias");
            just13.add("Ausência de Sensacionalismo");
            just13.add("Preconceito Institucional");
            just13.add("Experimento Humano");
            just13.add("Ocultação de Dados");

            bancoManchetes.add(new QuestaoQuiz(true, "noticia 13.jpg", just13, "A notícia sobre a pesquisa da vacina contra a sífilis é verdadeira. O texto detalha um estudo clínico internacional real, coordenado pela University of North Carolina (UNC) em cinco países. No Brasil, a investigação científica legítima está sendo conduzida por instituições oficiais de saúde e pesquisa, a Clínica do Homem do Recife e o Hospital Universitário Oswaldo Cruz (UPE), contando com o apoio técnico da organização global Aids Healthcare Foundation (AHF) para mapear a bactéria Treponema pallidum em estágios iniciais.\n"));

            ArrayList<String> just14 = new ArrayList<>();
            just14.add("Abordagem Científical");
            just14.add("Termo Científico 'experimental'");
            just14.add("Falta de Dados a Longo Prazo");
            just14.add("Omissão de Contexto");
            just14.add("Lucro Exagerado");
            just14.add("Imunidade Espontânea");

            bancoManchetes.add(new QuestaoQuiz(true, "noticia 14.jpg", just14, "A notícia sobre as vacinas de mRNA contra o câncer é verdadeira. Ao contrário dos imunizantes tradicionais que previnem infecções, essa tecnologia é um tratamento terapêutico experimental: ela usa o sequenciamento genético do tumor do próprio paciente para criar uma vacina personalizada que ensina o sistema imunológico a destruir as células cancerígenas. Os testes clínicos para melanoma e câncer de pâncreas são reais e promissores, e o texto mantém o rigor científico ao reforçar que a terapia ainda não é de uso geral."));

            ArrayList<String> just15 = new ArrayList<>();
            just15.add("Termo Científico");
            just15.add("Ausência de Soluções Mágicas");
            just15.add("Público-Alvo Bem Delimitado");
            just15.add("Estrutura Informativa");
            just15.add("Substituição Química");
            just15.add("Mapeamento de DNA");

            bancoManchetes.add(new QuestaoQuiz(true, "noticia 15.jpg", just15, "A notícia sobre a Calixcoca é verdadeira. Como vacina terapêutica experimental desenvolvida pela UFMG, ela estimula a produção de anticorpos que se ligam à cocaína no sangue, impedindo a molécula de chegar ao cérebro e gerar efeitos psicoativos. O texto mantém o rigor científico ao destacar que a pesquisa está em fase pré-clínica (testes em laboratório e animais) e serve como ferramenta de apoio para evitar recaídas em pacientes já em abstinência, sem prometer curas milagrosas."));

            ArrayList<String> just16 = new ArrayList<>();
            just16.add("Termo Científico");
            just16.add("Ausência de Soluções Mágicas");
            just16.add("Ataque às células saudáveis");
            just16.add("Tempo de espera");
            just16.add("Falta de dados de eficácia imediatos");
            just16.add("Fraude institucional");

            bancoManchetes.add(new QuestaoQuiz(true, "noticia 16.jpg", just16, "A notícia sobre a vacina experimental LungVax é verdadeira. O texto relata de forma factual o planejamento de um estudo clínico de Fase 1 no Reino Unido, coordenado pela University of Oxford e UCL, para avaliar a segurança e a dosagem de um imunizante voltado a prevenir o câncer de pulmão em grupos de alto risco. A veracidade é respaldada pela indicação correta do mecanismo biológico, treinar o sistema imune contra células anormais, pela menção a fundos e entidades de financiamento legítimas, como a Cancer Research UK, e pela ausência de promessas sensacionalistas de cura."));

            ArrayList<String> just17 = new ArrayList<>();
            just17.add("Destinação ao SUS");
            just17.add("Legenda contextualizada com base em dados de mortalidade");
            just17.add("Coerência entre o título e o corpo do texto");
            just17.add("Manipulação do número de casos");
            just17.add("Falta de dados de eficácia imediatos");
            just17.add("Fraude institucional");

            bancoManchetes.add(new QuestaoQuiz( true, "noticia 17.jpg", just17, "A notícia sobre a incorporação da vacina contra o VSR no SUS é verdadeira. O texto relata uma decisão factual do Ministério da Saúde baseada em critérios epidemiológicos exatos, apontando corretamente que o Vírus Sincicial Respiratório é responsável pela grande maioria dos casos de bronquiolite e que a mortalidade da doença afeta principalmente menores de 2 anos. A veracidade é reforçada pela precisão técnica ao citar paralelamente a adoção do anticorpo monoclonal nirsevimabe e ao delimitar de forma rigorosa o público-alvo de alto risco para essa tecnologia, composto por bebês prematuros e crianças com comorbidades."));

            ArrayList<String> just18 = new ArrayList<>();
            just18.add("Terminologia científica");
            just18.add("Reconhecimento de limitações do estudo");
            just18.add("Manipulação de dados");
            just18.add("Fraude institucional");
            just18.add("Falta de dados de eficácia imediatos");
            just18.add("Controle populacional por vetores");

            bancoManchetes.add(new QuestaoQuiz( true, "saveiro.jfif", just18, "A notícia sobre a vacina contra a doença de Lyme é verdadeira. O texto relata dados factuais de um ensaio clínico com 70% de eficiência desenvolvido pela Pfizer e Valneva, mantendo o rigor científico ao expor as limitações do estudo, como o número de casos menor que o previsto. A veracidade é respaldada pela presença de créditos jornalísticos e fotográficos auditáveis (AP), indicação precisa de data e fuso horário, além da exibição visual correta do carrapato, vetor real de transmissão da doença."));

            ArrayList<String> just19 = new ArrayList<>();
            just19.add("Saúde pública real");
            just19.add("Tom informativo e neutro");
            just19.add("Coerência com a realidade epidemiológica");
            just19.add("Reforço ineficaz");
            just19.add("Fonte não confiável");
            just19.add("Manipulação por indução ao compartilhamento");

            bancoManchetes.add(new QuestaoQuiz( true, "noticia 19.jpg", just19, "A notícia sobre a recomendação de reforço vacinal é verdadeira. O texto aborda um cenário real de utilidade pública e orientação clínica, tratando de forma neutra e puramente informativa o tempo necessário de espera após a infecção por Covid ou gripe para a aplicação do imunizante. A publicação demonstra rigor formal por meio de uma formatação estruturada, ausência de erros ortográficos, uso de botões de compartilhamento padrão de portais jornalísticos e uma fotografia perfeitamente contextualizada acompanhada de legenda e créditos institucionais legítimos.\n" ));

            ArrayList<String> just20 = new ArrayList<>();
            just20.add("Fontes Anônimas");
            just20.add("Linguagem Tendenciosa");
            just20.add("Teoria da Conspiração");
            just20.add("Ausência de Recomendações Oficiais");
            just20.add("Relato de Profissional da Linha de Frente");
            just20.add("Padrão de Sintomas Específico");

            bancoManchetes.add(new QuestaoQuiz(true, "noticia 20.jpg", just20, "a"));

            //Método responsável por gerar as perguntas aleatoriamente

            Collections.shuffle(bancoManchetes);

            // bloco que limita a quantidade do quiz em apenas 5 rodadas.
            int limite = Math.min(bancoManchetes.size(), 4);
            ArrayList<QuestaoQuiz> selecionadas = new ArrayList<>(bancoManchetes.subList(0, limite));


            new TelaNoticia(1, 0.0, selecionadas).setVisible(true);
            this.dispose();
        });

        btComoJogar.addActionListener(e -> {
            String regras = "REGRAS DO JOGO \n\n" +
                    "1. Cada round possui uma notícia aleatória.\n" +
                    "2. Após responder, você deve escolher as justificativas.\n\n" +
                    " SISTEMA DE PONTOS \n" +
                    "• Acertou e Justificou: 2,0 pts\n" +
                    "• Errou e Justificou: 1,5 pts\n" +
                    "• Acertou (Sem Justificar): 1,0 pt\n" +
                    "• Errou (Sem Justificar): 0,0 pts";
            JOptionPane.showMessageDialog(this, regras, "Como Jogar", JOptionPane.INFORMATION_MESSAGE);
        });

        btSair.addActionListener(e -> System.exit(0));
    }
}