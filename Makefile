# Makefile.
NAME := ProgressSoft_Project
BUILD_TOOL := ./mvnw

build :
	mvn clean install

docker-up : build
	docker-compose up -d
docker-down :
	docker-compose down -d