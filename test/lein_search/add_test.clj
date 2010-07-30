(ns lein-search.add-test
  "Test add functions...a little"
  (:use clojure.test
	lein-search.test-utils
	leiningen.add))

(def artifact-to-add
     '[foo/bar "1.0.0-SNAPSHOT"])

(deftest add-artifact-simple
  (let [[art version] artifact-to-add
	modified-project (add-artifact *simple-project* :dependencies
			     art version)
	actual (find-dependencies modified-project)
	expected (conj (find-dependencies *simple-project*)
		       artifact-to-add)]
    (is (= expected actual))))

(deftest add-artifact-maintains-vector-dep
  (let [[art version] artifact-to-add
	modified-project (add-artifact *simple-project* :dependencies
			     art version)
	deps (find-dependencies modified-project)]
    (is (vector? deps))))

(deftest add-artifact-maintains-vector-dev
  (let [[art version] artifact-to-add
	modified-project (add-artifact *simple-project* :dev-dependencies
			     art version)
	dev-deps (find-dependencies modified-project :dev)]
    (is (vector? dev-deps))))

