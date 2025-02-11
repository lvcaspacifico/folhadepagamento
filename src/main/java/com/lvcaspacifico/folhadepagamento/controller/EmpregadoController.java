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
import com.lvcaspacifico.folhadepagamento.repository.EmpregadoRepository;



@RequestMapping("/api/v1")
@RestController
public class EmpregadoController {

    private final EmpregadoRepository empregadoRepository;

    EmpregadoController(EmpregadoRepository empregadoRepository){
        this.empregadoRepository = empregadoRepository;
    }
    
    @GetMapping("/empregados/{id}")
    EntityModel<Empregado> obterUmEmpregado(@PathVariable Long id){

        Empregado empregado = empregadoRepository.findById(id)
        .orElseThrow(() -> new EmpregadoNaoEncontradoException(id));

        return EntityModel.of(empregado,
                              linkTo(methodOn(EmpregadoController.class).obterUmEmpregado(id)).withSelfRel(),
                              linkTo(methodOn(EmpregadoController.class).obterTodosOsEmpregados()).withRel("empregados"));

    }

    @GetMapping("/empregados")
    CollectionModel<EntityModel<Empregado>> obterTodosOsEmpregados(){

        List<EntityModel<Empregado>> empregados = empregadoRepository.findAll()
                                                                     .stream()
                                                                     .map(empregado -> EntityModel.of(empregado,
                                                                          linkTo(methodOn(EmpregadoController.class)
                                                                          .obterUmEmpregado(empregado.getId()))
                                                                          .withSelfRel(),

                                                                          linkTo(methodOn(EmpregadoController.class)
                                                                          .obterTodosOsEmpregados())
                                                                          .withRel("empregados")))
                                                                          .collect(Collectors.toList());

        return CollectionModel.of(empregados, 
                                linkTo(methodOn(EmpregadoController.class).obterTodosOsEmpregados()).withSelfRel());
    }

    

    @PostMapping("/empregados")
    Empregado criarNovoEmpregado(@RequestBody Empregado empregado){
        return empregadoRepository.save(empregado);
    }


    @PutMapping("/empregados/{id}")
    Empregado substituirEmpregado(@RequestBody Empregado novoEmpregado, @PathVariable Long id){
        
        return empregadoRepository.findById(id).map(empregado -> {
            empregado.setNome(novoEmpregado.getNome());
            empregado.setCargo(novoEmpregado.getCargo());
            return empregadoRepository.save(empregado);
        })
        .orElseGet(() -> {return empregadoRepository.save(novoEmpregado);});
    }

    @DeleteMapping("/empregados/{id}")
    void deletarEmpregado(@PathVariable Long id){
        empregadoRepository.deleteById(id);
    }
    
    
}
