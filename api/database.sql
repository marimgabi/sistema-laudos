CREATE DATABASE logic WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';

CREATE SCHEMA public;

CREATE TABLE public.conselho (
    id integer NOT NULL,
    tipo character varying(255) NOT NULL,
    numero character varying(255) NOT NULL,
    id_estado integer,
    data_inclusao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    data_alteracao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    status character varying(255) NOT NULL,
    CONSTRAINT conselho_status_check CHECK (((status)::bpchar = ANY (ARRAY['A'::bpchar, 'I'::bpchar])))
);

CREATE SEQUENCE public.conselho_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.estado (
    id integer NOT NULL,
    nome character varying(255),
    sigla character varying(2),
    data_inclusao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    data_alteracao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    status character varying(255) NOT NULL,
    CONSTRAINT estado_status_check CHECK (((status)::bpchar = ANY (ARRAY['A'::bpchar, 'I'::bpchar])))
);

CREATE SEQUENCE public.estado_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.laudo (
    id integer NOT NULL,
    data_laudo timestamp(6) without time zone,
    nome_paciente character varying(255),
    sexo character varying(255),
    data_nascimento timestamp(6) without time zone,
    id_medico_solicitante integer,
    id_medico_executante integer,
    conteudo text,
    data_inclusao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    data_alteracao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    status character varying(255) NOT NULL,
    CONSTRAINT laudo_sexo_check CHECK (((sexo)::bpchar = ANY (ARRAY['M'::bpchar, 'F'::bpchar, 'O'::bpchar]))),
    CONSTRAINT laudo_status_check CHECK (((status)::bpchar = ANY (ARRAY['A'::bpchar, 'I'::bpchar])))
);

CREATE SEQUENCE public.laudo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.medico (
    id integer NOT NULL,
    nome character varying(255) NOT NULL,
    tipo character varying(255),
    id_user integer,
    id_conselho integer,
    data_inclusao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    data_alteracao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    status character varying(255) NOT NULL,
    CONSTRAINT medico_status_check CHECK (((status)::bpchar = ANY (ARRAY['A'::bpchar, 'I'::bpchar])))
);

CREATE SEQUENCE public.medico_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.medico_template (
    id_medico integer NOT NULL,
    id_template integer NOT NULL
);

CREATE TABLE public.role (
    id integer NOT NULL,
    role character varying(255) NOT NULL,
    descricao character varying(255)
);

CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.template (
    id integer NOT NULL,
    descricao character varying(255),
    conteudo text,
    tipo character varying(255),
    data_inclusao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    data_alteracao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    status character varying(255) NOT NULL,
    CONSTRAINT template_status_check CHECK (((status)::bpchar = ANY (ARRAY['A'::bpchar, 'I'::bpchar])))
);

CREATE SEQUENCE public.template_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public."user" (
    id integer NOT NULL,
    email character varying(255) NOT NULL,
    username character varying(100) NOT NULL,
    password character varying(255) NOT NULL,
    data_inclusao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    data_alteracao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    status character(1) NOT NULL,
    id_role integer,
    CONSTRAINT user_status_check CHECK ((status = ANY (ARRAY['A'::bpchar, 'I'::bpchar])))
);

CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;