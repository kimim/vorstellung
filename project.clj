(defproject vorstellung "0.1.0-SNAPSHOT"

  :description "A demo app with Luminusweb and Material UI"
  :url "http://github.com/kimim/vorstellung"

  :dependencies [[buddy/buddy-auth "2.2.0"]
                 [buddy/buddy-core "1.6.0"]
                 [buddy/buddy-hashers "1.4.0"]
                 [buddy/buddy-sign "3.1.0"]
                 [ch.qos.logback/logback-classic "1.2.3"]
                 [cheshire "5.10.0"]
                 [cljs-ajax "0.8.0"]
                 [clojure.java-time "0.3.2"]
                 [com.cognitect/transit-clj "1.0.324"]
                 [com.fasterxml.jackson.core/jackson-core "2.11.2"]
                 [com.fasterxml.jackson.core/jackson-databind "2.11.2"]
                 [conman "0.9.0"]
                 [com.google.javascript/closure-compiler-unshaded "v20200504" :scope "provided"]
                 [cprop "0.1.17"]
                 [day8.re-frame/http-fx "0.1.6"]
                 [expound "0.8.5"]
                 [funcool/struct "1.4.0"]
                 [luminus-migrations "0.6.7"]
                 [luminus-http-kit "0.1.8"]
                 [luminus-transit "0.1.2"]
                 [luminus/ring-ttl-session "0.3.3"]
                 [markdown-clj "1.10.5"]
                 [metosin/jsonista "0.2.6"]
                 [metosin/muuntaja "0.6.7"]
                 [metosin/reitit "0.5.5"]
                 [metosin/ring-http-response "0.9.1"]
                 [mount "0.1.16"]
                 [nrepl "0.8.0"]
                 [org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.773" :scope "provided"]
                 [org.clojure/core.async "1.2.603"]
                 [org.clojure/google-closure-library "0.0-20191016-6ae1f72f" :scope "provided"]
                 [org.clojure/tools.cli "1.0.194"]
                 [org.clojure/tools.logging "1.1.0"]
                 [org.webjars.npm/bulma "0.9.0"]
                 [org.webjars.npm/material-icons "0.3.1"]
                 [org.webjars/webjars-locator "0.40"]
                 [re-frame "1.1.2"]
                 [reagent "1.0.0-rc1"]
                 [ring-webjars "0.2.0"]
                 [org.xerial/sqlite-jdbc "3.32.3"]
                 [ring/ring-core "1.8.1"]
                 [ring/ring-defaults "0.3.2"]
                 [selmer "1.12.28"]
                 [thheller/shadow-cljs "2.10.17" :scope "provided"]
                 ]

  :npm-deps [["@material-ui/core" "4.11.0"]
             ["@material-ui/icons" "4.9.1"]
             [bizcharts "4.0.16"]
             [process "0.11.10"]
             ["@material-ui/data-grid" "4.0.0-alpha.11"]
             [react-dropzone-uploader]]

  :npm-dev-deps [[xmlhttprequest "1.8.0"]]

  :min-lein-version "2.0.0"

  :source-paths ["src/clj" "src/cljs" "src/cljc"]
  :java-source-paths ["java"]
  :test-paths ["test/clj"]
  :resource-paths ["resources" "target/cljsbuild"]
  :target-path "target/%s/"
  :main ^:skip-aot vorstellung.core

  :plugins [[lein-shadow "0.2.0"]
            [cider/cider-nrepl "0.25.4"]]
  :clean-targets ^{:protect false} [:target-path "target/cljsbuild"]
  :shadow-cljs
  {:nrepl {:port 7002}
   :builds
   {:app
    {:target :browser
     :output-dir "target/cljsbuild/public/js"
     :asset-path "/js"
     :modules {:app {:entries [vorstellung.app]}}
     :devtools
     {:watch-dir "resources/public" :preloads [re-frisk.preload]}
     :dev
     {:closure-defines {"re_frame.trace.trace_enabled_QMARK_" true}}}
    :login
    {:target :browser
     :output-dir "target/cljsbuild/public/js"
     :asset-path "/js"
     :modules {:login {:entries [vorstellung.login]}}
     :devtools
     {:watch-dir "resources/public" :preloads [re-frisk.preload]}
     :dev
     {:closure-defines {"re_frame.trace.trace_enabled_QMARK_" true}}}
    :test
    {:target :node-test
     :output-to "target/test/test.js"
     :autorun true}}}

  :profiles
  {:uberjar {:omit-source true
             :prep-tasks ["compile" ["shadow" "release" "app" "login"]]
             :aot :all
             :uberjar-name "vorstellung.jar"
             :source-paths ["env/prod/clj"  "env/prod/cljs" ]
             :resource-paths ["env/prod/resources"]}

   :dev           [:project/dev :profiles/dev]
   :test          [:project/dev :project/test :profiles/test]

   :project/dev  {:jvm-opts ["-Dconf=dev-config.edn" ]
                  :dependencies [[binaryage/devtools "1.0.2"]
                                 [cider/piggieback "0.5.0"]
                                 [pjstadig/humane-test-output "0.10.0"]
                                 [prone "2020-01-17"]
                                 [re-frisk "1.3.4"]
                                 [ring/ring-devel "1.8.1"]
                                 [ring/ring-mock "0.4.0"]]
                  :plugins      [[com.jakemccrary/lein-test-refresh "0.24.1"]
                                 [jonase/eastwood "0.3.5"]]

                  :prep-tasks ["javac"]
                  :source-paths ["env/dev/clj"  "env/dev/cljs" "test/cljs" ]
                  :resource-paths ["env/dev/resources"]
                  :repl-options {:init-ns user
                                 :timeout 120000}
                  :injections [(require 'pjstadig.humane-test-output)
                               (pjstadig.humane-test-output/activate!)]}
   :project/test {:jvm-opts ["-Dconf=test-config.edn" ]
                  :resource-paths ["env/test/resources"]}
   :profiles/dev {}
   :profiles/test {}})
