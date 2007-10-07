----------------------------------------------------------------------------------
--	PROJET ECOM 								--
-- 	Fabienne Boyer, Didier Donsez						--
-- 	Universite Joseph Fourier, May 2003					--
----------------------------------------------------------------------------------

Directories
-----------
ecom\beans: contains source files of the beans 
ecom\servlets: contains source files of the servlets
ecom\client: contains source files of various beans clients
ecom\shell: contains source files of a shell client for ECOM application
shell: contains source files of a generic shell

ECOM beans 
----------

Account:		CMP entity bean providing a local interface
Product:		CMP entity bean providing a local interface
ProductStore :		CMP entity bean providing a local interface
Order (Optional):	BMP entity bean (mix of Orders, OrderLines and Payments tables)
Cart :			stateful session bean providing a local interface
EuroConvertor :		stateless session bean providing a local interface

EcomCustomer : 		stateful session bean providing the remote interface
			for an ECOM client (a client is supposed to list productstores
			and products, add products in a cart, buy products, etc)

EcomAdmin : 		stateful session bean providing the remote interface
			for an ECOM administrator (an administrator is supposed to create,
			delete or modify productstores, products, accounts).

ECOM external interfaces 
------------------------

 ExternCustomer : Java program allowing a user to make use of the features provided by a EcomCustomer bean
 ExternAdmin    : Java program allowing a user to make use of the features provided by an EcomAdmin bean
 InitDataBase   : Java program allowing to initialize the Database for the ECOM application.


How to compile and start the application
----------------------------------------
* Compile and generate the beans with compile.bat (Windows) or compile.sh (Unix)

* Start a JOnAS server with a command like this one:
jonas start

* You must wait for these messages before running clients:
The JOnAS Server 'jonas' version 3.0.6 is ready

* Remark:
	* the database must be created (BUT not necessary initialized).before 
	* if your DBMS is McKoi, your must start it before jonas

* Deploy the ECOM beans in the JOnAS server
jonas admin -a ecom.jar

* Run some basic clients :
jclient -cp \. ecom.client.InitDataBase
jclient -cp \. ecom.client.ExternClient
jclient -cp \. ecom.client.ExternAdm
