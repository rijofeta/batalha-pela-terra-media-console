package seletor_personagens;

import dto.PersonagemDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Properties;

import static seletor_personagens.SeletorPersonagens.ENLISTAR;
import static seletor_personagens.SeletorPersonagens.REMOVER;
import static seletor_personagens.SeletorPersonagens.SAIR;

public class SeletorDialogo {

    private static final String DIALOGO_DEFAULT = "src/main/resources/dialogo/dialogo.properties";

    private final PrintStream printer = System.out;
    private final Properties properties = new Properties();

    public SeletorDialogo(String ficheiroDialogo) {
        int tentativas = 2;
        while (tentativas > 0) { // Enquanto houver tentativas, tenta iniciar as propriedades
            tentativas--;
            // Tenta inicializar as propriedades contidas no ficheiro passado
            try (FileInputStream fileInputStream = new FileInputStream(ficheiroDialogo)) {
                properties.load(fileInputStream);
                break;
            } catch (FileNotFoundException e) { // Caso o ficheiro não seja encontrado
                if (tentativas != 0) { // Primeiramente, tenta usar um ficheiro default
                    System.err.println("Ficheiro " + ficheiroDialogo + " não foi encontrado!");
                    System.err.println("O programa irá tentar usar um ficheiro default...");
                    ficheiroDialogo = DIALOGO_DEFAULT;
                } else { // Se não for possível encontrar nenhum ficheiro, termina o programa
                    System.err.println("Não foi possível carregar um ficheiro de diálogo.");
                    System.exit(1);
                }
            } catch (IOException e) { // Caso haja erro na operação I/O
                System.err.println("Não foi possível realizar a operação I/O.");
                System.exit(1);
            }
        }
    }

    public void tipoSelecao() {
        String tipoSelecao = properties.getProperty("tipo_selecao");
        printer.println(tipoSelecao);
    }

    public void selecionarPersonagens() {
        String selecionarPersonagens = properties.getProperty("selecionar_personagens");
        printer.format(selecionarPersonagens, SAIR);
        printer.println();
    }

    public void removerPersonagens() {
        String removerPersonagens = properties.getProperty("remover_personagens");
        printer.format(removerPersonagens, SAIR);
        printer.println();
    }

    public void menuSelecionar() {
        String menuSelecionar = properties.getProperty("menu_selecionar");
        printer.format(menuSelecionar, ENLISTAR, REMOVER, SAIR);
        printer.println();
    }

    public void apresentarPersonagens(List<PersonagemDTO> personagens) {
        for (PersonagemDTO pDTO : personagens) {
            if (pDTO != null) {
                printer.format(
                        "Nome: %-14s Vida: %-4d Armadura: %-3d Tipo: %-8s Unico: %-6s%n",
                        pDTO.nome(), pDTO.vida(), pDTO.armadura(), pDTO.tipoClass().getSimpleName(), pDTO.unico()
                );
            }
        }
        printer.println();
    }

    public void apresentarSelecionados(List<PersonagemDTO> selecionados) {
        String apresentarSelecionados = properties.getProperty("apresentar_selecionados");
        printer.println(apresentarSelecionados);

        for (PersonagemDTO dto : selecionados) {
            printer.format(
                    "Nome: %-14s Vida: %-4d Armadura: %-3d%n",
                    dto.nome(), dto.vida(), dto.armadura()
            );
        }
        printer.println();
    }

    public void print(String message) {
        printer.println(message);
    }
}
