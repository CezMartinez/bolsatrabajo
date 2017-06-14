create or replace FUNCTION CREAR_CARGO
  (
    NOMBRE IN VARCHAR2
  ) RETURN NUMBER IS

  V_BOOL NUMBER;
  V_JOB_ID JOB.JOB_ID%TYPE;
  BEGIN

    SELECT HIBERNATE_SEQUENCE.NEXTVAL INTO V_JOB_ID FROM DUAL;


    INSERT INTO JOB(JOB_ID,PUESTO) VALUES
      (V_JOB_ID,lower(NOMBRE));

    V_BOOL := 0;

    RETURN V_BOOL;

    EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
    RETURN -1; --Valor duplicado

  END CREAR_CARGO;