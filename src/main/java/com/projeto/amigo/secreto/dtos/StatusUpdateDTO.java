package com.projeto.amigo.secreto.dtos;

import com.projeto.amigo.secreto.enums.StatusSorteio;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusUpdateDTO {
    @NotNull(message = "Status é obrigatório")
    private StatusSorteio status;
}