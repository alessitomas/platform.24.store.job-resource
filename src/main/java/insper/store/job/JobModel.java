package insper.store.job;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "job")
@EqualsAndHashCode(of = "id")
@Builder @Getter @Setter @Accessors(chain = true, fluent = true)
@NoArgsConstructor @AllArgsConstructor
public class JobModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_job")
    private String id;

    @Column(name = "tx_empresa")
    private String empresa;

    @Column(name = "tx_cargo")
    private String cargo;

    @Column(name = "tx_descricao")
    private String descricao;

    @Column(name = "tx_salario")
    private Float salario;

    public JobModel(Job o) {
        this.id = o.id();
        this.empresa = o.empresa();
        this.cargo = o.cargo();
        this.descricao = o.descricao();
        this.salario = o.salario();
    }
    
    public Job to() {
        return Job.builder()
            .id(id)
            .empresa(empresa)
            .cargo(cargo)
            .descricao(descricao)
            .salario(salario)
            .build();
    }
    
}
