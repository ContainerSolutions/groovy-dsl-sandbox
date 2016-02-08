package com.containersol.test

import groovy.util.logging.Slf4j

@Slf4j
class MinimesosDsl {

    def call(Closure cl) {

        log.info "Processing Main Configuration"

        cl.setDelegate(this);
        cl.setResolveStrategy(Closure.DELEGATE_ONLY)
        cl.call();
    }

    def exposePorts         = true
    def timeout             = 60
    def mesosVersion        = 0.25
    def loggingLevel        = "INFO"
    def clusterName         = "minimesos-test"

    def methodMissing(String methodName, args) {

        System.out.println( Arrays.asList(args) );
        System.out.println( "" );

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
