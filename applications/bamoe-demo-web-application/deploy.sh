echo "Deploying webapp to local Tomcat instance..."

mvn clean package
cp ./target/bamoe-demo-web-application.war /Library/Tomcat/webapps