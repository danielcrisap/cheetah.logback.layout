(defproject dev.danielcrisap/cheetah.logback.layout "0.1.0-SNAPSHOT"
  :description "A Clojure library that implements a SLF4J layout and transforms logs into JSON format"
  :url "https://github.com/danielcrisap/cheetah.logback.layout"
  :license {:name "MIT"
            :url  "https://opensource.org/licenses/MIT"}

  :plugins [[lein-cloverage "1.2.4"]
            [lein-vanity "0.2.0"]
            [lein-ancient "0.7.0"]]

  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.slf4j/slf4j-api "1.7.30"]]

  :profiles {:uberjar {:aot :all}
             :dev     {:plugins [[com.github.clojure-lsp/lein-clojure-lsp "1.3.18"]]}
             :dln {:jvm-opts ["-Dclojure.compiler.direct-linking=true"]}}

  :aliases {"diagnostics"  ["clojure-lsp" "diagnostics"]
            "format"       ["clojure-lsp" "format" "--dry"]
            "format-fix"   ["clojure-lsp" "format"]
            "clean-ns"     ["clojure-lsp" "clean-ns" "--dry"]
            "clean-ns-fix" ["clojure-lsp" "clean-ns"]
            "lint"         ["do" ["diagnostics"] ["format"] ["clean-ns"]]
            "lint-fix"     ["do" ["format-fix"] ["clean-ns-fix"]]}

  :main ^:skip-aot cheetah.logback.layout.json
  :target-path "target/%s")
