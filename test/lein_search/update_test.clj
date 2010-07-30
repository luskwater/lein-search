(ns lein-search.update-test
  (:use clojure.test
	lein-search.test-utils
	leiningen.update))



(def artifact-to-update
     '[org.clojure/clojure "1.3.0"])

(def dev-artifact-to-update
     '[lein-search "0.3.1"])

(deftest update-artifact-simple
  (let [[art version] artifact-to-update
	modified-project (update-artifact *simple-project* :dependencies
					  art version)
	actual (find-dependencies modified-project)
	expected [artifact-to-update]]
    (is (= expected actual))))

(deftest update-artifact-maintains-vector-dep
  (let [[art version] artifact-to-update
	modified-project (update-artifact *simple-project* :dependencies
			     art version)
	deps (find-dependencies modified-project)]
    (is (vector? deps))))

(deftest update-artifact-maintains-vector-dev
  (let [[art version] dev-artifact-to-update
	modified-project (update-artifact *simple-project* :dev-dependencies
			     art version)
	dev-deps (find-dependencies modified-project :dev)]
    (is (vector? dev-deps))))

