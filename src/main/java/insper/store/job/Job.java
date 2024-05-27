package insper.store.job;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Builder
@Getter @Setter @Accessors(fluent = true, chain = true)
@AllArgsConstructor @NoArgsConstructor
public class Job implements Serializable{

    private static final long serialVersionUID = 1L;
    

    private String id;
    private String empresa;
    private String cargo;
    private String descricao;
    private Float salario;
    
}
