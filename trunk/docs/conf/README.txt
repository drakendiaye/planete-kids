Voici quelque fichier ppour vous aidez à déployer le site :
 - guillaume.dedidream.com.conf :	- Ce fichier sert a parametrer un virtualhost pour apache
					- Renommer le fichier avec le nom du virtual host que vous souhaitez definir
					- Modifier la ligne ServerName dans le fichier en fonction
					- Ajouter une ligne dans /etc/hosts : 127.0.0.1 <nom du vhost>
					- Copier ce fichier dans le repertoire /etc/apache2/sites-available
					- Activer le site : a2ensite <nom du fichier de conf>
					- Activer le mod proxy : a2enmod proxy
					- Redemarrer apache : /etc/init.d/apache2 restart

 - planete-kids.properties : 	- Ce fichier sert à binder la base de donnée dans jonas
				- Creer une base de donnée mysql nommée planete-kids
				- Modifier dans le fichier le login et password pour pouvoir acceder a la base
				- Copier le fichier dans JONAS_ROOT/conf
				- Aller dans l'interface d'admin de jonas pour activer la base
