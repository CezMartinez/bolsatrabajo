CREATE OR REPLACE FUNCTION ACTUALIZAR_EXAM
  (
      ID_E    IN NUMBER
    , FECHA_E IN TIMESTAMP
    , PUBLICADO_E IN NUMBER
    , ID_SUBAREA_E IN NUMBER
    , TITULO_E IN VARCHAR2
  ) RETURN NUMBER --RETORNARA UN NUMERO
IS
    -- AQUI SE DECLARAN VARIABLES
   --
  BEGIN
    --ACTUALIZA EL REGISTRO
    UPDATE EXAM SET
                FECHA = FECHA_E,
                PUBLICADO = PUBLICADO_E,
                SUB_AREA_ID = ID_SUBAREA_E,
                TITULO = TITULO_E
                WHERE ID = A_ID;

    RETURN 0; --SIGNIFICA CORRECTO

    EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
    RETURN 1;  --SIGNIFICA ERROR

  END ACTUALIZAR_EXAM;