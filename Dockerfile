FROM openjdk:21

ADD build/libs/tig_stack*.jar /code/tig_stack.jar
WORKDIR /code
ENTRYPOINT ["java", "-jar", "tig_stack.jar"]