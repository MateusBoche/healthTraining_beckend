package br.fai.backend.heathtraining.beckend.healthtraining.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GamePointsDto {
    private int id; // String front
    private String status;
    private int nivelAtual;
    private int usuarioID;
    private int numeroAcertos;
    private int numeroErros;
    private int pontuacao;
    private String dataDeCriacao;
}
