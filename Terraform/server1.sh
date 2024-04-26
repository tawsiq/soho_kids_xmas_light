#!/usr/bin/bash

echo "Installing additional packages..."
sudo apt install -y wget

# Append configuration to SSH client configuration file
echo "    StrictHostKeyChecking no" | sudo tee -a /etc/ssh/ssh_config > /dev/null

### MariaDb & MySQL setup ###
echo "installing MariaDB..."
sudo apt update
sudo apt install mariadb-server -y
sudo systemctl start mariadb
sudo systemctl status mariadb
sudo systemctl enable mariadb

echo "creating mysql_secure_installation.txt..."
touch mysql_secure_installation.txt
cat << `EOF` >> mysql_secure_installation.txt

Y
comsc
comsc
Y
Y
Y
Y
`EOF`

echo "configuring root user in MariaDB..."
sudo mysql -e "ALTER USER root@localhost IDENTIFIED VIA mysql_native_password USING PASSWORD("comsc")"
sudo mysql -e "UPDATE mysql.user SET plugin = 'mysql_native_password' WHERE User = 'root'"
echo "running mysql_secure_installation..."
sudo mysql_secure_installation < mysql_secure_installation.txt


### Git installation, Key setup and Repository Cloning ###
echo "Installing git..."
sudo apt install git -y



echo "                                                                         "
echo "                                                                         "
echo "========================================================================="
echo "=========================== Installing Java17 ==========================="
echo "                                                                         "
echo "                                                                         "
# # Reference: https://computingforgeeks.com/install-oracle-java-openjdk-on-debian-linux/

sudo apt -y install wget
wget https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.deb

sudo apt install -y ./jdk-17_linux-x64_bin.deb

export JAVA_HOME=/usr/lib/jvm/jdk-17-oracle-x64
export PATH=$PATH:$JAVA_HOME/bin

echo "                                                                         "
echo "                                                                         "
echo "========================================================================="
echo "   ========== The following version of Java was installed ==========     "
echo "                                                                         "
java -version
sudo apt install default-jre -y

# Install Jenkins
echo "Installing Jenkins..."
sudo wget -O /usr/share/keyrings/jenkins-keyring.asc \
  https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt-get update
sudo apt-get install jenkins

# Update and upgrade Jenkins
echo "Updating Jenkins..."
sudo apt update
sudo apt upgrade jenkins -y

# # Configure Jenkins to run on port 8081
echo "Configuring Jenkins to run on port 8081..."
sudo systemctl stop jenkins
sudo sed --i 's/"JENKINS_PORT=8080/JENKINS_PORT=8081"/g' /usr/lib/systemd/system/jenkins.service
sudo systemctl daemon-reload
sudo systemctl restart jenkins

# # Start Jenkins service
# echo "Starting Jenkins service..."
# sudo systemctl start jenkins

# Enable Jenkins service to start on boot
echo "Enabling Jenkins service to start on boot..."
sudo systemctl enable jenkins

# Check Jenkins service status
echo "Checking Jenkins service status..."
sudo systemctl status jenkins


# Install Terraform
echo "Installing Terraform..."
sudo apt install -y unzip
wget https://releases.hashicorp.com/terraform/1.2.3/terraform_1.2.3_linux_amd64.zip
unzip terraform_1.2.3_linux_amd64.zip
sudo mv terraform /usr/local/bin/
terraform --version

echo "                                                                         "
echo "                                                                         "
echo "========================================================================="
echo "======================== Installing Gradle 8.4 =========================="
echo "                                                                         "
echo "                                                                         "
# # Reference: https://linuxize.com/post/how-to-install-gradle-on-debian-10/

sudo apt -y install unzip
wget https://services.gradle.org/distributions/gradle-8.4-bin.zip -P /tmp
sudo mkdir -p /opt/gradle
sudo unzip -d /opt/gradle /tmp/gradle-8.4-bin.zip

export GRADLE_HOME=/opt/gradle/gradle-8.4
export PATH=$PATH:$GRADLE_HOME/bin

echo "                                                                         "
echo "                                                                         "
echo "========================================================================="
echo "   ========== The following version of Gradle was installed ==========   "
echo "                                                                         "
echo "                                                                         "
gradle -v
           

su debian << 'EOF'
whoami

cat << `EOF` >> /home/debian/gitlab_project_keypair.key
-----BEGIN OPENSSH PRIVATE KEY-----
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
-----END OPENSSH PRIVATE KEY-----
`EOF`

echo "Recieved private key. Changing permissions..."
# Set private key file to read-only
chmod 400 /home/debian/gitlab_project_keypair.key
echo "Communicating with Gitlab..."

# Ask gitlab to send its public keys so that the VM can trust it.
touch /home/debian/.ssh/known_hosts
ssh-keyscan git.cardiff.ac.uk >> /home/debian/.ssh/known_hosts
# Set known_hosts to read & write for file owner and read-only for anyone else.
chmod 644 /home/debian/.ssh/known_hosts

ssh-agent bash -c 'ssh-add /home/debian/gitlab_project_keypair.key; git clone git@git.cardiff.ac.uk:c21011555/team-7-soho-kids-christmas-lights-21011555-fork.git /home/debian/team-7-soho-kids-christmas-lights-21011555-fork'

cd /home/debian/team-7-soho-kids-christmas-lights-21011555-fork/

mysql -uroot -pcomsc < src/main/resources/schema.sql

                                                          
#gradle build
#gradle test
gradle bootrun






SHELL

end