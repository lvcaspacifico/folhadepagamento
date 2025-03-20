package com.lvcaspacifico.folhadepagamento.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvcaspacifico.folhadepagamento.exception.EmpregadoNaoEncontradoException;
import com.lvcaspacifico.folhadepagamento.model.Empregado;
import com.lvcaspacifico.folhadepagamento.model.EmpregadoModelAssembler;
import com.lvcaspacifico.folhadepagamento.repository.EmpregadoRepository;



@RequestMapping("/api/v1")
@RestController
public class EmpregadoController {

    private final EmpregadoRepository empregadoRepository;

    private final EmpregadoModelAssembler empregadoModelAssembler;

    EmpregadoController(EmpregadoRepository empregadoRepository, EmpregadoModelAssembler empregadoModelAssembler){
        this.empregadoRepository = empregadoRepository;
        this.empregadoModelAssembler = empregadoModelAssembler;
    }
    
    @GetMapping("/empregados/{id}")
    public EntityModel<Empregado> obterUmEmpregado(@PathVariable Long id){

        Empregado empregado = empregadoRepository.findById(id)
        .orElseThrow(() -> new EmpregadoNaoEncontradoException(id));

        return empregadoModelAssembler.toModel(empregado);
    }

    @GetMapping("/empregados")
    public CollectionModel<EntityModel<Empregado>> obterTodosOsEmpregados(){

        List<EntityModel<Empregado>> empregados = empregadoRepository.findAll()
                                                .stream()
                                                .map(empregadoModelAssembler::toModel)
                                                .collect(Collectors.toList());
        return CollectionModel.of(empregados, 
                                linkTo(methodOn(EmpregadoController.class)
                                .obterTodosOsEmpregados())
                                .withSelfRel());
    }

    

    @PostMapping("/empregados")
    public ResponseEntity<?> criarNovoEmpregado(@RequestBody Empregado empregado){
        
        EntityModel<Empregado> entityModel = empregadoModelAssembler.toModel(empregadoRepository.save(empregado));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }


    @PutMapping("/empregados/{id}")
    public ResponseEntity<?> substituirEmpregado(@RequestBody Empregado novoEmpregado, @PathVariable Long id){
        
        Empregado empregadoAtualizado = empregadoRepository.findById(id)
        .map(empregado -> {
            empregado.setNome(novoEmpregado.getNome());
            empregado.setCargo(novoEmpregado.getCargo());
            return empregadoRepository.save(empregado);
        })
        .orElseGet(() -> {
            return empregadoRepository.save(novoEmpregado);
        }); 

        EntityModel<Empregado> entityModel = empregadoModelAssembler.toModel(empregadoAtualizado);

        return ResponseEntity
            .created(entityModel
                .getRequiredLink(IanaLinkRelations.SELF)
            .toUri()).body(entityModel);
    }

    @DeleteMapping("/empregados/{id}")
    public ResponseEntity<?> deletarEmpregado(@PathVariable Long id){
        empregadoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    
    
}
