package com.scripta.scripta_api.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class EmprestimoDTO {
    
    @NotNull
    private Long usuarioID;

    @NotNull
    private Long livroID;

}