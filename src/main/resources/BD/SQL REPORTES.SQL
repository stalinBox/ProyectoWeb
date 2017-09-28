/* REPORTE CAPACIDADES POR ORDENES*/
SELECT op.ordenprod_codigo, cl.nombrecli, us.user_name, m.mod_nombre, t.tal_numero, emp.emp_nombre,emp.emp_direcc,emp.emp_telf, op.f_actual, dto.cantidad, encode(emp.emp_logo,'base64') as emp_logo, encode(lgs.logos,'base64') as logos
FROM ordenprod op inner join 
detalleorden dto on op.ordenprod_codigo = dto.ordenprod_codigo inner join 
usuario us on us.user_id = op.user_id_soli inner join
clientes cl on cl.cod_cliente = op.user_id_soli inner join 
modelos m on m.mod_codigo = dto.mod_codigo inner join
tallas t on t.tal_codigo = dto.tal_codigo, empresa emp, logosfaps lgs
WHERE op.ordenprod_codigo = 133