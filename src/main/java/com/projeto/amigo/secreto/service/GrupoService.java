package com.projeto.amigo.secreto.service;

import com.projeto.amigo.secreto.dtos.GrupoDTO;
import com.projeto.amigo.secreto.entities.Grupo;
import com.projeto.amigo.secreto.repositories.GrupoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {
    private final GrupoRepository grupoRepository;

    public GrupoService(GrupoRepository repository) {
        this.grupoRepository = repository;
    }

    public GrupoDTO create(GrupoDTO dto){
        Grupo grupo = dto.mapToEntitie();
        grupo.setDataCriacao(LocalDate.now());
        grupo.setSorteado(false);
        grupoRepository.save(grupo);
        return grupo.mapToDto();
    }

    public List<GrupoDTO> findAll(){
        return grupoRepository.findAll().stream().map(Grupo::mapToDto).toList();
    }

    public GrupoDTO findById(Long id){
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));

        return grupo.mapToDto();
    }

    public void delete(Long id){
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));
        grupoRepository.delete(grupo);
    }

    public GrupoDTO update(GrupoDTO dto, Long id){
        Optional<Grupo> optionalGrupo = grupoRepository.findById(id);

        if(optionalGrupo.isEmpty()){
            throw new RuntimeException("Grupo não encontrado");
        }

        Grupo grupo = optionalGrupo.get();
        grupo.updateGrupo(dto.getNome(), dto.getSorteado());
        grupoRepository.save(grupo);

        return grupo.mapToDto();
    }

    public List<GrupoDTO> findGruposSorteados(){
        return grupoRepository.findBySorteado(true).stream().map(Grupo::mapToDto).toList();
    }

}
