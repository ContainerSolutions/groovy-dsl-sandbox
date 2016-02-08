package com.containersol.test

import groovy.util.logging.Slf4j

@Slf4j
class Agent {

    def imageName     = "containersol/mesos-agent"
    def imageTag      = "0.25"
    def loggingLevel  = "INFO"

    def methodMissing(String methodName, args) {

        System.out.println( Arrays.asList(args) );
        System.out.println( "" );

        log.error "Unsupported option: " + methodName

    }

}
