/*script tablas bd utacaltuBD  utacaltuBD */
/* VERSION 1 */ 
CREATE TABLE MODELOS(
MOD_CODIGO SERIAL PRIMARY KEY NOT NULL,
MOD_COD_MOD VARCHAR(20) UNIQUE,
MOD_NOMBRE VARCHAR(20) UNIQUE,
MOD_PIEZAS INTEGER
);

CREATE TABLE TROQUELES(
TRQ_CODIGO SERIAL PRIMARY KEY NOT NULL,
TRQ_NOMBRE VARCHAR(20) UNIQUE
);

CREATE TABLE TALLAS(
TAL_CODIGO SERIAL PRIMARY KEY NOT NULL,
TAL_NUMERO INTEGER UNIQUE
);

CREATE TABLE TIPO_PROCESOS(
TPR_CODIGO SERIAL PRIMARY KEY NOT NULL,
TPR_NOMBRE VARCHAR(50) UNIQUE
);

CREATE TABLE T_TALLAS(
TRQ_CODIGO INTEGER,
TAL_CODIGO INTEGER,
CANTIDAD INTEGER,
PRIMARY KEY (TRQ_CODIGO,TAL_CODIGO)
);

CREATE TABLE PROCESOS(
PRO_CODIGO SERIAL PRIMARY KEY NOT NULL,
TPR_CODIGO INTEGER NOT NULL,
PRO_PADRE INTEGER,
PRO_ACTIVO BOOLEAN,
PRO_DESCRIP VARCHAR(50)
);

CREATE TABLE CONFPROCESO(
CONFPRO_CODIGO SERIAL PRIMARY KEY NOT NULL,
MOD_CODIGO INTEGER NOT NULL,
PRO_CODIGO INTEGER NOT NULL,
CODIGO_TIPLINEA INTEGER,
SUB_PRO INTEGER NOT NULL,
TIEMPO_TS DOUBLE PRECISION
);

CREATE TABLE COSTOSINDI(
COSTOS_CODIGO SERIAL PRIMARY KEY NOT NULL,
PRO_CODIGO INTEGER NOT NULL,
ORDENPROD_CODIGO INTEGER NOT NULL,
CIFREAL DOUBLE PRECISION,
CIFPRESU DOUBLE PRECISION,
MANOBRAREAL DOUBLE PRECISION,
MANOBRAPRESU DOUBLE PRECISION
);

CREATE TABLE MOD_TRQ_TAL
(
MTT_CODIGO SERIAL PRIMARY KEY NOT NULL,
TAL_CODIGO INTEGER NOT NULL,
TRQ_CODIGO INTEGER NOT NULL,
MOD_CODIGO INTEGER NOT NULL,
DISPONIBILIDAD VARCHAR(1) NOT NULL
);

