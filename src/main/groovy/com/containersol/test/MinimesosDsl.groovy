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

    def methodMissing(String methodName, args) {

        if (methodName.equals("agent")) {

            Closure closure = args[0]
            closure.setDelegate(new AgentGroup())
            closure.setResolveStrategy(Closure.DELEGATE_ONLY)
            closure()

        } else {
            log.error "Unsupported option: " + methodName
        }
    }

}
