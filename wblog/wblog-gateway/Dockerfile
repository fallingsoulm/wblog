FROM registry.cn-hangzhou.aliyuncs.com/l_third_party/java:8
MAINTAINER luyanan <luyanan0718@163.com>
ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY target/wblog-gateway.jar app.jar
EXPOSE 8888
CMD  exec java $JAVA_OPTS -jar app.jar $RUN_OPTS