package personagens.bestas;

import personagens.Personagem;

public class Orque extends Besta {

    public Orque(String nome, int vida, int armadura) {
        super(nome, vida, armadura);
    }

    @Override
    public int obterPotenciaOfensivaEspecial(Personagem atacado) {
        // calcular potencia ofensiva base
        int potenciaOfensiva = obterPotenciaOfensivaBase();
        /*
        Eventual lógica especial ....
         */
        return potenciaOfensiva;
    }

    @Override
    public int serAtacado(int potenciaOfensiva, Personagem atacante) {
        /*
        Eventual lógica de defesa...
         */
        // Calcular dano efetivo
        int danoEfetivo = 0;
        if (potenciaOfensiva > this.armadura) {
            danoEfetivo = potenciaOfensiva - this.armadura;
        }
        // Retirar pontos de vida
        this.vida -= danoEfetivo;
        return danoEfetivo;
    }
}
