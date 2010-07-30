(ns lein-search.test-utils)


(defn find-dependencies
  "Return the specified dependencies from a project.  Use
a call to (find-dependencies a-project :dev) for :dev-dependencies.
Return empty vector if not found."
  [project & [is-dev?]]
  (let [dtype (if is-dev? :dev-dependencies :dependencies)
	[kw deps] (drop-while #(not= dtype %) project)]
    (or deps [])))

(def *simple-project*
     '(defproject com.foo/bar "1.2.3-SNAPSHOT"
	:description "A description"
	:dependencies [[org.clojure/clojure "1.2.0-beta"]]
	:dev-dependencies [[lein-search "0.3.0"]]))