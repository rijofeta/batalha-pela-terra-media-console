package jogo;

import batalha.Batalha;
import dto.PersonagemDTO;
import exercito.Exercito;
import conversor.Conversor;
import personagens.bestas.Besta;
import personagens.herois.Heroi;
import seletor_personagens.SeletorPersonagens;

import java.util.List;

public class Jogo {

    private final JogoDialogo dialogo = new JogoDialogo();
    private final SeletorPersonagens seletorHerois = new SeletorPersonagens(SeletorPersonagens.Tipo.HEROI);
    private final SeletorPersonagens seletorBestas = new SeletorPersonagens(SeletorPersonagens.Tipo.BESTA);
    private final Batalha batalha = new Batalha();

    public void start() {
        // Apresentar o jogo
        dialogo.apresentarJogo();
        // Apresentar fase de seleção
        dialogo.apresentarFaseSelecionar();
        // Chamar fase de seleção
        List<PersonagemDTO> heroisSelecionados = seletorHerois.menuSelecaoPersonagens();
        List<PersonagemDTO> bestasSelecionados = seletorBestas.menuSelecaoPersonagens();
        // Converter DTOs para Personagens
        List<Heroi> heroisConv = Conversor.converterPersonagens(Heroi.class, heroisSelecionados);
        List<Besta> bestasConv = Conversor.converterPersonagens(Besta.class, bestasSelecionados);
        // Formar exércitos
        Exercito<Heroi> herois = new Exercito<>();
        Exercito<Besta> bestas = new Exercito<>();
        herois.addAll(heroisConv);
        bestas.addAll(bestasConv);
        // Apresentar fase de batalha
        dialogo.apresentarFaseBatalha();
        // Chamar fase de batalha
        batalha.batalhar(herois, bestas);
    }
}
