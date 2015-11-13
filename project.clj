(defproject pdp "0.1.0-SNAPSHOT"
  :description "PDP backend"
  :url         "http://127.0.0.1"
  :license     {:name "Eclipse Public License"
                :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies   [[org.clojure/clojure "1.7.0"]
                   [criterium           "0.4.3"]
                   [clongra             "0.1.0-SNAPSHOT"]]

  :plugins        [[cider/cider-nrepl   "0.9.1"]]

  :main           pdp.core
  :aot            :all

  :hooks          []
  :disable-deps-clean true

  :source-paths   ["src/clj"]
  :resource-paths ["../clongra/lib/jclongra.jar"
                   "../clongra/lib/flextao-inflector.jar"]

  :global-vars    {*warn-on-reflection* true
                   *print-length*       500}

  :jvm-opts       ["-server"

                   "-d64"

                   "-Dlog4j.configurationFile=lib/log4j2.xml"

                   "-Xshare:off"
                   "-XX:+AggressiveOpts"
                   "-XX:+DoEscapeAnalysis"
                   "-XX:+UseCompressedOops"
                   ;; "-XX:+UseNUMA" ;; to check: numactl --hardware

                   ;; HEAP SETTINGS
                   "-Xms1G"
                   "-Xmx1G"

                   ;; PARALLEL GC SETTINGS
                   "-XX:+UseParallelGC"
                   "-XX:+UseParallelOldGC"
                   "-XX:NewSize=400m"
                   "-XX:MaxNewSize=400m"
                   "-XX:-UseAdaptiveSizePolicy"
                   "-XX:SurvivorRatio=6"

                   ;; GC DEBUG
                   ;; "-verbose:gc"
                   "-XX:+PrintGCDetails"
                   "-XX:+PrintGCTimeStamps"
                   ;; "-Xloggc:gc.log"
                   ;; "-XX:+PrintTenuringDistribution"

                   ;; JCONSOLE DEBUG
                   ;; "-Xdebug"
                   ;; "-Xrunjdwp:transport=dt_socket,address=8021,server=y,suspend=n"
                   ])
