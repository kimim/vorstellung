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
echo "export PATH=/usr/local/lib/$GRAALVM_LIB/bin:$PATH" >> ~/.bashrc
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
echo "export PATH=/Library/Java/JavaVirtualMachines/$GRAALVM_LIB/Contents/Home/bin:$PATH" \
     >> ~/.bash_profile
echo "export JAVA_HOME=/Library/Java/JavaVirtualMachines/$GRAALVM_LIB/Contents/Home" \
     >> ~/.bash_profile
source ~/.bash_profile
```

- Windows

```shell
export GRAALVM_VERSION=20.3.0
export GRAALVM_PACKAGE=graalvm-ce-java11-windows-amd64-$GRAALVM_VERSION
export GRAALVM_LIB=graalvm-ce-java11-$GRAALVM_VERSION
export GRAALVM_GITHUB=https://github.com/graalvm/graalvm-ce-builds/releases/download
wget $GRAALVM_GITHUB/vm-$GRAALVM_VERSION/$GRAALVM_PACKAGE.zip
# Suppose 7zip is installed in C:\Program Files\7-Zip\
"/cygdrive/C/Program Files/7-Zip/7z.exe" x $GRAALVM_PACKAGE.zip
mv $GRAALVM_LIB /usr/local/lib
echo "export PATH=/usr/local/lib/$GRAALVM_LIB/bin:$PATH" >> ~/.bash_profile
source ~/.bash_profile

```

### Leiningen

You will need [Leiningen][1] 2.0 or above installed.

```shell
wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
chmod +x lein
sudo mv lein /usr/local/bin
lein
```

[1]: https://github.com/technomancy/leiningen

## Running

Prepare development config:

    cp dev-config.edn.sample.edn dev-config.edn

To create migration:

    lein run create-migrate "users-table"
    lein run migrate

To drop tables:

    lein run rollback

To watch ClojureScript changes, run:

    lein shadow npm-deps
    lein shadow watch app login

To watch Clojure changes, run:

    lein run

And then visit: http://localhost:3030

## License

Copyright © 2020 kimim
