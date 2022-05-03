# Easy Ordering

## Clone

```shell
git clone https://github.com/TheFu527/easy-ordering
cd easy-ordering
```

## Build

java version: jdk 11. Please remind! This works for all four web applications!

### Local

```shell
mvn clean package
```

### Docker

```shell
make all
```

## How to Run Local

### Run MySQL & Import SQL Script

#### Docker installation
1. Get the Release from official website https://docs.docker.com/get-docker/ 
2. (Linux) Start docker service `sudo systemctl start docker.serivce`
3. Pull mysql and create password `root` for user `root`.
```shell
docker pull mysql:5.7.37
docker run -itd --name mysql5-test -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql:5.7.37
```
#### Import sql/seata.sql to database
1. Get the container id and copy the sql/seata.sql file to container.
```
sudo docker ps # get container id like 5b10bc261e77
sudo docker cp ./sql/seata.sql container_id:seata.sql
```
3. First enter the docker container.
```
sudo docker exec -it mysql5-test bash
```
3. Enter the MySQL server
```
mysql -uroot -p
Enter password: root
mysql> CREATE SCHEMA seata;
mysql> exit
```
4. Import SQL file in docker.
```
mysql --user="root" --database="seata" --password="root" < "./seata.sql"
```


Then we can see:
```shell
+-----------------+
| Tables_in_seata |
+-----------------+
| t_account       |
| t_order         |
| t_stock         |
| undo_log        |
+-----------------+
```

### Start Nacos

[Nacos 1.1.0](https://github.com/alibaba/nacos/releases/tag/1.1.0)

[Quick Start](https://nacos.io/en-us/docs/quick-start.html)

Linux/Unix/Mac

```shell
sh startup.sh -m standalone
```

Windows

```cmd
cmd startup.cmd -m standalone
```

### Start Seata

[Seata v1.4.2](https://github.com/seata/seata/releases/tag/v1.4.2)

```shell
sh seata-server.sh --port 8091
```

### Start Easy Ordering

Start `AccountApplication`, `OrderApplication`, `StockApplication`, and `BusinessApplicaton`

Then we can see our service in [Nacos webconsole](http://127.0.0.1:8848/nacos/#/serviceManagement)

Nacos default user/pwd: nacos/nacos

### CURL

```shell
curl -H "Content-Type:application/json" -X POST -d '{"userId":"1","commodityCode":"KOI12345678","name":"iPhone","count":20,"amount":"1000"}' localhost:8104/api/orders
```

Result:

```shell
{"status":200,"message":"SUCCESS","data":null}
```

## Run on K8s

Helm Charts in `deploy/charts/easy-ordering`

### Run project dependencies on K8s

Please run MySQL, Nacos, Seata in k8s. Then modify the relevant configuration in `Values.yaml`.

```yaml
account:
  image:
    repository: fuhao527/easy-ordering-account
    pullPolicy: Always
    tag: "latest"
  config:
    dubbo:
      registry: "nacos://127.0.0.1:8848"
      protocol:
        port: 20880
    spring:
      cloud:
        nacos:
          discovery:
            server-addr: "127.0.0.1:8848"
      datasource:
        url: "jdbc:mysql://127.0.0.1:3306/seata?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true"
        username: "root"
        password: "root"
    server:
      port: 8102

business:
  image:
    repository: fuhao527/easy-ordering-business
    pullPolicy: Always
    tag: "latest"
  config:
    dubbo:
      registry: "nacos://127.0.0.1:8848"
      protocol:
        port: 10001
    spring:
      cloud:
        nacos:
          discovery:
            server-addr: "127.0.0.1:8848"
    server:
      port: 8104

stock:
  image:
    repository: fuhao527/easy-ordering-stock
    pullPolicy: Always
    tag: "latest"
  config:
    dubbo:
      registry: "nacos://127.0.0.1:8848"
      protocol:
        port: 20882
    spring:
      cloud:
        nacos:
          discovery:
            server-addr: "127.0.0.1:8848"
      datasource:
        url: "jdbc:mysql://127.0.0.1:3306/seata?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true"
        username: "root"
        password: "root"
    server:
      port: 8100


order:
  image:
    repository: fuhao527/easy-ordering-stock
    pullPolicy: Always
    tag: "latest"
  config:
    dubbo:
      registry: "nacos://127.0.0.1:8848"
      protocol:
        port: 20881
    spring:
      cloud:
        nacos:
          discovery:
            server-addr: "127.0.0.1:8848"
      datasource:
        url: "jdbc:mysql://127.0.0.1:3306/seata?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true"
        username: "root"
        password: "root"
    server:
      port: 8101
```

### Run Easy Ordering

```shell
helm install -n YOUR_NAMESPACE easy-ordering ./deploy/charts/easy-ordering
```

## Common Problem
### Cannot load configuration class

 ```
Cannot load configuration class:org.springframework.cloud.bootstrap.config.PropertySourceBootstrapConfiguration
 ```

Use the wrong version of JDK, should be jdk 11. 
The download link: https://files01.tchspt.com/temp/jdk-11.0.14_linux-x64_bin.tar.gz

### ERROR 1045 (28000)

```
ERROR 1045 (28000): Access denied for user 'root'@'localhost' (using password: NO)
```

Input wrong password of server, the user should be `root`, password should be 'root'.
