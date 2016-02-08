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

    def exposePorts         = true
    int timeout             = 60
    String mesosVersion     = "0.25"
    def loggingLevel        = "INFO"
    def clusterName         = "minimesos-test"

    List<Agent> agents = new ArrayList<>()

    def agent( @DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=Agent) Closure cl) {
        def agent = new Agent()
        def code = cl.rehydrate(agent, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
        agents.add( agent )
    }

    def methodMissing(String methodName, args) {
        throw new MissingPropertyException("Block '" + methodName + "' not supported");
    }

}
