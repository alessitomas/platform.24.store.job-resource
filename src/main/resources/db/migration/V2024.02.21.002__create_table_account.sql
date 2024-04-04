CREATE TABLE job
(
    id_job character varying(36) NOT NULL,
    tx_empresa character varying(256) NOT NULL,
    tx_cargo character varying(256) NOT NULL,
    tx_descricao character varying(256) NOT NULL,
    tx_salario float NOT NULL,

    CONSTRAINT job_pkey PRIMARY KEY (id_job)
);


