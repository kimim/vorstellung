(ns vorstellung.routes.services
  (:require
   [reitit.swagger :as swagger]
   [reitit.swagger-ui :as swagger-ui]
   [reitit.ring.coercion :as coercion]
   [reitit.coercion.spec :as spec-coercion]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [reitit.ring.middleware.multipart :as multipart]
   [reitit.ring.middleware.parameters :as parameters]
   [vorstellung.middleware.formats :as formats]
   [vorstellung.middleware.exception :as exception]
   [ring.util.http-response :refer :all]
   [clojure.java.io :as io]))

(defn service-routes []
  ["/api"
   {:coercion spec-coercion/coercion
    :muuntaja formats/instance
    :swagger {:id ::api}
    :middleware [;; query-params & form-params
                 parameters/parameters-middleware
                 ;; content-negotiation
                 muuntaja/format-negotiate-middleware
                 ;; encoding response body
                 muuntaja/format-response-middleware
                 ;; exception handling
                 exception/exception-middleware
                 ;; decoding request body
                 muuntaja/format-request-middleware
                 ;; coercing response bodys
                 coercion/coerce-response-middleware
                 ;; coercing request parameters
                 coercion/coerce-request-middleware
                 ;; multipart
                 multipart/multipart-middleware]}

   ;; swagger documentation
   ["" {:no-doc true
        :swagger {:info {:title "my-api"
                         :description "https://cljdoc.org/d/metosin/reitit"}}}

    ["/swagger.json"
     {:get (swagger/create-swagger-handler)}]

    ["/api-docs/*"
     {:get (swagger-ui/create-swagger-ui-handler
            {:url "/api/swagger.json"
             :config {:validator-url nil}})}]]

   ["/ping"
    {:get (constantly (ok {:message "pong"}))}]

   ["/dogs"
    {:swagger {:tags ["dogs"]}}
    [""
     {:get {:summary "fetch all dogs info with params"
            :parameters {}
            :response {200 {:body {:dogs [{:dog {:id int? :name string? :color string?}}]}}}
            :handler (fn [{{{:keys [id]} :path} :parameters}]
                       {:status 200
                        :body {:dogs
                               [{:dog
                                 {:id 1234
                                  :name "AI"
                                  :color "brwon"}}
                                {:dog
                                 {:id 1234
                                  :name "AI"
                                  :color "brwon"}}]}})}
      :post {:summary "add a new dog"
             :parameters {:query {:name string? :color string?}}
             :response {200 {:body {:dogs [{:dog {:id int? :name string? :color string?}}]}}}
             :handler (fn [{{{:keys [name color]} :query} :parameters}]
                        {:status 200
                         :body {:dog
                                {:id 1234
                                 :name name
                                 :color color}}})}}]
    ["/:id"
     {:get {:summary "fetch dog info with id"
            :parameters {:path {:id int?}}
            :response {200 {:body {:dog {:id int? :name string? :color string?}}}}
            :handler (fn [{{{:keys [id]} :path} :parameters}]
                       {:status 200
                        :body {:dog
                               {:id 1234
                                :name "AI"
                                :color "brwon"}}})}
      :put {:summary "update a dog"
            :parameters {:path {:id int?}
                         :query {:name string? :color string?}}
            :response {200 {:body {:dog {:id int? :name string? :color string?}}}}
            :handler (fn [{{{:keys [id]} :path {:keys [name color]} :query} :parameters}]
                       {:status 200
                        :body {:dog
                               {:id id
                                :name name
                                :color color}}})}
      :delete {:summary "delete dog info with id"
               :parameters {:path {:id int?}}
               :response {200 {}}
               :handler (fn [{{{:keys [id]} :path} :parameters}]
                          {:status 200})}}]]

   ["/math"
    {:swagger {:tags ["math"]}}

    ["/plus"
     {:get {:summary "plus with spec query parameters"
            :parameters {:query {:x int?, :y int?}}
            :responses {200 {:body {:total pos-int?}}}
            :handler (fn [{{{:keys [x y]} :query} :parameters}]
                       {:status 200
                        :body {:total (+ x y)}})}
      :post {:summary "plus with spec body parameters"
             :parameters {:query {:x int?, :y int?}}
             :responses {200 {:body {:total pos-int?}}}
             :handler (fn [{{{:keys [x y]} :query} :parameters}]
                        {:status 200
                         :body {:total (+ x y)}})}}]]

   ["/files"
    {:swagger {:tags ["files"]}}

    ["/upload"
     {:post {:summary "upload a file"
             :parameters {:multipart {:file multipart/temp-file-part}}
             :responses {200 {:body {:name string?, :size int?}}}
             :handler (fn [{{{:keys [file]} :multipart} :parameters}]
                        {:status 200
                         :body {:name (:filename file)
                                :size (:size file)}})}}]

    ["/download"
     {:get {:summary "downloads a file"
            :swagger {:produces ["image/png"]}
            :handler (fn [_]
                       {:status 200
                        :headers {"Content-Type" "image/png"}
                        :body (-> "public/img/warning_clojure.png"
                                  (io/resource)
                                  (io/input-stream))})}}]]])
