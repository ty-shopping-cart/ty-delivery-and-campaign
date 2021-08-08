FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/deliveryCampaign.jar deliveryCampaign.jar
ENTRYPOINT ["java", "-jar", "/deliveryCampaign.jar"]