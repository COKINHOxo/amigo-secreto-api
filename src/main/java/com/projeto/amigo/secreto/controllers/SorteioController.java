package com.projeto.amigo.secreto.controllers;

import com.projeto.amigo.secreto.dtos.SorteioDTO;
import com.projeto.amigo.secreto.service.SorteioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sorteios")
public class SorteioController {
    private final SorteioService sorteioService;

    public SorteioController(SorteioService sorteioService) {
        this.sorteioService = sorteioService;
    }

    @Operation(summary = "Listar todos os sorteios", description = "retorna uma lista com todos os sorteios cadastrados na API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<SorteioDTO>> getAllSorteios(){
        return ResponseEntity.ok(sorteioService.findAll());
    }

    @Operation(summary = "Criar novo sorteio", description = "Cria um novo sorteio, baseando-se nas informações enviadas pela API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sorteio criado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
            @ApiResponse(responseCode = "400", description = "Falha ao criar sorteio")
    })
    @PostMapping
    public ResponseEntity<SorteioDTO> createSorteio(@Valid @RequestBody SorteioDTO dto){
        return ResponseEntity.ok(sorteioService.create(dto));
    }

    @Operation(summary = "Buscar sorteio por ID", description = "Retorna um sorteio com o id selecionado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sorteio encontrado"),
            @ApiResponse(responseCode = "404", description = "Sorteio não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SorteioDTO> getSorteioById(@PathVariable long id){
        return ResponseEntity.ok(sorteioService.findById(id));
    }

    @Operation(summary = "Excluir um sorteio do sistema", description = "Deleta um sorteio do sistema, baseando-se no seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sorteio deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sorteio não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSorteio(@PathVariable Long id){
        sorteioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Finalizar sorteio", description = "Atualiza o status do sorteio para FINALIZADO")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sorteio finalizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sorteio não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<Void> finalizarSorteio(@PathVariable long id){
        sorteioService.finalizarSorteio(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Realizar sorteio automaticamente", description = "Realiza o sorteio entre as pessoas do grupo associado ao sorteio com o ID informado. Cada pessoa é sorteada para outra, garantindo que ninguém se sorteie e que todos participem.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sorteio realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sorteio não encontrado"),
            @ApiResponse(responseCode = "400", description = "Grupo com menos de 2 pessoas ou sorteio já realizado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/{id}/realizar")
    public ResponseEntity<Void> realizarSorteio(@PathVariable long id){
        sorteioService.realizarSorteio(id);
        sorteioService.finalizarSorteio(id);
        return ResponseEntity.noContent().build();
    }
}
