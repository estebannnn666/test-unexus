1. Proyecto Spring Boot compilado con maven
2. Scrip base de datos postgres
create database mibdd;
CREATE TABLE CLIENTES(
	ID_CLIENTE SERIAL NOT NULL,
	TIPO_IDENTIFICACION VARCHAR(3) NOT NULL,
	NUMERO_IDENTIFICACION VARCHAR(13) NOT NULL,
	NOMBRE VARCHAR(64) NOT NULL,
	CORREO VARCHAR(64),
	CELULAR VARCHAR(13),
	PRIMARY KEY(ID_CLIENTE)
);

CREATE TABLE DIRECCIONES(
	ID_DIRECCION SERIAL NOT NULL,
	ID_CLIENTE SERIAL NOT NULL,
	PROVINCIA VARCHAR(32) NOT NULL,
	CIUDAD VARCHAR(32) NOT NULL,
	DIRECCION VARCHAR(256) NOT NULL,
	ESDIRECCIONMATRIZ BOOLEAN NOT NULL,
	PRIMARY KEY(ID_DIRECCION)
);

ALTER TABLE DIRECCIONES ADD CONSTRAINT IDCLIENTEFKCLIENTESPKDIRECCIONES FOREIGN KEY(ID_CLIENTE) REFERENCES CLIENTES(ID_CLIENTE);

SERVICIOS WEB PARA TESTS
3.1 FUNCIONALIDAD 1
	- URL: http://localhost:8080/miContextoWs/api/v1/minegocio/admin/clientes/listaClientes
	- POST SERVICE
	- JSON entrada:
		{
			"numeroIdentificacion" : "1712354657"
		}
	- JSON Salida:
		{
			"code": 200,
			"message": "La busqueda se realizo correctamente",
			"errors": null,
			"data": [
				{
					"idCliente": 1,
					"tipoIdentificacion": "DNI",
					"numeroIdentificacion": "1712354657",
					"nombres": "CLIENTE 1",
					"correo": "correo1@hotmail.com",
					"telefono": "0998543265"
					"provincia": "PROVINCIA UNO",
					"ciudad": "CIUDAD UNO",
					"direccion": "DIRECCION UNO"
				}
			]
		}

3.2 FUNCIONALIDAD 2 
	- URL: http://localhost:8080/miContextoWs/api/v1/minegocio/admin/clientes/crearCliente
	- POST SERVICE
	- JSON entrada:
		{			
			"tipoIdentificacion": "DNI",
			"numeroIdentificacion": "1712354657",
			"nombres": "CLIENTE 1",
			"correo": "correo1@hotmail.com",
			"telefono": "0998543265"
			"provincia": "PROVINCIA UNO",
			"ciudad": "CIUDAD UNO",
			"direccion": "DIRECCION UNO"
		}
	- JSON Salida:
		{
			"code": 200,
			"message": "EL cliente se creo correctamente",
			"errors": null,
			"data": {
				"tipoIdentificacion": "DNI",
				"numeroIdentificacion": "1712354657",
				"nombres": "CLIENTE 1",
				"correo": "correo1@hotmail.com",
				"telefono": "0998543265"
				"provincia": "PROVINCIA UNO",
				"ciudad": "CIUDAD UNO",
				"direccion": "DIRECCION UNO"
			}
		}
		
3.3 FUNCIONALIDAD 3
	- URL: http://localhost:8080/miContextoWs/api/v1/minegocio/admin/clientes/actualizarCliente
	- POST SERVICE
	- JSON entrada:
		{
			"idCliente": 1,
			"tipoIdentificacion": "DNI",
			"numeroIdentificacion": "1712354657",
			"nombres": "CLIENTE 1",
			"correo": "correo1@hotmail.com",
			"telefono": "0998543265"
		}
	- JSON Salida:
		{
			"code": 200,
			"message": "EL cliente se actualizo correctamente",
			"errors": null,
			"data": {
				"idCliente": 1,
				"tipoIdentificacion": "DNI",
				"numeroIdentificacion": "1712354657",
				"nombres": "CLIENTE 1",
				"correo": "correo1@hotmail.com",
				"telefono": "0998543265"
			}
		}
		
3.4 FUNCIONALIDAD
	- URL: http://localhost:8080/miContextoWs/api/v1/minegocio/admin/clientes/eliminarCliente
	- POST SERVICE
	- JSON entrada:
		{
			"idCliente": 4
		}
	- JSON Salida:
		{
			"code": 200,
			"message": "El cliente se elimino correctamente",
			"errors": null,
			"data": {
				"idCliente": 4
			}
		}
		
3.5 FUNCIONALIDAD 5
	- URL: http://localhost:8080/miContextoWs/api/v1/minegocio/admin/direcciones/guardarDireccion
	- POST SERVICE
	- JSON entrada:
		{
			"idCliente": 1,
			"provincia": "PROVINCIA UNO",
			"ciudad": "CIUDAD UNO",
			"direccion": "DIRECCION UNO",
			"esDireccionMatriz": true
		}
	- JSON Salida con direccion matriz en false:
		{
			"code": 200,
			"message": "La direccion se creo correctamente",
			"errors": null,
			"data": {
				"idDireccion": 1,
				"idCliente": 1,
				"provincia": "PROVINCIA UNO",
				"ciudad": "CIUDAD UNO",
				"direccion": "DIRECCION UNO",
				"esDireccionMatriz": false
			}
		}

3.6 FUNCIONALIDAD 6 
	- URL: http://localhost:8080/miContextoWs/api/v1/minegocio/admin/direcciones/listaDireccionesCliente
	- POST SERVICE
	- JSON entrada:
		{
			"codigoCliente": 1
		}
	- JSON Salida:
		{
			"code": 200,
			"message": "La busqueda se realizo correctamente",
			"errors": null,
			"data": [
				{
					"idCliente": 1,
					"provincia": "PROVINCIA UNO",
					"ciudad": "CIUDAD UNO",
					"direccion": "DIRECCION UNO",
					"esDireccionMatriz": true
				}				
			]
		}