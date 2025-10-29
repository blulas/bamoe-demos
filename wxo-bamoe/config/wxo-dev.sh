#!/bin/bash

if [ "$1" == "" ]; then
    echo -e "\nUsage: wxo-dev.sh <env>..."
    echo -e "  where <env> is the name of the environment configuration file.\n"
    exit 1
fi

# Remove the project directory if it exists
if [ -d "$1" ]; then rm -rf $1; fi

# Install WxO server
echo -e "\nInstalling Watson Orchestrate server..."
orchestrate server start -e $1
echo -e "\nWatson Orchestrate server has been installed and started...";
echo -e "--> In order to stop the Watson Orchestrate server, use <orchestrate server stop>";
echo -e "--> In order to uninstall the Watson Orchestrate server, use <orchestrate server reset>";
echo -e "--> and <docker system prune -a --volumes>";

# Install WxO copilot server
echo -e "\nInstalling Watson Orchestrate copilot server..."
orchestrate copilot start --env-file $1
echo -e "\nWatson Orchestrate copilot server has been installed and started...";
echo -e "--> In order to stop the Watson Orchestrate copilot server, use <orchestrate copilot stop>";
echo -e "--> In order to uninstall the Watson Orchestrate copilot server, use <orchestrate server reset>";
