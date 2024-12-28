package seletor_personagens;

import dto.PersonagemDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SeletorPersonagens {
    // Variáveis definem os comandos da fase de seleção.
    static final String ENLISTAR = "E"; // Enlistar personagens
    static final String REMOVER = "R"; // Remover personagens
    static final String SAIR = "S"; // Sair do método corrente

    private final Scanner scanner = new Scanner(System.in);
    private final SeletorDialogo dialogo;

    private final List<PersonagemDTO> disponiveis;
    private final List<PersonagemDTO> selecionados;

    public SeletorPersonagens(Tipo tipo) {
        this.disponiveis = CarregadorPersonagens.carregarPersonagens(tipo.ficheiroSer);
        this.selecionados = new ArrayList<>();
        this.dialogo = new SeletorDialogo(tipo.ficheiroDialogo);
    }

    public enum Tipo {
        HEROI(
                "src/main/resources/personagens_ser/herois.ser",
                "src/main/resources/dialogo/dialogo_heroi.properties"
        ),
        BESTA(
                "src/main/resources/personagens_ser/bestas.ser",
                "src/main/resources/dialogo/dialogo_besta.properties"
        );

        private final String ficheiroSer;
        private final String ficheiroDialogo;

        Tipo(String ficheiroSer, String ficheiroDialogo) {
            this.ficheiroSer = ficheiroSer;
            this.ficheiroDialogo = ficheiroDialogo;
        }
    }

    public List<PersonagemDTO> menuSelecaoPersonagens() {
        dialogo.tipoSelecao();

        while (true) {
            dialogo.menuSelecionar();
            String input = scanner.nextLine();
            if (input.equals(ENLISTAR)) {
                dialogo.selecionarPersonagens();
                selecionarPersonagens();
            } else if (input.equals(REMOVER)) {
                dialogo.removerPersonagens();
                removerPersonagens();
            } else if (input.equals(SAIR)) {
                break;
            } else {
                dialogo.print("Input desconhecido!");
            }
        }
        dialogo.apresentarSelecionados(selecionados);
        return selecionados;
    }

    private void selecionarPersonagens() {
        while (true) {
            // Apresentar personagens disponiveis
            dialogo.apresentarPersonagens(disponiveis);

            String input = scanner.nextLine();
            if (input.equals(SAIR)) return;
            PersonagemDTO personagem;
            for (int i = 0; i < disponiveis.size(); i++) {
                personagem = disponiveis.get(i);
                if (personagem != null && input.equals(personagem.nome())) {
                    selecionados.add(personagem);
                    dialogo.print(personagem.nome() + " foi recrutado!");
                    if (personagem.unico()) disponiveis.set(i, null);
                    return;
                }
            }
            dialogo.print("O nome " + input + " não é válido!");
        }
    }

    private void removerPersonagens() {
        while (true) {
            // Apresentar personagens selecionadas
            dialogo.apresentarPersonagens(selecionados);

            String input = scanner.nextLine();
            if (input.equals(SAIR)) return;
            PersonagemDTO personagem;
            for (int i = 0; i < selecionados.size(); i++) {
                personagem = selecionados.get(i);
                if (input.equals(personagem.nome())) {
                    selecionados.remove(i);
                    dialogo.print(personagem.nome() + " foi removido da seleção!");
                    if (personagem.unico()) disponiveis.set(personagem.id() - 1, personagem);
                    return;
                }
            }
            dialogo.print("O nome " + input + " não é válido!");
        }
    }
}
