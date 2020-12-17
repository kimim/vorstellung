(defproject vorstellung "0.1.0-SNAPSHOT"
  :description "A template with backend and frontend"
  :url "http://github.com/kimim/vorstellung"
  :license {:name "The MIT Licence"
            :url "https://opensource.org/licenses/MIT"}
  :plugins [[lein-tools-deps "0.4.5"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files [:install :user :project]})
