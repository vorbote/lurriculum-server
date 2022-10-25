# lurriculum/lurriculum-server



## Getting Started

## Prerequisites

- Operating System: Ubuntu 20.04 LTS
- SDK: Corretto JDK 17 LTS or later
- Database: MySQL 5.7
- NoSQL: Redis 7.0.5
- Build System: Maven v3.8.x or later
- SMS Platform: Tencent Cloud

## Deployment

### Database

Firstly, you will need a host with a MySQL 5.7 database installed.

Then, run the SQL script in the `init.sql` file on this host.

After that, create a file named `application-db.yml` in `resources` directory, and the format is:
```yaml
spring:
  datasource:
    driver-class-name: <Your driver class>
    url: jdbc:mysql://<Your MySQL Host>:<Your MySQL Port>/curriculum_helper?characterEncoding=UTF-8&useSSL=false&useUnicode=true&serverTimezone=UTC
    type: com.zaxxer.hikari.HikariDataSource
    username: <Your MySQL Username>
    password: <Your MySQL Password>
```

### NoSQL

Firstly, you will need a host with a Redis 7.0+ installed.

After that, create a file named `application-redis.yml` in `resources` directory, and the format is:
```yaml
spring:
  redis:
    host: <Your redis host>
    password: <Your Redis Password>
```

### SMS Services

Firstly, you will need an account which can access SMS Service on Tencent Cloud Platform, and you will need a sign in your area.

Then, prepare your Access Key and Access Key Secret for your tencent cloud account.

After that, create a file named `application-msgsender.yml` in `resources` directory, and the format is:
```yaml
vorbote:
  msg-sender:
    tencent:
      app-id: <Your App ID>
      region: guangzhou
      secret-id: <Your tencent account Access Key ID>
      secret-key: <Your tencent account Access Key Secret>
      enabled: true
```

In the end, please change the sign value and templated ids in class `cn.vorbote.curriculum.values.MessageSenderConstant`.

### JWT

You will need JWT to authorize users to your application, please create a file named `application-jwt.yml` in `resources` directory, and the format is:

```yaml
vorbote:
  web-dev:
    jwt:
      enabled: true
      algorithm: <Algorithm you chose>
      secret: <Your JWT Secret>
      issuer: <Your name>
```

### Deploy this app

After all these settings to support the running of this app, you can deploy it now.

First, you'll need to build an executable jar file, then push it to your server. Then, use `java -jar` command to run it.

For all linux users, I strongly recommend you use our `systemd` service file.

```unit file (systemd)
[Unit]
Description=Lurriculum Service
After=network.target

[Service]
ExecStart=<Your java executable> -jar -Dspring.profiles.active=prod <Your executable jar file>
ExecReload=/bin/kill -9 $(/usr/bin/lsof -i tcp:<The port you define> -t) && <Your java executable> -jar -Dspring.profiles.active=prod <Your executable jar file>
ExecStop=/bin/kill -9 $(/usr/bin/lsof -i tcp:<The port you define> -t)
KillMode=process
Restart=on-failure
RestartSec=10s

[Install]
WantedBy=multi-user.target
```

## Resources

Add links to external resources for this project, such as CI server, bug tracker, etc.
