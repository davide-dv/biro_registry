BEGIN;

CREATE SEQUENCE IF NOT EXISTS company_id_seq;
CREATE SEQUENCE IF NOT EXISTS company_person_id_seq;
CREATE SEQUENCE IF NOT EXISTS person_id_seq;


CREATE TABLE public.company (
    id integer NOT NULL DEFAULT nextval('company_id_seq'::regclass),
    name character varying NOT NULL,
    city character varying NOT NULL,
    vat character varying NOT NULL,
    deleted bool NULL DEFAULT false,
    UNIQUE(vat)
);

CREATE TABLE public.company_person (
    company_id integer NOT NULL,
    person_id integer NOT NULL
);

CREATE TABLE public.person (
    id integer NOT NULL DEFAULT nextval('person_id_seq'::regclass),
    k_id character varying NOT NULL,
    name character varying NOT NULL,
    surname character varying NOT NULL,
    city character varying NOT NULL,
    address character varying NOT NULL,
    deleted bool NULL DEFAULT false,
    UNIQUE(k_id)
);

ALTER TABLE ONLY public.company_person
    ADD CONSTRAINT company_person_pkey PRIMARY KEY (company_id, person_id);

ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.company_person
    ADD CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES public.company(id) NOT VALID;


ALTER TABLE ONLY public.company_person
    ADD CONSTRAINT fk_person_id FOREIGN KEY (person_id) REFERENCES public.person(id) NOT VALID;

END;