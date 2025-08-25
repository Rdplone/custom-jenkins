# Use the official Jenkins LTS image as base
FROM jenkins/jenkins:lts

# Switch to root to install packages
USER root

# Update package lists and install sshpass
RUN apt-get update && \
    apt-get install -y sshpass && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Switch back to Jenkins user (default in the base image)
USER jenkins
