mvn clean package -DskipTests
docker-compose down -v
docker-compose build
docker-compose up