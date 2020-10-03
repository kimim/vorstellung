docker rm $(docker stop $(docker ps -a -f name=vorstellung -q))
docker rmi kimim/vorstellung

docker build -t kimim/vorstellung .
docker run -d -p 3333:3000 --name=vorstellung kimim/vorstellung
docker logs $(docker ps -f name=vorstellung -q)
