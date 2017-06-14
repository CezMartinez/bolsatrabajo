CREATE OR REPLACE PROCEDURE SP_CREAR_EXPERI_LABORAL_PREFIL
  (
      ID_PROFILE IN WORK_EXPERIENCES_PROFILES.ID%TYPE
    , ID_PUESTO IN WORK_EXPERIENCES_PROFILES.JOB_ID%TYPE
    , AÑOS IN  WORK_EXPERIENCES_PROFILES.YEAR_OF_EXPERIENCE%TYPE
    , NOMBRE_PUESTO IN JOB.PUESTO%TYPE
  ) IS
  V_JOB_NAME JOB.PUESTO%TYPE := LOWER(NOMBRE_PUESTO);
  V_RESULTADO NUMBER;
  V_RESUL_CAR NUMBER;
  BEGIN

    V_RESULTADO := CREAR_EXPERIENCIA_LABORAR_PERF(AÑOS,ID_PUESTO,ID_PROFILE);



    IF V_RESULTADO = -1 THEN

      V_RESUL_CAR := CREAR_CARGO(NOMBRE_PUESTO);

      IF V_RESUL_CAR = -1 THEN
        RAISE_APPLICATION_ERROR(-20002, 'El Puesto ya existe');
      END IF;

    ELSIF V_RESULTADO = -2 THEN
      RAISE_APPLICATION_ERROR(-20002, 'El perfil no existe');
    elsif v_resultado = -3 THEN
      RAISE_APPLICATION_ERROR(-20003, 'Ya existe el registro de este cargo para este perfil');
    elsif v_resultado = -4 THEN
      RAISE_APPLICATION_ERROR(-20004, 'La experiencia no puede ser negativa o igual a cero');
    END IF;


  END SP_CREAR_EXPERI_LABORAL_PREFIL;