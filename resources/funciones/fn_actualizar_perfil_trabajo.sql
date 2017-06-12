create or replace FUNCTION ACTUALIZAR_PERFIL_TRABAJO
(
  ID_JP       IN JOB_PROFILES.ID%TYPE
, NOMBRE      IN JOB_PROFILES.NAME%TYPE
, DESCRIPCION IN JOB_PROFILES.DESCRIPTION%TYPE
, EDAD_MAX    IN JOB_PROFILES.MAX_AGE%TYPE
, EDAD_MIN    IN JOB_PROFILES.MIN_AGE%TYPE
, COMPAÑIA_ID IN JOB_PROFILES.COMPANY_ID%TYPE
) RETURN NUMBER
IS
  V_BOOL          NUMBER;
  V_JP_ID         JOB_PROFILES.ID%TYPE;
  V_JP_CODIGO     JOB_PROFILES.CODE%TYPE;
  E_DES_MUY_LARGA EXCEPTION;
  E_DE_EDAD       EXCEPTION;
  E_DES_LARGA     EXCEPTION;

BEGIN

  V_JP_CODIGO := CREATE_SLUG(LOWER(NOMBRE) || ' ' || TO_CHAR(COMPAÑIA_ID));


  IF EDAD_MAX < 18 OR EDAD_MAX >= 100 THEN
    RAISE E_DE_EDAD;
  END IF;

  IF EDAD_MIN < 18 THEN
    RAISE E_DE_EDAD;
  END IF;

  UPDATE JOB_PROFILES SET
    CODE = V_JP_CODIGO,
    NAME = lower(NOMBRE),
    DESCRIPTION = DESCRIPCION,
    MAX_AGE = EDAD_MAX,
    MIN_AGE = EDAD_MIN,
    COMPANY_ID = COMPAÑIA_ID WHERE ID = ID_JP;

  RETURN 0; -- NO HAY PEDO

  EXCEPTION

  WHEN DUP_VAL_ON_INDEX THEN

    RETURN -1; --Valor duplicado

  WHEN E_DE_EDAD THEN

    RETURN -2; -- ERROR DE EDAD

END ACTUALIZAR_PERFIL_TRABAJO;