package com.lvcaspacifico.folhadepagamento.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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
    public Empregado criarNovoEmpregado(@RequestBody Empregado empregado){
        return empregadoRepository.save(empregado);
    }


    @PutMapping("/empregados/{id}")
    public Empregado substituirEmpregado(@RequestBody Empregado novoEmpregado, @PathVariable Long id){
        
        return empregadoRepository.findById(id).map(empregado -> {
            empregado.setNome(novoEmpregado.getNome());
            empregado.setCargo(novoEmpregado.getCargo());
            return empregadoRepository.save(empregado);
        })
        .orElseGet(() -> {return empregadoRepository.save(novoEmpregado);});
    }

    @DeleteMapping("/empregados/{id}")
    public void deletarEmpregado(@PathVariable Long id){
        empregadoRepository.deleteById(id);
    }
    
    
}
