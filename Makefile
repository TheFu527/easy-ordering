.PHONY: all account business order stock

all: account business order stock clean

account: clean
	mvn clean package -pl account -am
	mkdir -p account-release/build
	cp -r ./account/target account-release/build/target
	cp ./account/Dockerfile account-release/build/
	cp ./account/build_image.sh account-release/build/.
	sh account-release/build/build_image.sh

business: clean
	mvn clean package -pl business -am
	mkdir -p business-release/build
	cp -r ./business/target business-release/build/target
	cp ./business/Dockerfile business-release/build/
	cp ./business/build_image.sh business-release/build/.
	sh ./business/build_image.sh

order: clean
	mvn clean package -pl order -am
	mkdir -p order-release/build
	cp -r ./order/target order-release/build/target
	cp ./order/Dockerfile order-release/build/
	cp ./order/build_image.sh order-release/build/.
	sh ./order/build_image.sh

stock: clean
	mvn clean package -pl stock -am
	mkdir -p stock-release/build
	cp -r ./stock/target stock-release/build/target
	cp ./stock/Dockerfile stock-release/build/
	cp ./stock/build_image.sh stock-release/build/.
	sh ./stock/build_image.sh

clean:
	rm -rf *release*

