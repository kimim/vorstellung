# vorstellung

demo with material-ui

## Prerequisites

### JDK and Nodejs

If your machine do not have JDK and Nodejs, please refer to [install jdk and
nodejs](./resources/docs/install-djk-nodejs.md) for more details.

### clj and shadow-cljs

You will need [clj][1] and [shadow-clj][2] installed.

```shell
# clj
curl -O https://download.clojure.org/install/linux-install-1.10.2.774.sh
chmod +x linux-install-1.10.2.774.sh
sudo ./linux-install-1.10.2.774.sh

# shadow-cljs
npm install -g shadow-cljs
```

For Windows, you need add hotfix to `/usr/local/bin/clojure` at around line 247:

```diff
- tools_cp="$install_dir/libexec/clojure-tools-1.10.2.774.jar"
+ tools_cp=`cygpath -w "$install_dir/libexec/clojure-tools-1.10.2.774.jar"`
```

[1]: https://clojure.org/guides/getting_started
[2]: https://shadow-cljs.github.io/docs/UsersGuide.html

## Running

Prepare development and test config:

    cp dev-config.edn.sample.edn  dev-config.edn
    cp test-config.edn.sample.edn test-config.edn

To create migration:

    clojure -M:run create-migrate "users-table"
    clojure -M:run migrate

To drop tables:

    clojure -M:run rollback

To watch ClojureScript changes, run:

    npm install
    shadow-cljs watch dev

To watch Clojure changes, run:

    clojure -M:run

And then visit: [http://localhost:3030](http://localhost:3030)

## Unit Testing

    clojure -M:test

## Deploy to docker

    shadow-cljs release prod
    clojure -X:uberjar
    ./dockerize.sh

And then visit: [http://localhost:3000](http://localhost:3000)

## License

Copyright © 2020 kimim
