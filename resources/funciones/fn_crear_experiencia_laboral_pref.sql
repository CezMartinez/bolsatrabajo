create or replace FUNCTION CREAR_EXPERIENCIA_LABORAR_PERF
  (
      AÑOS IN WORK_EXPERIENCES_PROFILES.YEAR_OF_EXPERIENCE%TYPE
    , ID_PUESTO IN JOB.JOB_ID%TYPE
    , ID_PERFIL IN JOB_PROFILES.ID%TYPE
  ) RETURN NUMBER
IS
  V_JCAT_ID JOB.JOB_ID%TYPE := -100;
  V_JPRO_ID JOB_PROFILES.ID%TYPE := -100;
  V_BOOL NUMBER;

    V_ERRO_0 EXCEPTION;
    CHECK_CONSTRAINT_JOBCAT EXCEPTION;
    CHECK_CONSTRAINT_JOBPRO EXCEPTION;
  BEGIN

    IF AÑOS <= 0 THEN
      RAISE V_ERRO_0;
    END IF;

    select 1 into V_JPRO_ID FROM JOB_PROFILES WHERE ID = ID_PERFIL;

    select 1 into V_JCAT_ID FROM JOB WHERE JOB_ID = ID_PUESTO;

    INSERT  INTO WORK_EXPERIENCES_PROFILES(YEAR_OF_EXPERIENCE,JOB_ID,ID)
    VALUES(AÑOS,ID_PUESTO,ID_PERFIL);

    V_BOOL := 0;
    RETURN V_BOOL; --SIGNIFICA CORRECTO

    EXCEPTION
    WHEN NO_DATA_FOUND THEN
    if V_JCAT_ID = -100 then
      V_BOOL := -1; -- NO ESTA EL PUESTO
    end if;

    if V_JPRO_ID = -100 then
      V_BOOL := -2; -- NO ESTA EL PERFIL
    end if;

    RETURN V_BOOL;

    WHEN DUP_VAL_ON_INDEX THEN
    RETURN -3; -- ya existe el registro para este perfil
    WHEN V_ERRO_0 THEN
    RETURN -4; --Experiencia menor que 0 o igual

  END CREAR_EXPERIENCIA_LABORAR_PERF;