create temp table persona2 as (select count(*) as cant,cedula 
from persona 
where cedula not ilike'%44444%'
group by 2
order by 1 desc);

update persona set tipo_persona='cliente-dupli' from persona2
where persona.cedula=persona2.cedula
and persona2.cant>1;

create temp table persona3 as (select idpersona,cedula from persona where tipo_persona='cliente-dupli');

update persona set tipo_persona='cliente-uni' from persona3
where persona.idpersona=(select max(idpersona) as id from persona3 where cedula=persona.cedula)
and persona.tipo_persona='cliente-dupli';

delete from persona where tipo_persona='cliente-dupli';

update persona set tipo_persona='cliente' where tipo_persona='cliente-uni';


