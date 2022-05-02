set -ex
#docker pull openjdk:11.0-oracle
cd order-release/build
docker build -t fuhao527/easy-ordering-order:latest -f Dockerfile .
#docker push fuhao527/easy-ordering-order:latest