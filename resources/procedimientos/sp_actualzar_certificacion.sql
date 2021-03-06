-- EL NOMBRE QUE SALE AQUI SE DEBE PONER EN LA CLASE P.J ACTUALIZAR_CERTIFICACION.
create or replace PROCEDURE ACTUALIZAR_CERTIFICACION
  (
    C_ID IN CERTIFICATION.CERTIFICATION_ID%TYPE,
    C_TITULO IN CERTIFICATION.CERTIFICATION_TITLE%TYPE,
    I_ID IN INSTITUTION.ID%TYPE
  )
IS
  --output
  V_ME VARCHAR2(100);

  --Certificacion - C
  V_C_ID CERTIFICATION.CERTIFICATION_ID%TYPE        := C_ID;
  V_C_TITULO CERTIFICATION.CERTIFICATION_TITLE%TYPE := LOWER(C_TITULO);
  V_C_CODIGO CERTIFICATION.CERTIFICATION_CODE%TYPE  := V_C_TITULO;


  --Institucion - I
  V_I_ID CERTIFICATION.INSTITUTION_ID%TYPE          := I_ID;
  V_I_NOMBRE INSTITUTION.INSTITUTION_NAME%TYPE;


  BEGIN

    V_C_CODIGO := CREATE_SLUG(V_C_CODIGO || ' ' || V_I_ID);

    --busco la institucion por el id y obtengo su nombre
    --no sirve para nada en este caso :)
    SELECT INSTITUTION_NAME
    INTO V_I_NOMBRE
    FROM INSTITUTION
    WHERE ID = I_ID;

    --aqui se actualiza buscando la certificacion por el id
    UPDATE CERTIFICATION SET
      CERTIFICATION_TITLE = V_C_TITULO,
      INSTITUTION_ID = V_I_ID,
      CERTIFICATION_CODE = V_C_CODIGO
    WHERE CERTIFICATION_ID = V_C_ID;

    EXCEPTION
    -- valores duplicados
    WHEN DUP_VAL_ON_INDEX THEN
    Raise_Application_Error(-20001, 'La certificacion a actualizar ya esta registrada');

  END;