/* VERSION 2 */ 
CREATE TABLE LUGARES(
LUGAR_CODIGO SERIAL PRIMARY KEY NOT NULL,
NOMLUGAR VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE CLIENTES(
COD_CLIENTE SERIAL PRIMARY KEY NOT NULL,
NOMBRECLI VARCHAR(20),
APELLIDOCLI VARCHAR(20),
TELEFONO VARCHAR(10),
DIRECCION VARCHAR(50),
DESCRIPCIONCLI VARCHAR(50) 
);

CREATE TABLE MAQUINAS(
MAQ_CODIGO SERIAL PRIMARY KEY NOT NULL,
LINEAPRO_CODIGO INTEGER NOT NULL,
NOMMAQUINA VARCHAR(20),
MARCA VARCHAR(20),
DESCMAQ VARCHAR(50)
);

CREATE TABLE TURNOS(
TURNO_CODIGO SERIAL PRIMARY KEY NOT NULL,
NOMBTURNO VARCHAR(20) UNIQUE,
H_INICIO TIME NOT NULL,
H_FIN TIME NOT NULL,
TURNO_DESC VARCHAR(50)
);

CREATE TABLE TIP_LINEA(
CODIGO_TIPLINEA SERIAL PRIMARY KEY NOT NULL,
TIPOLINEA VARCHAR(10) NOT NULL,
DESCTIPLINEA VARCHAR(50)
);

CREATE TABLE LINEASPROD(
LINEAPRO_CODIGO SERIAL PRIMARY KEY NOT NULL,
CODIGO_TIPLINEA INTEGER NOT NULL,
PRO_CODIGO INTEGER NOT NULL,
NOMLINEA VARCHAR(20) UNIQUE NOT NULL,
LINEA_NUMTRAB INTEGER,
LINEA_DESC VARCHAR(50)
);

CREATE TABLE LINEASTURNOS(
LTCODIGO SERIAL PRIMARY KEY NOT NULL,
CAP_CODIGO INTEGER NOT NULL,
LINEAPRO_CODIGO INTEGER NOT NULL,
TURNO_CODIGO INTEGER NOT NULL
);

CREATE TABLE ORDENPROD(
ORDENPROD_CODIGO SERIAL PRIMARY KEY NOT NULL,
USER_ID_SOLI INTEGER NOT NULL,
USER_ID_RESP INTEGER NOT NULL,
LUGAR_CODIGO_DEST INTEGER,
F_ACTUAL DATE,
F_ESTIM DATE,
F_FINAL DATE
);

CREATE TABLE DETALLEORDEN(
DETAORDEN_CODIGO SERIAL PRIMARY KEY NOT NULL,
ORDENPROD_CODIGO INTEGER NOT NULL,
MOD_CODIGO INTEGER NOT NULL,
TAL_CODIGO INTEGER NOT NULL,
CANTIDAD INTEGER
);

CREATE TABLE PROGRAMTURNOS(
PROGRAM_CODIGO SERIAL NOT NULL PRIMARY KEY,
PROCESSOP_COD INTEGER NOT NULL,
PROGDIAS_CODIGO INTEGER NOT NULL,
TAL_CODIGO INTEGER NOT NULL,
MOD_CODIGO INTEGER NOT NULL,
TURNO_CODIGO INTEGER NOT NULL,
DIA CHARACTER VARYING(1),
F_INICIO DATE,
CANT_ESTIM INTEGER,
CANT_REAL INTEGER DEFAULT 0,
F_FINAL DATE,
HORA TIMESTAMP WITHOUT TIME ZONE,
ESTADO_TUR CHARACTER VARYING(10) DEFAULT 'PENDIENTE'::CHARACTER VARYING,
NO_PLAN CHARACTER(1),
PROGRAM_FINALIZADA BOOLEAN
);

CREATE TABLE PROGRAMDIAS(
PROGDIAS_CODIGO SERIAL PRIMARY KEY NOT NULL,
CAP_CODIGO INTEGER NOT NULL,
CANTPARES INTEGER,
CANTHORAS FLOAT,
FINICIO DATE,
FFIN DATE
);

CREATE TABLE PROCESOSOP(
PROCESSOP_COD SERIAL PRIMARY KEY NOT NULL,
PRO_CODIGO INTEGER NOT NULL,
ORDENPROD_CODIGO INTEGER NOT NULL,
LUGAR_CODIGO_ORIG INTEGER,
LUGAR_CODIGO_DEST INTEGER,
USER_ID_RESP INTEGER,
PARAM_CODIGO INTEGER,
F_ESTIM DATE,
F_ACTUAL DATE
);

CREATE TABLE DISPOSITIVOS(
ID_DISPOSITIVO SERIAL PRIMARY KEY NOT NULL,
MAC_DISPOSITIVO CHARACTER VARYING(20),
PRO_CODIGO_REF INTEGER NOT NULL,
ID_MAQUINAS_REF INTEGER NOT NULL,
NOMBRE_DIS CHARACTER VARYING(20)
);

CREATE TABLE ALERTAS(
ALERTA_CODIGO SERIAL PRIMARY KEY NOT NULL,
DESCRIPCION VARCHAR(20) NOT NULL,
COLOR VARCHAR(20) NOT NULL,
ACTIVO CHAR(1) NOT NULL
);

CREATE TABLE PROC_ALERTA(
PROC_ALE_CODIGO SERIAL PRIMARY KEY NOT NULL,
PRO_CODIGO INTEGER NOT NULL,
ALERTA_CODIGO INTEGER NOT NULL,
FECHA DATE,
OBSERVACION VARCHAR(100),
estado_codigo integer DEFAULT 1,
time_llegada character varying(10),
time_solucion character varying(10),
id_dispositivo integer
);

CREATE TABLE ESTADOS_ALT(
ESTADO_CODIGO SERIAL PRIMARY KEY NOT NULL,
ESTADO VARCHAR(20) NOT NULL
);

/* VERSION 3 */
CREATE TABLE DISTRIBDETALLE(
DISTRIB_CODIGO SERIAL PRIMARY KEY NOT NULL,
DETAORDEN_CODIGO INTEGER,
PRO_CODIGO INTEGER,
CODIGO_TIPLINEA INTEGER
);

CREATE TABLE PARAMETROS(
PARAM_CODIGO SERIAL PRIMARY KEY NOT NULL,
ORDENPROD_CODIGO INTEGER,
PRO_CODIGO INTEGER,
CODIGO_TIPLINEA INTEGER,
STANDAR INTEGER
);

CREATE TABLE EMPRESA(
EMP_CODIGO SERIAL PRIMARY KEY NOT NULL,
EMP_NOMBRE VARCHAR(50),
EMP_DIRECC VARCHAR(100),
EMP_TELF VARCHAR(10),
EMP_LOGO BYTEA
);

CREATE TABLE LOGOSFAPS(
IDLOGOS SERIAL PRIMARY KEY NOT NULL,
NOMLOGO VARCHAR(20),
LOGOS BYTEA
);

/* VERSION 1 REFERENCIAS */ 
ALTER TABLE T_TALLAS ADD
FOREIGN KEY (TRQ_CODIGO) REFERENCES TROQUELES (TRQ_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE T_TALLAS ADD
FOREIGN KEY (TAL_CODIGO) REFERENCES TALLAS (TAL_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE MOD_TRQ_TAL ADD
FOREIGN KEY (TAL_CODIGO) REFERENCES TALLAS (TAL_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE MOD_TRQ_TAL ADD
FOREIGN KEY (TRQ_CODIGO) REFERENCES TROQUELES (TRQ_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE MOD_TRQ_TAL ADD
FOREIGN KEY (MOD_CODIGO) REFERENCES MODELOS (MOD_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;

/* VERSION 2 REFERENCIAS */

ALTER TABLE LINEASPROD ADD
FOREIGN KEY (PRO_CODIGO) REFERENCES PROCESOS(PRO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE LINEASPROD ADD
FOREIGN KEY (CODIGO_TIPLINEA) REFERENCES TIP_LINEA(CODIGO_TIPLINEA) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE MAQUINAS ADD
FOREIGN KEY (LINEAPRO_CODIGO) REFERENCES LINEASPROD(LINEAPRO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE LINEASTURNOS ADD
FOREIGN KEY (LINEAPRO_CODIGO) REFERENCES LINEASPROD(LINEAPRO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE LINEASTURNOS ADD
FOREIGN KEY (TURNO_CODIGO) REFERENCES TURNOS(TURNO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE LINEASTURNOS ADD
FOREIGN KEY (PARAM_CODIGO) REFERENCES PARAMETROS(PARAM_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ORDENPROD ADD
FOREIGN KEY (USER_ID_SOLI) REFERENCES CLIENTES(COD_CLIENTE) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE ORDENPROD ADD
FOREIGN KEY (USER_ID_RESP) REFERENCES USUARIO(USER_ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE ORDENPROD ADD
FOREIGN KEY (LUGAR_CODIGO_DEST) REFERENCES LUGARES(LUGAR_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE DETALLEORDEN ADD
FOREIGN KEY (MOD_CODIGO) REFERENCES MODELOS(MOD_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE DETALLEORDEN ADD
FOREIGN KEY (TAL_CODIGO) REFERENCES TALLAS(TAL_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE DETALLEORDEN ADD
FOREIGN KEY (ORDENPROD_CODIGO) REFERENCES ORDENPROD(ORDENPROD_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE PROGRAMTURNOS ADD
FOREIGN KEY (PROCESSOP_COD) REFERENCES PROCESOSOP(PROCESSOP_COD) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PROGRAMTURNOS ADD
FOREIGN KEY (PROGDIAS_CODIGO) REFERENCES PROGRAMDIAS(PROGDIAS_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PROGRAMTURNOS ADD
FOREIGN KEY (TAL_CODIGO) REFERENCES TALLAS(TAL_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PROGRAMTURNOS ADD
FOREIGN KEY (MOD_CODIGO) REFERENCES MODELOS(MOD_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PROGRAMTURNOS ADD
FOREIGN KEY (TURNO_CODIGO) REFERENCES TURNOS(TURNO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PROGRAMTURNOS ADD
FOREIGN KEY (LTCODIGO) REFERENCES LINEASTURNOS(LTCODIGO) ON UPDATE CASCADE ON DELETE CASCADE;


ALTER TABLE PROGRAMDIAS ADD
FOREIGN KEY (PARAM_CODIGO) REFERENCES PARAMETROS (PARAM_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE PROCESOSOP ADD
FOREIGN KEY (PRO_CODIGO) REFERENCES PROCESOS (PRO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PROCESOSOP ADD
FOREIGN KEY (PARAM_CODIGO) REFERENCES PARAMETROS(PARAM_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;


ALTER TABLE PROCESOSOP ADD
FOREIGN KEY (ORDENPROD_CODIGO) REFERENCES ORDENPROD (ORDENPROD_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PROCESOSOP ADD
FOREIGN KEY (LUGAR_CODIGO_ORIG) REFERENCES LUGARES(LUGAR_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PROCESOSOP ADD
FOREIGN KEY (LUGAR_CODIGO_DEST) REFERENCES LUGARES(LUGAR_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PROCESOSOP ADD
FOREIGN KEY (USER_ID_RESP) REFERENCES USUARIO(USER_ID) ON UPDATE CASCADE ON DELETE CASCADE;

/* REFERENCIAS 3 RICARDO */
ALTER TABLE DISPOSITIVOS ADD 
FOREIGN KEY (ID_MAQUINAS_REF) REFERENCES MAQUINAS(MAQ_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE DISPOSITIVOS ADD 
FOREIGN KEY (PRO_CODIGO_REF) REFERENCES PROCESOS(PRO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE PROC_ALERTA ADD
FOREIGN KEY (PRO_CODIGO) REFERENCES PROCESOS (PRO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PROC_ALERTA ADD
FOREIGN KEY (ALERTA_CODIGO ) REFERENCES ALERTAS (ALERTA_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PROC_ALERTA ADD
FOREIGN KEY (ESTADO_CODIGO ) REFERENCES ESTADOS_ALT (ESTADO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PROC_ALERTA ADD
FOREIGN KEY (ID_DISPOSITIVO ) REFERENCES DISPOSITIVOS (ID_DISPOSITIVO) ON UPDATE CASCADE ON DELETE CASCADE;

/* REFERENCIAS 4 */ 
ALTER TABLE PROCESOS ADD
FOREIGN KEY (TPR_CODIGO) REFERENCES TIPO_PROCESOS(TPR_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PROCESOS ADD
FOREIGN KEY (PRO_PADRE) REFERENCES PROCESOS(PRO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE CONFPROCESO ADD
FOREIGN KEY (PRO_CODIGO) REFERENCES PROCESOS(PRO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE CONFPROCESO ADD
FOREIGN KEY (MOD_CODIGO) REFERENCES MODELOS(MOD_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE CONFPROCESO ADD
FOREIGN KEY (SUB_PRO) REFERENCES PROCESOS(PRO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE CONFPROCESO ADD
FOREIGN KEY (CODIGO_TIPLINEA) REFERENCES TIP_LINEA(CODIGO_TIPLINEA) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE COSTOSINDI ADD
FOREIGN KEY (PRO_CODIGO) REFERENCES PROCESOS(PRO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE COSTOSINDI ADD
FOREIGN KEY (ORDENPROD_CODIGO) REFERENCES ORDENPROD(ORDENPROD_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;

/* REFERENCIAS 5 */
ALTER TABLE DISTRIBDETALLE ADD
FOREIGN KEY (DETAORDEN_CODIGO) REFERENCES DETALLEORDEN(DETAORDEN_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE DISTRIBDETALLE ADD
FOREIGN KEY (PRO_CODIGO) REFERENCES PROCESOS(PRO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE DISTRIBDETALLE ADD
FOREIGN KEY (CODIGO_TIPLINEA) REFERENCES TIP_LINEA(CODIGO_TIPLINEA) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE PARAMETROS ADD
FOREIGN KEY (PRO_CODIGO) REFERENCES PROCESOS(PRO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PARAMETROS ADD
FOREIGN KEY (CODIGO_TIPLINEA) REFERENCES TIP_LINEA(CODIGO_TIPLINEA) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE PARAMETROS ADD
FOREIGN KEY (ORDENPROD_CODIGO) REFERENCES ORDENPROD(ORDENPROD_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;



/* ELIMINAR TABLAS VERSION 1 */
DROP TABLE T_TALLAS CASCADE;
DROP TABLE PROCESOS CASCADE;
DROP TABLE MOD_TRQ_TAL CASCADE;
DROP TABLE MODELOS CASCADE;
DROP TABLE TROQUELES CASCADE;
DROP TABLE TALLAS CASCADE;
DROP TABLE TIPO_PROCESOS CASCADE;

/* ELIMINAR TABLAS VERSION 2 */
DROP TABLE MAQUINAS CASCADE;
DROP TABLE LINEASTURNOS CASCADE;
DROP TABLE PROGRAMTURNOS CASCADE;
DROP TABLE PROCESOSOP CASCADE;
DROP TABLE DETALLEORDEN CASCADE;
DROP TABLE ORDENPROD CASCADE;
DROP TABLE PROGRAMDIAS CASCADE;
DROP TABLE LINEASPROD CASCADE;
DROP TABLE LUGARES CASCADE;
DROP TABLE TURNOS CASCADE;
DROP TABLE CLIENTES CASCADE;
DROP TABLE PROC_ALERTA CASCADE;
DROP TABLE ALERTAS CASCADE;
DROP TABLE DISPOSITIVOS CASCADE;
DROP TABLE CONFPROCESO CASCADE;
DROP TABLE COSTOSINDI CASCADE;

/* ELIMINAR TABLAS INICIALES */
DROP TABLE USUARIO;
DROP TABLE ROLMENU;
DROP TABLE ROL; 
DROP TABLE MENU;

/* ELIMINAR DATOS 1*/
DELETE  FROM T_TALLAS;
DELETE  FROM MODELOS;
DELETE  FROM TALLAS;
DELETE  FROM MOD_TRQ_TAL;
DELETE  FROM TIPO_PROCESOS;
DELETE  FROM TROQUELES;
DELETE  FROM PROCESOS;

/*ELIMINAR DATOS DEL PROCESO*/
delete from programturnos;
delete from procesosop;
delete from programdias;
delete from lineasturnos;
delete from parametros;
delete from distribdetalle;
delete from detalleorden;
delete from ordenprod;
/*____________________________________*/
CREATE TABLE USUARIO
(
USER_ID SERIAL PRIMARY KEY NOT NULL,
ROL_ID INTEGER NOT NULL,
USER_NAME  VARCHAR(20),
USER_PASSWD VARCHAR(20),
USER_EMAIL VARCHAR(30),
USER_STATE NUMERIC(1),
USER_CREATION VARCHAR(20),
USER_THEME VARCHAR(20),
USER_DATE_CREATION DATE,
PRO_CODIGO INTEGER,
LINEAPRO_CODIGO INTEGER,
TURNO_CODIGO INTEGER,
MAQ_CODIGO INTEGER
);

CREATE TABLE ROL(
ROL_ID SERIAL PRIMARY KEY NOT NULL,
ROL_NOMBRE VARCHAR(20),
ROL_DESCRIPCION VARCHAR(50),
ROL_STADO NUMERIC(1)
);

CREATE TABLE ROLMENU(
ROLMENU_ID SERIAL PRIMARY KEY NOT NULL,
ROL_ID INTEGER,
MENU_ID INTEGER
);

CREATE TABLE MENU(
MENU_ID SERIAL PRIMARY KEY NOT NULL,
M_ID INTEGER,
MENU_NIVEL INTEGER,
MENU_ORDEN INTEGER,
MENU_NOMBRE VARCHAR(10),
MENU_URL VARCHAR(120),
MENU_ICONO VARCHAR(30),
MENU_ESTADO NUMERIC(1)
);

/* REFERENCIAS TABLAS BASICAS */
ALTER TABLE USUARIO ADD
FOREIGN KEY (ROL_ID) REFERENCES ROL(ROL_ID) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE USUARIO ADD
FOREIGN KEY (PRO_CODIGO) REFERENCES PROCESOS(PRO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE USUARIO ADD
FOREIGN KEY (LINEAPRO_CODIGO) REFERENCES LINEASPROD(LINEAPRO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE USUARIO ADD
FOREIGN KEY (TURNO_CODIGO) REFERENCES TURNOS(TURNO_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE USUARIO ADD
FOREIGN KEY (MAQ_CODIGO) REFERENCES MAQUINAS(MAQ_CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ROLMENU ADD
FOREIGN KEY (ROL_ID) REFERENCES ROL(ROL_ID) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ROLMENU ADD
FOREIGN KEY (MENU_ID) REFERENCES MENU(MENU_ID) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE MENU ADD
FOREIGN KEY (MENU_NIVEL) REFERENCES MENU(MENU_ID) ON UPDATE CASCADE ON DELETE CASCADE;

/* ELIMINAR TABLAS BASICAS */
DROP TABLE ROLMENU CASCADE;
DROP TABLE ROL CASCADE;
DROP TABLE USUARIO CASCADE;
DROP TABLE MENU CASCADE;
/* ******************** */ 

/* *********************** */
INSERT INTO ROL(ROL_ID,ROL_NOMBRE,ROL_DESCRIPCION,ROL_STADO)
VALUES(DEFAULT,'ADMIN','ADMINISTRADOR',1);

INSERT INTO USUARIO(USER_ID, USER_CREATION, USER_DATE_CREATION, USER_EMAIL, USER_NAME, USER_PASSWD, USER_STATE, ROL_ID,USER_THEME)
VALUES(DEFAULT,'ADMIN',CURRENT_DATE,'ADMIN@UTA.EDU.EC','ADMIN','ADMIN',1,1,NULL);

INSERT INTO CLIENTES
VALUES(DEFAULT, 'COMERCIAL NN',NULL,'0321456522','AV. LOS ATIS',NULL);

SELECT * FROM USUARIO;

/* Consulta vista t_tallas */
SELECT TA.TALNUMERO FROM TALLA TA WHERE TA.TALCODIGO NOT IN (SELECT T.TALCODIGO FROM TALLA T INNER JOIN T.TTALLAS AS TT WHERE TT.ID.TRQCODIGO = '4')

/* CONSULTA PARA CONSUMIR DE LA TABLA T_TALLAS PARA LA TABLA MTT*/
SELECT TT.TALLA.TALNUMERO FROM TTALLA TT WHERE TT.CANTIDAD !=0 AND TT.TROQUELE.TRQCODIGO = 2

/*TRIGGER PARA RESTAR LAS CANTIDADES DE LA TABLA T_TALLAS LA CANTIDAD DE TROQUELES*/
CREATE OR REPLACE FUNCTION DISMCANTTTALLA() RETURNS TRIGGER AS $DISMCANTTTALLA$
BEGIN

UPDATE T_TALLAS
SET CANTIDAD = CANTIDAD -1
WHERE TAL_CODIGO = NEW.TAL_CODIGO
AND TRQ_CODIGO = NEW.TRQ_CODIGO;

RETURN NULL;
END
$DISMCANTTTALLA$ LANGUAGE PLPGSQL;

CREATE TRIGGER DISMCANTTTALLAO AFTER INSERT ON MOD_TRQ_TAL FOR EACH ROW EXECUTE PROCEDURE DISMCANTTTALLA();

/* MOSTRAR SECUENCIA DE LAS TABLAS */
SELECT c.relname FROM pg_class c WHERE c.relkind = 'S';

/* REINICIAR SECUENCIA DE LAS TABLAS*/
ALTER SEQUENCE product_id_seq RESTART WITH 1;