# vorstellung

demo with material-ui

## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

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
