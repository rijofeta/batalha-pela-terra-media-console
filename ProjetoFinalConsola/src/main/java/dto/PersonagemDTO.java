package dto;

import personagens.Personagem;

import java.io.Serializable;

public record PersonagemDTO(
        int id,
        String nome,
        int vida,
        int armadura,
        Class<? extends Personagem> tipoClass,
        boolean unico
) implements Serializable { }
