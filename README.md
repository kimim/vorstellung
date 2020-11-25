# vorstellung

demo with material-ui

## Prerequisites

JDK and Node.js

```shell
export GRAALVM_VERSION=20.3.0
export GRAALVM_NAME=graalvm-ce-java11-linux-amd64-$GRAALVM_VERSION
export GRAALVM_LIB=graalvm-ce-java11-$GRAALVM_VERSION
wget https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-$GRAALVM_VERSION/$GRAALVM_NAME.tar.gz
tar -xvzf $GRAALVM_NAME.tar.gz
sudo mv $GRAALVM_LIB /usr/local/lib
echo "export PATH=/usr/local/lib/$GRAALVM_LIB/bin:$PATH" >> .bashrc
source .bashrc
```

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

    lein shadow watch app login

To watch Clojure changes, run:

    lein run

And then visit: http://localhost:3030


## License

Copyright © 2020 kimim
