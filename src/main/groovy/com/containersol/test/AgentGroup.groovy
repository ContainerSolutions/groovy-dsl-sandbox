package com.containersol.test

import groovy.util.logging.Slf4j

@Slf4j
class AgentGroup {

    def call(Closure cl) {

        log.info "Processing Main Configuration"

        cl.setDelegate(this);
        cl.setResolveStrategy(Closure.DELEGATE_ONLY)
        cl.call();
    }

    def imageName     = "containersol/mesos-agent"
    def imageTag      = "0.25"
    def loggingLevel  = "INFO"

    def methodMissing(String methodName, args) {

        System.out.println( Arrays.asList(args) );
        System.out.println( "" );

        log.error "Unsupported option: " + methodName

    }

}
