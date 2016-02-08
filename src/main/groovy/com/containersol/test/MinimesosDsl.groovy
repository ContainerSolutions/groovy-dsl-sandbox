package com.containersol.test

import groovy.util.logging.Slf4j

@Slf4j
class MinimesosDsl {

    def call(Closure cl) {

        log.info "Processing MinimesosDsl"

        cl.setDelegate(this);
        cl.setResolveStrategy(Closure.DELEGATE_ONLY)
        cl.call();
    }

    def exposePorts = true
    int timeout = 60
    String mesosVersion = "0.25"
    def loggingLevel = "INFO"
    def clusterName = "minimesos-test"

    Master master = null
    List<Agent> agents = new ArrayList<>()

    def agent(@DelegatesTo(Agent) Closure cl) {
        def agent = new Agent()
        delegateTo(agent, cl)
        agents.add(agent)
    }

    def master(@DelegatesTo(Agent) Closure cl) {
        if( master != null ) {
            throw new RuntimeException("Multiple Masters are not supported in this version yet")
        }
        master = new Master()
        delegateTo(master, cl)
    }

    def delegateTo(Object obj, Closure cl) {
        def code = cl.rehydrate(obj, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }

    def methodMissing(String methodName, args) {
        throw new MissingPropertyException("Block '" + methodName + "' not supported");
    }

}
