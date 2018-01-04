all: test-server compile-server build-server init-client compile-client build-client

init-client:
	cd ./client && npm i

test-client:
	cd ./client && ng test

compile-client:
	cd ./client && ng build --prod

build-client:
	docker build -t deniscraig/teclient -f ./client/Dockerfile

test-server:
	cd ./server && gradle clean test

compile-server:
	cd ./server && gradle clean build

build-server:
	docker build -t deniscraig/teserver -f ./server/Dockerfile