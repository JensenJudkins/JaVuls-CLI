#!/bin/sh
echo "Updating"
sudo apt update
echo "Going to put device into monitor mode on $1 (linux only)"
echo "installing dependicies (aircrack-ng suite)"
sudo apt install aircrack-ng -y
echo "Setting $1 to monitor mode"
echo "Killing processes"
sudo airmon-ng --check-kill 
echo "Setting up"
sudo airmon-ng start $1
