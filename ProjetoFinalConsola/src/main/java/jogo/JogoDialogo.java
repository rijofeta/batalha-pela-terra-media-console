package jogo;


import java.io.PrintStream;

public class JogoDialogo {

    private final PrintStream printer = System.out;

    public void apresentarJogo() {
        printer.println("""
                Bem vindos à batalha pela Terra Média!
                
                Nesta batalha, Heróis e Bestas irão enfrentar-se para determinar quem reinará sobre a terra média.
                Cabe a cada um dos jogadores construir o respetivo exército para que se enfrentem numa batalha até à morte.
                """);
    }

    public void apresentarFaseSelecionar() {
        printer.println("""
                Comecem por escolher os integrantes dos vossos exércitos.
                """);
    }

    public void apresentarFaseBatalha() {
        printer.println("""
                A batalha começa agora!
                Empunhem as espadas, aprontem os arcos e flechas, agarrem os escudos!
                Encham o coração de coragem e fogo, pois por esta causa nos honra viver e morrer!
                """
        );
    }
}
