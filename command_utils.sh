sudo docker-compose down -v
#creer mon container
docker-compose up -d

# se connecter au postgreSQL
docker exec -it nom_container psql -U username_db -d name_db

docker exec -i nom_container psql -U username_db -d name_db < database.sql



sudo docker exec -i db_moderateur psql -U postgres  < create_db_moderateur.sql
