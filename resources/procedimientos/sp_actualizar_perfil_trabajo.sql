create or replace PROCEDURE SP_ACTUALIZAR_PERFIL_TRABAJO
(
  ID_JP IN NUMBER
, NOMBRE IN VARCHAR2
, DESCRIPCION IN VARCHAR2
, EDAD_MAX IN NUMBER
, EDAD_MIN IN NUMBER
, COMPAÑIA_ID IN NUMBER
) IS

  V_RESPUESTA NUMBER;

BEGIN

  V_RESPUESTA := ACTUALIZAR_PERFIL_TRABAJO(ID_JP,NOMBRE,DESCRIPCION,EDAD_MAX,EDAD_MIN,COMPAÑIA_ID);

  IF V_RESPUESTA = -1 THEN
    RAISE_APPLICATION_ERROR(-20001, 'El perfil de trabajo ya existe para esta empresa');
  END IF;
  IF V_RESPUESTA = -2 THEN
    RAISE_APPLICATION_ERROR(-20002, 'El rango de la edad debe de estar entre 18 y 99 años');
  END IF;

END SP_ACTUALIZAR_PERFIL_TRABAJO;