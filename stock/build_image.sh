set -ex
#docker pull openjdk:11.0-oracle
cd stock-release/build
docker build -t fuhao527/easy-ordering-stock:latest -f Dockerfile .
#docker push fuhao527/easy-ordering-stock:latest