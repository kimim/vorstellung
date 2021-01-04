# vorstellung

demo with material-ui

## Prerequisites

### JDK and Node.js

- Linux

```shell
export GRAALVM_VERSION=20.3.0
export GRAALVM_NAME=graalvm-ce-java11-linux-amd64-$GRAALVM_VERSION
export GRAALVM_LIB=graalvm-ce-java11-$GRAALVM_VERSION
export GRAALVM_GITHUB=https://github.com/graalvm/graalvm-ce-builds/releases/download

wget $GRAALVM_GITHUB/vm-$GRAALVM_VERSION/$GRAALVM_NAME.tar.gz
tar -xvzf $GRAALVM_NAME.tar.gz
sudo mv $GRAALVM_LIB /usr/local/lib
echo "export PATH=/usr/local/lib/$GRAALVM_LIB/bin:/usr/local/lib/$GRAALVM_LIB/languages/js/bin:$PATH" >> ~/.bashrc
source ~/.bashrc
```

- macOS

```shell
export GRAALVM_VERSION=20.3.0
export GRAALVM_NAME=graalvm-ce-java11-darwin-amd64-$GRAALVM_VERSION
export GRAALVM_LIB=graalvm-ce-java11-$GRAALVM_VERSION
export GRAALVM_GITHUB=https://github.com/graalvm/graalvm-ce-builds/releases/download

curl -L $GRAALVM_GITHUB/vm-$GRAALVM_VERSION/$GRAALVM_PACKAGE.tar.gz \
     -o $GRAALVM_PACKAGE.tar.gz
tar -xvzf $GRAALVM_PACKAGE.tar.gz
sudo mv $GRAALVM_LIB /Library/Java/JavaVirtualMachines/
echo "export PATH=/Library/Java/JavaVirtualMachines/$GRAALVM_LIB/Contents/Home/bin:/usr/local/lib/$GRAALVM_LIB/languages/js/bin:$PATH" \
     >> ~/.bash_profile
echo "export JAVA_HOME=/Library/Java/JavaVirtualMachines/$GRAALVM_LIB/Contents/Home" \
     >> ~/.bash_profile
source ~/.bash_profile
```

- Windows

Firstly, you need to install following tools:
- `cygwin` download from https://cygwin.com/
- `7zip` download from https://www.7-zip.org/ and install to `C:\Program Files\7-Zip\`

Then open `mintty.exe`, usually automatically appear in your desktop after installing `cygwin`:

```shell
export GRAALVM_VERSION=20.3.0
export GRAALVM_PACKAGE=graalvm-ce-java11-windows-amd64-$GRAALVM_VERSION
export GRAALVM_LIB=graalvm-ce-java11-$GRAALVM_VERSION
export GRAALVM_GITHUB=https://github.com/graalvm/graalvm-ce-builds/releases/download

wget $GRAALVM_GITHUB/vm-$GRAALVM_VERSION/$GRAALVM_PACKAGE.zip
"/cygdrive/C/Program Files/7-Zip/7z.exe" x $GRAALVM_PACKAGE.zip
mv $GRAALVM_LIB /usr/local/lib
echo "export PATH=\"/usr/local/lib/$GRAALVM_LIB/bin:/usr/local/lib/$GRAALVM_LIB/languages/js/bin:$PATH\"" >> ~/.bash_profile
source ~/.bash_profile
```

### clj and shadow-cljs

You will need [clj][1] and [shadow-clj][2] installed.

```shell
# clj
curl -O https://download.clojure.org/install/linux-install-1.10.1.754.sh
chmod +x linux-install-1.10.1.754.sh
sudo ./linux-install-1.10.1.754.sh

# shadow-cljs
npm install -g shadow-cljs
```

[1]: https://clojure.org/guides/getting_started
[2]: https://shadow-cljs.github.io/docs/UsersGuide.html

## Running

Prepare development config:

    cp dev-config.edn.sample.edn dev-config.edn

To create migration:

    clj -M:run create-migrate "users-table"
    clj -M:run migrate

To drop tables:

    clj -M:run rollback

To watch ClojureScript changes, run:

    npm install
    shadow-cljs watch dev

To watch Clojure changes, run:

    clj -M:run

And then visit: [http://localhost:3030](http://localhost:3030)

## Unit Testing

    clj -M:test -m kaocha.runner

## Deploy to docker

    shadow-cljs release prod
    clj -X:uberjar
    ./dockerize.sh

And then visit: [http://localhost:3000](http://localhost:3000)

## License

Copyright © 2020 kimim
