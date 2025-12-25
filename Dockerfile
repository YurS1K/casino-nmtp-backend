FROM public.ecr.aws/amazoncorretto/amazoncorretto:21
WORKDIR /app
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75"
COPY build/libs/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]