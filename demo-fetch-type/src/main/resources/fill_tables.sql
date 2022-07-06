CREATE OR REPLACE FUNCTION fill_tables()
    RETURNS VOID
AS
$$
DECLARE

_customer_id numeric;
    _invoice_id  numeric;
    _temp        numeric;
BEGIN
_customer_id = 0;
_invoice_id = 0;
_temp = 0;
FOR _customer_id in 1 .. 1000
        LOOP
            raise notice 'inserted into customer (%)' , _customer_id;
insert into customer (id, city, first_name, last_name, street)
values (_customer_id, concat('city_', _customer_id), concat('first_name_', _customer_id),
        concat('last_name_', _customer_id), concat('street_', _customer_id));
FOR _invoice_id in 1 + _temp .. 1000
                LOOP
                    raise notice 'inserted into invoice (%), (%)' , _customer_id, _invoice_id;
insert into invoice (id, name, customer_id)
values (_invoice_id, concat('name_', _invoice_id), _customer_id);
end loop;
_temp = _temp + 1000;
            raise notice '_temp (%)' , _temp;
END LOOP;
END ;
$$ LANGUAGE plpgsql;

do
$$
begin
        perform fill_tables();
end
$$;