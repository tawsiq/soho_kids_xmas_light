variable "flavor" { default = "m1.medium" }
variable "image" { default = "Debian Buster 10.11.1 20211029" }
variable "name1" { default = "DebbySoho" }

variable "keypair" { default = "osproject" } # you may need to change this
variable "network" { default = "openStack_project_network" }   # you need to change this

variable "pool" { default = "cscloud_private_floating" }
variable "server1_script" { default = "./server1.sh" }
variable "security_description" { default = "Terraform security group" }
variable "security_name" { default = "tf_securityMat_new" }
