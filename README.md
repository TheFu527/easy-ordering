# Easy Ordering

## About/Overview

## List of features

## Build

## How to Run

### Run MySQL & Import SQL Script

```shell
docker pull mysql:5.7.37
docker run -itd --name mysql5-test -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql:5.7.37
```

Import sql/seata.sql to database (create schema seata first).

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
curl -H "Content-Type:application/json" -X POST -d '{"userId":"1","commodityCode":"KOI12345678","name":"iPhone","count":20,"amount":"1000"}' localhost:8104/business/dubbo/buy
```

Result:

```shell
{"status":200,"message":"SUCCESS","data":null}
```


