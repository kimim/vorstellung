(defproject vorstellung "0.1.0-SNAPSHOT"
  :description "A template with backend and frontend"
  :url "http://github.com/kimim/vorstellung"
  :license {:name "The MIT Licence"
            :url "https://opensource.org/licenses/MIT"}
  :plugins [[lein-tools-deps "0.4.5"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files [:install :user :project]}
  :main ^:skip-aot vorstellung.core
  :profiles
  {:uberjar {:omit-source true
             :prep-tasks ["compile" #_["shadow" "release" "app"]]
             :aot :all
             :uberjar-name "vorstellung.jar"
             :source-paths ["src/clj" "src/cljs" "src/cljc"
                            "env/prod/clj" "env/prod/cljs" ]
             :resource-paths ["resources" "target/cljsbuild"
                              "env/prod/resources"]}})
