CREATE OR REPLACE FUNCTION ACTUALIZAR_SUBAREA
  (
      SA_ID IN NUMBER
    , SA_AREA_ID NUMBER
    , SA_NOMBRE IN VARCHAR2
  ) RETURN NUMBER
IS
  -- AQUI SE DECLARAN VARIABLES
  BEGIN

    UPDATE SUB_AREA SET
                AREA_ID = SA_AREA_ID,
                NOMBRE = SA_NOMBRE
                WHERE ID = SA_ID;
    RETURN 0; --SIGNIFICA CORRECTO

    EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
    RETURN 1;  --SIGNIFICA ERROR

  END ACTUALIZAR_SUBAREA;