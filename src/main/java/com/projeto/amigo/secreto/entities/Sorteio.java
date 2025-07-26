package com.projeto.amigo.secreto.entities;

import com.projeto.amigo.secreto.dtos.SorteioDTO;
import com.projeto.amigo.secreto.enums.StatusSorteio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sorteio {
    @Id @GeneratedValue
    long id;

    @ManyToOne
    private Grupo grupo;

    LocalDateTime dataSorteio;

    @OneToMany(mappedBy = "sorteio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResultadoSorteio> resultados;

    @Enumerated(EnumType.STRING)
    private StatusSorteio status;

    public SorteioDTO mapToDto(){
        SorteioDTO dto = new SorteioDTO();
        dto.setId(this.getId());
        if (this.getGrupo() != null){
            dto.setGrupoId(this.getGrupo().getId());
        }
        dto.setStatus(this.getStatus());
        dto.setDataSorteio(this.getDataSorteio());
        return dto;
    }
}
