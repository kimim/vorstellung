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

(deftest test-dogs
  (jdbc/with-transaction [t-conn *db* {:rollback-only true}]
    ;; make sure to rollback all the records in database
    (let [bob {:name "Bob" :color "blue"}
          tom {:name "Tom" :color "green"}]
      ;; create
      (is (= 1 (db/create-dog! t-conn bob)))
      (is (= (assoc bob :id 1)
             (-> (db/get-dog t-conn {:id 1})
                 (dissoc :creatime))))
      ;; update
      (is (= 1 (db/update-dog! t-conn (assoc tom :id 1))))
      (is (= (assoc tom :id 1)
             (-> (db/get-dog t-conn {:id 1})
                 (dissoc :creatime))))
      (is (= 1 (db/create-dog! t-conn bob)))
      ;; delete
      (is (= 1 (db/delete-dog! t-conn {:id 1})))
      ;; select all
      (is (= [bob]
             (->> (db/all-dogs t-conn {})
                  (map #(dissoc % :id :creatime))))))))
