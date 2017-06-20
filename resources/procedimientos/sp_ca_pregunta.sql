CREATE OR REPLACE PROCEDURE CA_PREGUNTA --CA = CREAR / ACTUALIZAR
  (
    Q_ID IN QUESTION.ID%TYPE,
    Q_D1 IN QUESTION.D1%TYPE,
    Q_D2 IN QUESTION.D2%TYPE,
    Q_D3 IN QUESTION.D3%TYPE,
    Q_D4 IN QUESTION.D4%TYPE,
    Q_ENUNCIADO IN QUESTION.ENUNCIADO%TYPE,
    Q_PESO IN QUESTION.PESO%TYPE,
    Q_RESPUESTA IN QUESTION.RESPUESTA%TYPE,
    Q_SUBAREA_ID IN QUESTION.SUB_AREA_ID%TYPE
  )
IS
  --PREGUNTA NUEVA DESDE LA PETICION - Q
    V_Q_ID QUESTION.ID%TYPE := Q_ID;
    V_Q_D1 QUESTION.D1%TYPE := Q_D1;
    V_Q_D2 QUESTION.D2%TYPE := Q_D2;
    V_Q_D3 QUESTION.D3%TYPE := Q_D3;
    V_Q_D4 QUESTION.D4%TYPE := Q_D4;
    V_Q_ENUNCIADO QUESTION.ENUNCIADO%TYPE := Q_ENUNCIADO;
    V_Q_PESO QUESTION.PESO%TYPE := Q_PESO;
    V_Q_RESPUESTA QUESTION.RESPUESTA%TYPE := Q_RESPUESTA;
    V_Q_SUBAREA_ID QUESTION.SUB_AREA_ID%TYPE := Q_SUBAREA_ID;

  --VARIABLES VERIFICACION DE GUARDADO. -G
  V_G NUMBER;
  TOTAL NUMBER;

  BEGIN

    --SI El ID ES 0 SE TRATA DE UN NUEVO REGISTRO
    IF V_Q_ID = 0 THEN

      -- seguarda el valor de la secuencia en el nuevo valor del
      -- ID
      SELECT HIBERNATE_SEQUENCE.NEXTVAL INTO V_Q_ID FROM DUAL;

      -------INSERCION DEL REGISTRO USANDO LA FUNCION CREAR_ ---------
      V_G := CREAR_PREGUNTA(V_Q_ID,V_Q_D1,V_Q_D2,V_Q_D3,V_Q_D4,V_Q_ENUNCIADO,V_Q_PESO,V_Q_RESPUESTA,V_Q_SUBAREA_ID);

      -- 1 significa error desde la funcion
      IF V_G = 1 THEN
        RAISE_APPLICATION_ERROR(-20001, 'NO SE PUDO GUARDAR, PORQUE YA EXISTE LA PREGUNTA');
      END IF;

    ELSE --SI SE DIJITA POR EL USUARIO
        TOTAL := 0;
        SELECT COUNT(*) INTO TOTAL FROM QUESTION WHERE ID = V_Q_ID;

        IF TOTAL = 0 THEN -- SI EL TOTAL ES 0 NO SE ENCONTRO
                            --REGISTRO CON ESE ID ENTONCES LO CREA
            -------INSERCION DEL REGISTRO---------
            V_G := CREAR_PREGUNTA(V_Q_ID,V_Q_D1,V_Q_D2,V_Q_D3,V_Q_D4,V_Q_ENUNCIADO,V_Q_PESO,V_Q_RESPUESTA,V_Q_SUBAREA_ID);

            -- 1 significa error desde la funcion
            IF V_G = 1 THEN
              RAISE_APPLICATION_ERROR(-20001, 'NO SE PUDO GUARDAR, PORQUE YA EXISTE LA PREGUNTA');
            END IF;

        ELSE --SI ENCONTRO REGISTRO ENTONCES LA ACTUALIZA

            V_G :=ACTUALIZAR_PREGUNTA(V_Q_ID,V_Q_D1,V_Q_D2,V_Q_D3,V_Q_D4,V_Q_ENUNCIADO,V_Q_PESO,V_Q_RESPUESTA,V_Q_SUBAREA_ID);

            IF V_G = 1 THEN
                Raise_Application_Error(-20001, 'La subarea no se pudo actualizar');
            END IF;

        END IF;
    END IF;
  END CA_PREGUNTA;