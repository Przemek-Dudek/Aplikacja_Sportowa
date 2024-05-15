gradle build

docker build -t pkiam-userbase .

docker tag pkiam-userbase:latest 637423465034.dkr.ecr.eu-north-1.amazonaws.com/pkiam-userbase:latest

docker push 637423465034.dkr.ecr.eu-north-1.amazonaws.com/pkiam-userbase:latest

docker-compose -f docker-compose.yaml up
