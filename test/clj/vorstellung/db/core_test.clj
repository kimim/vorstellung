(ns vorstellung.db.core-test
  (:require
   [vorstellung.db.core :refer [*db*] :as db]
   [java-time.pre-java8]
   [luminus-migrations.core :as migrations]
   [clojure.test :refer :all]
   [next.jdbc :as jdbc]
   [vorstellung.config :refer [env]]
   [mount.core :as mount]))

(use-fixtures
  :once
  (fn [f]
    (mount/start
     #'vorstellung.config/env
     #'vorstellung.db.core/*db*)
    (migrations/migrate ["migrate"] (select-keys env [:database-url]))
    (f)))

(deftest test-users
  (jdbc/with-transaction [t-conn *db* {:rollback-only true}]
    (is (= 1 (db/create-user!
              t-conn
              {:email      "sam.smith@example.com"
               :first_name "Sam"
               :last_name  "Smith"
               :password   "pass"}
              {})))
    (is (= {:email      "sam.smith@example.com"
            :first_name "Sam"
            :last_name  "Smith"
            :password   "pass"}
           (db/get-user t-conn {:email "sam.smith@example.com"} {})))))
