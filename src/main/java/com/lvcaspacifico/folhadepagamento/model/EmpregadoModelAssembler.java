package com.lvcaspacifico.folhadepagamento.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import com.lvcaspacifico.folhadepagamento.controller.EmpregadoController;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EmpregadoModelAssembler
implements RepresentationModelAssembler<Empregado, EntityModel<Empregado>>{

    @Override
    public EntityModel<Empregado> toModel(Empregado empregado) {
        
        return EntityModel.of(empregado, 
                            linkTo(methodOn(EmpregadoController.class).obterUmEmpregado(empregado.getId())).withSelfRel(),
                            linkTo(methodOn(EmpregadoController.class).obterTodosOsEmpregados()).withRel("empregados"));
    
    }
}
