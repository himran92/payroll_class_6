scp /home/travis/build/himran92/payroll_class_6/target/payroll-${TRAVIS_BUILD_NUMBER}.jar ec2-user@ec2-3-20-204-183.us-east-2.compute.amazonaws.com:~/
rm -rf *
killall -9 java
java -jar payroll-${TRAVIS_BUILD_NUMBER}.jar