# push到docker仓库的命令
echo "start docker push"
cd ..
mvn clean package -Dmaen.test.skip=true && cd wblog-web && mvn docker:stop docker:remove docker:build docker:push docker:remove
echo "docker push success!!!"