package insper.store.job;

public class JobParser {

    public static Job to(JobIn in) {
        return Job.builder()
            .empresa(in.empresa())
            .cargo(in.cargo())
            .descricao(in.descricao())
            .salario(in.salario())
            .build();
    }

    public static JobOut to(Job Job) {
        return JobOut.builder()
            .id(Job.id())
            .empresa(Job.empresa())
            .cargo(Job.cargo())
            .descricao(Job.descricao())
            .salario(Job.salario())
            .build();
    }
    
}
