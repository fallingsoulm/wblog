# push到docker仓库的命令
echo "start docker push"
mvn clean package docker:stop docker:remove docker:build docker:push docker:remove
echo "docker push success!!!"