# PostgreSQL
> https://www.postgresql.org/download/linux/redhat/

## 安装
https://www.postgresql.org/download/linux/redhat/
参数：centos 7, x86_64, postgres-14
The Red Hat family of distributions includes:

Red Hat Enterprise Linux
Rocky Linux
AlmaLinux
CentOS (7 and 6 only)
Fedora
Oracle Linux
and others.

PostgreSQL is available on these platforms by default. However, each version of the platform normally "snapshots" a specific version of PostgreSQL that is then supported throughout the lifetime of this platform. Since this can often mean a different version than preferred, the PostgreSQL project provides a repository of packages of all supported versions for the most common distributions.

PostgreSQL Yum Repository
The PostgreSQL Yum Repository will integrate with your normal systems and patch management, and provide automatic updates for all supported versions of PostgreSQL throughout the support lifetime of PostgreSQL.

The PostgreSQL Yum Repository currently supports:

Red Hat Enterprise Linux
Rocky Linux
AlmaLinux
CentOS (7 and 6 only)
Oracle Linux
Fedora*
*Note: due to the shorter support cycle on Fedora, all supported versions of PostgreSQL are not available on this platform. We do not recommend using Fedora for server deployments.

To use the PostgreSQL Yum Repository, follow these steps:

Select version:
14

Select platform:
Red Hat Enterprise, CentOS, Scientific or Oracle version 7
Select architecture:
x86_64
Copy, paste and run the relevant parts of the setup script:
```shell
# Install the repository RPM:
sudo yum install -y https://download.postgresql.org/pub/repos/yum/reporpms/EL-7-x86_64/pgdg-redhat-repo-latest.noarch.rpm
# Install PostgreSQL:
sudo yum install -y postgresql14-server

# Optionally initialize the database and enable automatic start:
sudo /usr/pgsql-14/bin/postgresql-14-setup initdb
sudo systemctl enable postgresql-14
sudo systemctl start postgresql-14
```

Included in Distribution
These distributions all include PostgreSQL by default. To install PostgreSQL from these repositories, use the yum command on RHEL 6 and 7:

yum install postgresql-server

or dnf command on RHEL 8 and Fedora:

dnf install postgresql-server

Which version of PostgreSQL you get will depend on the version of the distribution:

Distribution	Version
RHEL / Rocky Linux / AlmaLinux 9	13
RHEL / Rocky Linux / AlmaLinux / OL 8	13, 12, 10 and 9.6 via modules
RHEL / CentOS / SL / OL 7	9.2 (also supplies package rh-postgresql10, rh-postgresql96, rh-postgresql95 and rh-postgresql94 via SCL)
RHEL / CentOS / SL / OL 6	8.4 (also supplies package rh-postgresql96, via SCL)
Fedora 39	15
Fedora 38	15
Other Red Hat family distributions may ship a different version of PostgreSQL by default, check with your distribution vendor to be sure.

The repository contains many different packages including third party addons. The most common and important packages are (substitute the version number as required):

postgresql-client	libraries and client binaries
postgresql-server	core database server
postgresql-contrib	additional supplied modules
postgresql-devel	libraries and headers for C language development
Post-installation
Due to policies for Red Hat family distributions, the PostgreSQL installation will not be enabled for automatic start or have the database initialized automatically. To make your database installation complete, you need to perform the following steps, based on your distribution:

# For RHEL / Rocky Linux / AlmaLinux / CentOS / SL / OL 7, 8, 9 Or Fedora 38 And Later Derived Distributions:
```shell
postgresql-setup --initdb
systemctl enable postgresql.service
systemctl start postgresql.service
```
For RHEL / CentOS / SL / OL 6
```shell
service postgresql initdb
chkconfig postgresql on
```
Direct RPM download
If you cannot, or do not want to, use the yum based installation method, all the RPMs that are in the yum repository are available for direct download and manual installation as well.