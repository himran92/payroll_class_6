scp /home/travis/build/himran92/payroll_class_6/target/payroll-0.0.1-SNAPSHOT.jar ec2-user@ec2-3-20-204-183.us-east-2.compute.amazonaws.com:~/
ssh ec2-user@ec2-3-20-204-183.us-east-2.compute.amazonaws.com "killall -9 java"
ssh ec2-user@ec2-3-20-204-183.us-east-2.compute.amazonaws.com "java -jar payroll-0.0.1-SNAPSHOT.jar &"